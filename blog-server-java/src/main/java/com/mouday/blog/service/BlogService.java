package com.mouday.blog.service;

import com.mouday.blog.domain.Blog;
import com.mouday.blog.domain.User;
import com.mouday.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    // 复杂查询，使用JdbcTemplate 实现
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Blog getBlog(Integer id) {
        Optional<Blog> optional = blogRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    /**
     * 通过用户id 获取文章列表
     * @param page
     * @param size
     * @param userId 可以为 null
     * @return
     */
    public Page<Blog> getBlogs(Integer page, Integer size, Integer userId) {
        // left outer join mo_user where user_id = :userId order by create_time desc limit 20
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        // 可以单独一个函数，以便复用
        Specification specification = new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                Join<Blog, User> user = root.join("user", JoinType.LEFT);

                return criteriaBuilder.equal(user.get("id"), userId);
            }
        };

        Page<Blog> blogs = null;

        if (userId == null) {
            blogs = blogRepository.findAll(pageable);
        } else {
            blogs = blogRepository.findAll(specification, pageable);
        }

        return blogs;
    }

    /**
     * 对用户 聚合统计
     * @param page
     * @param size
     * @return
     */
    public List<Map<String, Object>> getBlogByGroupUser(Integer page, Integer size) {
        String sql = "select user.name as user_name, user.id as user_id, count(*) as count" +
                " from mo_blog blog" +
                " left join mo_user user on blog.user_id=user.id" +
                " GROUP BY user_id" +
                " ORDER BY count desc" +
                " limit ?"+
                " offset ?";

        List<Map<String, Object>> list = new ArrayList<>();

        jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, size);
                ps.setInt(2, (page - 1) * size);
                return ps;
            }
        }, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Map<String, Object> map = new HashMap<>();
                map.put("user_name", rs.getString("user_name"));
                map.put("user_id", rs.getInt("user_id"));
                map.put("count", rs.getInt("count"));
                list.add(map);
            }
        });
        return list;
    }


}
