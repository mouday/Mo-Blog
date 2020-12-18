package com.mouday.blogapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@MapperScan("com.mouday.blogapi.mapper") // 指定mapper扫描包
public class BlogApiApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(BlogApiApplication.class, args);
        Environment environment = context.getBean(Environment.class);
        System.out.println("访问链接：http://localhost:" + environment.getProperty("local.server.port"));
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
