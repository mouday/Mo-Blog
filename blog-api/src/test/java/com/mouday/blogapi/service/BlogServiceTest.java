package com.mouday.blogapi.service;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BlogServiceTest {
    @Autowired
    BlogService blogService;

    @Test
    public void TestGetBlog(){
        // blogService.getBlog();
    }


    @Test
    void getCountByUserId() {
    }

    @Test
    void testGetBlog() {

    }

}
