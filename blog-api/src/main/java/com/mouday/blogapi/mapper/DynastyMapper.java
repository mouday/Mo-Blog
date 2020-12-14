package com.mouday.blogapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mouday.blogapi.pojo.Dynasty;

import java.util.List;

public interface DynastyMapper extends BaseMapper<Dynasty> {
    List<Dynasty> getAllDynastyWithUserCount();

    int updateDynastyById(Dynasty dynasty);
}