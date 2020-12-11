package com.mouday.blogapi.conf;

import com.mouday.blogapi.result.ResultData;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 包装返回数据
 */
@RestControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
    /**
     * 如果有注解 ResponseResult 就对数据进行包装
     */
    @Override
    public boolean supports(
            MethodParameter returnType,
            Class<? extends HttpMessageConverter<?>> converterType) {

        System.out.println("ResponseResultHandler.supports");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object flag = request.getAttribute(ResponseResultInterceptor.RESPONSE_RESULT);

        System.out.println("flag: " + flag);

        return flag != null;
    }

    /**
     * 重写返回体
     */
    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {

        System.out.println("ResponseResultHandler.beforeBodyWrite");

        if(body instanceof ResultData){
            return body;
        } else{
            return ResultData.success(body);
        }
    }
}
