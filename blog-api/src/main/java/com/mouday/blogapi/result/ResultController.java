package com.mouday.blogapi.result;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * 合并注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@RestController
@ResponseResult
public @interface ResultController {
    @AliasFor(annotation = RestController.class)
    String value() default "";
}
