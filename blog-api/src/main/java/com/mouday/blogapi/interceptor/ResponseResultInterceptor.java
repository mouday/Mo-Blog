package com.mouday.blogapi.conf;

import com.mouday.blogapi.result.ResponseResult;
import com.mouday.blogapi.result.ResultController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 获取需要包装的数据标记
 */
public class ResponseResultInterceptor implements HandlerInterceptor {

    // 标记
    public static final String RESPONSE_RESULT = "RESPONSE-RESULT";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("ResponseResultInterceptor.preHandle");

        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();

            // 判断是否在类对象上加了注解, 做需要包装的标记
            if (hasAnnotation(clazz.getAnnotations())) {
                request.setAttribute(RESPONSE_RESULT, true);
            }

            if (hasAnnotation(method.getAnnotations())) {
                request.setAttribute(RESPONSE_RESULT, true);
            }
        }

        return true;
    }

    /**
     * 判断是否包含包装对象注解
     * @param annotations
     * @return
     */
    private boolean hasAnnotation(Annotation[] annotations) {

        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == ResponseResult.class
                    || annotation.annotationType() == ResultController.class) {
                return true;
            }
        }

        return false;
    }

}
