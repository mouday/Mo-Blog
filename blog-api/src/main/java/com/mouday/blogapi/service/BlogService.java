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

@Service
public class BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 获取用户的文章数
     *
     * @param userId
     * @return
     */
    public Integer getCountByUserId(Integer userId) {
        QueryWrapper<Blog> query = new QueryWrapper<>();
        query.eq("user_id", userId);
        Integer count = blogMapper.selectCount(query);
        return count;
    }

    /**
     * 获取文章详情
     *
     * @param id
     * @return
     */
    public Blog getBlog(Integer id) {
        Blog blog = blogMapper.selectById(id);
        User user = userMapper.selectById(blog.getUserId());

        Integer count = this.getCountByUserId(blog.getUserId());
        user.setCount(count);
        blog.setUser(user);
        return blog;
    }

    /**
     * 获取文章列表
     *
     * @param page
     * @param size
     * @return
     */
    public IPage<Blog> getBlog(Integer page, Integer size) {
        Page<Blog> pager = new Page<>(page, size, false);
        IPage<Blog> iPage = blogMapper.selectWithUser(pager);
        Integer count = blogMapper.selectCount(null);
        iPage.setTotal(count);
        return iPage;
    }

    /**
     * 插入数据
     * @param blog
     * @return
     */
    public Blog insert(Blog blog){
        blogMapper.insert(blog);
        return blog;
    }

    /**
     * 更新数据
     * @param blog
     * @return
     */
    public Blog update(Blog blog){
        blogMapper.updateById(blog);
        return blog;
    }

}
