package com.mouday.blogapi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mouday.blogapi.mapper.UserMapper;
import com.mouday.blogapi.pojo.Blog;
import com.mouday.blogapi.pojo.User;
import com.mouday.blogapi.result.ResponseResult;
import com.mouday.blogapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@ResponseResult
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public User getUser(@RequestParam Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/error")
    public String getError() {
        int i  = 1/0;
        return "String";
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

    @PostMapping("/insertUser")
    public User insert(@RequestBody User user){
        userService.insert(user);
        return user;
    }

    @PostMapping("/updateUser")
    public User update(@RequestBody User user){
        userService.update(user);
        return user;
    }
}
