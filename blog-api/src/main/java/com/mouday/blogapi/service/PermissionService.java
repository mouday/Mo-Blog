package com.mouday.blogapi.service;

import com.mouday.blogapi.mapper.PermissionMapper;
import com.mouday.blogapi.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    PermissionMapper permissionMapper;

    public Permission savePermission(Permission permission) {
        if (permission.getId() == null) {
            permissionMapper.insert(permission);
        } else {
            permissionMapper.updateById(permission);
        }
        return permission;
    }

    public int deletePermissionById(Integer id) {
        return permissionMapper.deleteById(id);
    }

    public Permission getPermissionById(Integer id) {
        return permissionMapper.selectById(id);
    }

    public List<Permission> getPermissionList() {
        return permissionMapper.selectList(null);
    }
}
