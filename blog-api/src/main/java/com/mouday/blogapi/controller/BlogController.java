package com.mouday.blogapi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mouday.blogapi.exception.BaseException;
import com.mouday.blogapi.pojo.Blog;
import com.mouday.blogapi.result.ResultCode;
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

    @DeleteMapping("/blog")
    public void deleteBlog(@RequestParam Integer id) {
        int ret = blogService.deleteById(id);

        if(ret == 0){
            throw new BaseException(ResultCode.RECORD_NOT_EXIST);
        }
    }

    @GetMapping("/blogs")
    public Map<String, Object> getBlogs(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false) Integer dynastyId
            ) {
        IPage<Blog> iPage = blogService.getBlogList(page, size, dynastyId);

        Map<String, Object> map = new HashMap<>();
        map.put("list", iPage.getRecords());
        map.put("total", iPage.getTotal());

        return map;
    }

    @PostMapping("/blog")
    public Blog saveBlog(@RequestBody Blog blog){
        return blogService.saveBlog(blog);
    }

    @PostMapping("/blogStatus")
    public void saveBlogStatus(@RequestBody Blog blog){
        int ret = blogService.updateShowStatusById(blog.getId(), blog.getIsShow());

        if(ret == 0){
            throw new BaseException(ResultCode.RECORD_NOT_EXIST);
        }
    }

}
