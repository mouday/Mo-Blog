package com.mouday.blogapi.interceptor;

import com.mouday.blogapi.component.JsonWebToken;
import com.mouday.blogapi.exception.BaseException;
import com.mouday.blogapi.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    JsonWebToken jsonWebToken;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("X-Token");

        // 验证token
        if (!jsonWebToken.verifyToken(token)) {
            throw new BaseException(ResultCode.TOKEN_IS_INVALID);
        }

        System.out.println("id: " + jsonWebToken.getId(token));

        return true;
    }
}
