package com.mouday.blogapi.service;

import com.mouday.blogapi.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void testGetUserList() {
        // IPage<User> iPage = userService.getUserList(1, 1);
    }

    @Test
    public void testUpdateUser() {

        User user = new User();
        user.setName("李小白");

        // userService.update(user);
    }

    @Test
    public void testInsertUser() {

        User user = new User();
        user.setName("李小白");

        // user = userService.insert(user);

        System.out.println(user);
    }


}
