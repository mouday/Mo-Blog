package com.mouday.blog.controller;

import com.mouday.blog.domain.User;
import com.mouday.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public User getUser(@RequestParam Integer id) {
        System.out.println(id);
        User user = userService.getUser(id);
        System.out.println(user);
        return user;
    }

    @GetMapping("/users")
    public Object getUsers(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "20") Integer size) {
        Page<User> users = userService.getUsers(page, size, true);

        Map<String, Object> map = new HashMap<>();
        map.put("total", users.getTotalElements());
        map.put("list", users.getContent());

        return map;
    }
}
