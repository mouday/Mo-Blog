package com.mouday.blogapi.controller;

import com.mouday.blogapi.exception.BaseException;
import com.mouday.blogapi.pojo.Permission;
import com.mouday.blogapi.result.ResultCode;
import com.mouday.blogapi.result.ResultController;
import com.mouday.blogapi.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResultController
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @GetMapping("/PermissionList")
    public List<Permission> getPermissionList() {
        return permissionService.getPermissionList();
    }


    @GetMapping("/Permission")
    public Permission getPermission(@RequestParam Integer id) {
        return permissionService.getPermissionById(id);
    }


    @DeleteMapping("/Permission")
    public void deletePermission(@RequestParam Integer id) {
        int ret = permissionService.deletePermissionById(id);

        if(ret == 0){
            throw new BaseException(ResultCode.RECORD_NOT_EXIST);
        }
    }

    @PostMapping("/Permission")
    public Permission savePermission(@RequestBody Permission permission) {
        return permissionService.savePermission(permission);
    }

}
