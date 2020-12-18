package com.mouday.blogapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mouday.blogapi.pojo.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    List<Role>  getRoleListWithPermission();
}