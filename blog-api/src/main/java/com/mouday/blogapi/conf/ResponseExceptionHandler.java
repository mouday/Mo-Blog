package com.mouday.blogapi.conf;

import com.mouday.blogapi.exception.BaseException;
import com.mouday.blogapi.result.ResultCode;
import com.mouday.blogapi.result.ResultData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理
 */
@RestControllerAdvice
public class ResponseExceptionHandler {

    // 自定义异常处理
    @ExceptionHandler({BaseException.class})
    public ResultData baseExceptionHandler(BaseException e) {
        return ResultData.failure(e.getResultCode());
    }

    // 其他异常处理
    @ExceptionHandler
    public ResultData exceptionHandler(Exception e) {
        return ResultData.failure(ResultCode.UNKNOWN_ERROR);
    }
}
