package com.mouday.blogapi.result;

import java.lang.annotation.*;

/**
 * 用来标记方法的返回值，是否需要包装
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResult {
}
