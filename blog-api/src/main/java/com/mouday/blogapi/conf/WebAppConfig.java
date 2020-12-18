package com.mouday.blogapi.conf;

import com.mouday.blogapi.interceptor.AuthenticationInterceptor;
import com.mouday.blogapi.interceptor.ResponseResultInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 注册拦截器
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Value("${debug}")
    private boolean debug;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        System.out.println("debug: " + this.debug);

        // 权限拦截器, 调试模式下不启用
        if (!this.debug) {
            registry.addInterceptor(new AuthenticationInterceptor())
                    .addPathPatterns("/**")
                    .excludePathPatterns("/login", "/userPassword");
        }

        // 响应拦截器
        registry.addInterceptor(new ResponseResultInterceptor())
                .addPathPatterns("/**");
    }
}