package com.mouday.blogapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mouday.blogapi.pojo.Blog;

public interface BlogMapper extends BaseMapper<Blog> {
    IPage<Blog> selectWithUser(Page<?> page, Integer dynastyId);

    int updateStatusById(Integer id, Boolean isShow);
}