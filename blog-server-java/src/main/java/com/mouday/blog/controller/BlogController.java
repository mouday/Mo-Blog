package com.mouday.blog.controller;

import com.mouday.blog.domain.Blog;
import com.mouday.blog.domain.User;
import com.mouday.blog.service.BlogService;
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
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/blog")
    public Blog getBlog(@RequestParam Integer id) {
        System.out.println(id);
        Blog blog = blogService.getBlog(id);
        System.out.println(blog);
        return blog;
    }

    @GetMapping("/blogs")
    public Object getBlogs(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "20") Integer size,
                           @RequestParam(required = false) Integer userId) {

        // Page<Blog> blogs = blogService.getBlogs(page, size, userId);
        Page<Blog> blogs = blogService.getBlogGroupUser(page, size);

        Map<String, Object> map = new HashMap<>();
        map.put("total", blogs.getTotalElements());
        map.put("list", blogs.getContent());

        return map;
    }
}
