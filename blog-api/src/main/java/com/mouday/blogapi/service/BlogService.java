package com.mouday.blogapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mouday.blogapi.mapper.BlogMapper;
import com.mouday.blogapi.mapper.UserMapper;
import com.mouday.blogapi.pojo.Blog;
import com.mouday.blogapi.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    BlogMapper blogMapper;

    public User getUserById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    public User update(User user){
        Integer id = userMapper.updateById(user);
        QueryWrapper<Blog> query = new QueryWrapper<>();
        query.select("count(*)").eq("user_id", user.getId());
        blogMapper.selectCount(query);
        return user;
    }

    public User insert(User user){
        userMapper.insert(user);
        // user.setId(id);
        return user;
    }

    public IPage<User> getUserList(Integer page, Integer size){
        Page<User> pager = new Page<>(page, size, false);
        IPage<User> iPage = userMapper.selectByGroupUser(pager);

        Integer count = userMapper.selectCount(null);
        iPage.setTotal(count);
        return iPage;
    }

}
