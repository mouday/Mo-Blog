package com.mouday.blogapi.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mouday.blogapi.pojo.Email;
import com.mouday.blogapi.result.ResultController;
import com.mouday.blogapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@ResultController
public class EmailController {
    @Autowired
    EmailService emailService;

    @PostMapping("/sendMail")
    public void sendMail(@RequestBody Email email) {
        emailService.sendMail(email);
    }

    @GetMapping("/emailList")
    public Map<String, Object> getEmailList(@RequestParam(required = false, defaultValue = "1") Integer page,
                                     @RequestParam(required = false, defaultValue = "10") Integer size) {
        IPage<Email> iPage = emailService.getEmailList(page, size);
        Map<String, Object> map = new HashMap<>();
        map.put("list", iPage.getRecords());
        map.put("total", iPage.getTotal());

        return map;
    }



}
