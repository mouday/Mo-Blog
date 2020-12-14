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
     *
     * @param user
     * @return
     */
    public User saveUser(User user) {
        if (user.getId() == null) {
            userMapper.insert(user);
        } else {
            userMapper.updateUserById(user);
        }
        return user;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public int deleteById(Integer id) {
        return userMapper.deleteById(id);
    }


    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    public User getUserById(Integer id) {
        User user = userMapper.selectById(id);

        Integer count = blogService.getCountByUserId(id);
        user.setCount(count);

        return user;
    }

    /**
     * 获取用户列表
     *
     * @param page
     * @param size
     * @return
     */
    public IPage<User> getUserList(Integer page, Integer size, String keywords) {
        Page<User> pager = new Page<>(page, size, false);
        IPage<User> iPage = userMapper.selectUserWithBlogCount(pager, keywords);

        Integer count = userMapper.selectCount(null);
        iPage.setTotal(count);
        return iPage;
    }



}
