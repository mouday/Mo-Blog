package com.mouday.blogapi.service;

import com.mouday.blogapi.mapper.RoleMapper;
import com.mouday.blogapi.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    public Role saveRole(Role role) {
        if (role.getId() == null) {
            roleMapper.insert(role);
        } else {
            roleMapper.updateById(role);
        }
        return role;
    }

    public int deleteRoleById(Integer id) {
        return roleMapper.deleteById(id);
    }

    public Role getRoleById(Integer id) {
        return roleMapper.selectById(id);
    }

    public List<Role> getRoleList() {
        return roleMapper.selectList(null);
    }

    public List<Role> getRoleListWithPermission() {
        return roleMapper.getRoleListWithPermission();
    }

}
