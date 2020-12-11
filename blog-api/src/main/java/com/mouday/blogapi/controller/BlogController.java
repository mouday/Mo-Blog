package com.mouday.blogapi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mouday.blogapi.pojo.Blog;
import com.mouday.blogapi.result.ResponseResult;
import com.mouday.blogapi.result.ResultController;
import com.mouday.blogapi.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ResultController
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/blog")
    public Blog getBlog(@RequestParam Integer id) {
        return blogService.getBlog(id);
    }

    @GetMapping("/blogs")
    public Map<String, Object> getBlogs(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        IPage<Blog> iPage = blogService.getBlog(page, size);

        Map<String, Object> map = new HashMap<>();
        map.put("list", iPage.getRecords());
        map.put("total", iPage.getTotal());

        return map;
    }

    @PostMapping("/insertBlog")
    public Blog insert(@RequestBody Blog blog){
        blogService.insert(blog);
        return blog;
    }

    @PostMapping("/updateBlog")
    public Blog update(@RequestBody Blog blog){
        blogService.update(blog);
        return blog;
    }
}
