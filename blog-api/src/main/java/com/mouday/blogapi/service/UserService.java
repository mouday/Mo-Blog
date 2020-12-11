package com.mouday.blogapi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mouday.blogapi.mapper.UserMapper;
import com.mouday.blogapi.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    BlogService blogService;

    /**
     * 更新数据
     * @param user
     * @return
     */
    public User update(User user){
        userMapper.updateById(user);
        return user;
    }

    /**
     * 插入数据
     * @param user
     * @return
     */
    public User insert(User user){
        userMapper.insert(user);
        return user;
    }

    /**
     * 获取用户
     * @param id
     * @return
     */
    public User getUserById(Integer id){
        User user = userMapper.selectById(id);

        Integer count = blogService.getCountByUserId(id);
        user.setCount(count);

        return user;
    }

    /**
     * 获取用户列表
     * @param page
     * @param size
     * @return
     */
    public IPage<User> getUserList(Integer page, Integer size){
        Page<User> pager = new Page<>(page, size, false);
        IPage<User> iPage = userMapper.selectUserWithBlogCount(pager);

        Integer count = userMapper.selectCount(null);
        iPage.setTotal(count);
        return iPage;
    }

}
