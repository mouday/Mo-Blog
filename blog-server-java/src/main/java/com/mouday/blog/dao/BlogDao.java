package com.mouday.blog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     *
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
