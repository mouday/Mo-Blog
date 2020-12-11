package com.mouday.blogapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mouday.blogapi.pojo.User;

public interface UserMapper extends BaseMapper<User> {

    IPage<User> selectUserWithBlogCount(Page<?> page);
    
}