package com.mouday.blogapi.controller;

import com.mouday.blogapi.exception.BaseException;
import com.mouday.blogapi.pojo.Role;
import com.mouday.blogapi.result.ResultCode;
import com.mouday.blogapi.result.ResultController;
import com.mouday.blogapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResultController
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/roleList")
    public List<Role> getRoleList() {
        return roleService.getRoleList();
    }

    @GetMapping("/roleListWithPermission")
    public List<Role> getRoleListWithPermission() {
        return roleService.getRoleListWithPermission();
    }


    @GetMapping("/role")
    public Role getRole(@RequestParam Integer id) {
        return roleService.getRoleById(id);
    }


    @DeleteMapping("/role")
    public void deleteRole(@RequestParam Integer id) {
        int ret = roleService.deleteRoleById(id);

        if(ret == 0){
            throw new BaseException(ResultCode.RECORD_NOT_EXIST);
        }
    }

    @PostMapping("/role")
    public Role saveRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

}
