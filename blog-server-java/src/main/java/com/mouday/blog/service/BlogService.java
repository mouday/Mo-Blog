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
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    public Blog getBlog(Integer id) {
        Optional<Blog> optional = blogRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    public Page<Blog> getBlogs(Integer page, Integer size, Integer userId) {
        // left outer join mo_user where user_id = :userId order by create_time desc limit 20
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Specification specification = new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                Join<Blog, User> user = root.join("user", JoinType.LEFT);

                return criteriaBuilder.equal(user.get("id"), userId);
            }
        };

        Page<Blog> blogs = null;

        if (userId != null) {
            blogs = blogRepository.findAll(specification, pageable);
        } else {
            blogs = blogRepository.findAll(pageable);
        }

        return blogs;
    }


    public Page<Blog> getBlogGroupUser(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        Specification specification = new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                root.fetch("user", JoinType.LEFT);
                query.groupBy(root.get("user"));
                return query.getRestriction();
            }
        };

        return blogRepository.findAll(specification, pageable);
    }

}
