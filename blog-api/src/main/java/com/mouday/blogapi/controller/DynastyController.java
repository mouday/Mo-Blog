package com.mouday.blogapi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mouday.blogapi.exception.BaseException;
import com.mouday.blogapi.pojo.User;
import com.mouday.blogapi.result.ResultCode;
import com.mouday.blogapi.result.ResultController;
import com.mouday.blogapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ResultController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public User getUser(@RequestParam Integer id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam Integer id) {
        int ret = userService.deleteById(id);

        if(ret == 0){
            throw new BaseException(ResultCode.RECORD_NOT_EXIST);
        }
    }


    @GetMapping("/users")
    public Map<String, Object> getUsers(@RequestParam(required = false, defaultValue = "1") Integer page,
                                        @RequestParam(required = false, defaultValue = "10") Integer size) {
        IPage<User> iPage = userService.getUserList(page, size);

        Map<String, Object> map = new HashMap<>();
        map.put("list", iPage.getRecords());
        map.put("total", iPage.getTotal());

        return map;

    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        if(user.getId() != null){
            userService.update(user);
        } else{
            userService.insert(user);
        }

        return user;
    }

}
