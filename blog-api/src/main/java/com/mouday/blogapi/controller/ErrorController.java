package com.mouday.blogapi.controller;

import com.mouday.blogapi.exception.BaseException;
import com.mouday.blogapi.result.ResultCode;
import com.mouday.blogapi.result.ResultController;
import com.mouday.blogapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 错误测试
 */
@ResultController
public class ErrorController {
    @Autowired
    UserService userService;

    @GetMapping("/error")
    public String getError() {
        int i = 1 / 0;
        return "String";
    }

    @GetMapping("/exception")
    public void getException() {
        throw new BaseException(ResultCode.PARAM_IS_BLANK);
    }
}
