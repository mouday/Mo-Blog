package com.mouday.blog.controller;

import com.mouday.blog.domain.Blog;
import com.mouday.blog.domain.User;
import com.mouday.blog.service.BlogService;
import com.mouday.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 页面控制器
 */
@Controller
public class IndexController {
    @Autowired
    UserService userService;

    @Autowired
    BlogService blogService;

    /**
     * 首页
     * @param page
     * @param size
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "20") Integer size,
                          Model model) {

        Page<Blog> blogs = blogService.getBlogs(page, size, null);

        model.addAttribute("list", blogs.getContent());
        model.addAttribute("total", blogs.getTotalElements());

        return "index";
    }


    /**
     * 用户主页
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/user/{userId}")
    public String getUser(@PathVariable Integer userId, Model model){
        User user = userService.getUser(userId);
        Page<Blog> blogs = blogService.getBlogs(1, 10, userId);

        model.addAttribute("user", user);

        model.addAttribute("list", blogs.getContent());
        model.addAttribute("total", blogs.getTotalElements());

        return "user";
    }

    /**
     * 获取用户列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/users")
    public String getUsers(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "20") Integer size,
                           Model model){

        List<Map<String, Object>> list = blogService.getBlogByGroupUser(page, size);

        model.addAttribute("list", list);

        return "users";
    }

    /**
     * 通过id 获取博客详情
     * @param blogId
     * @return
     */
    @GetMapping("/blog/{blogId}")
    public String getBlog(@PathVariable Integer blogId,
                           Model model) {

        Blog blog = blogService.getBlog(blogId);


        List<String> list = new ArrayList<>();

        String content = blog.getContent();
        String[] lines = content.split("。");

        for (String line: lines){
            list.add("<p>"+line+"。</p>");
        }

        blog.setContent(String.join("", list));

        model.addAttribute("blog", blog);

        return "blog";
    }
}
