package com.mouday.blogapi.conf;

import com.mouday.blogapi.exception.BaseException;
import com.mouday.blogapi.result.ResultCode;
import com.mouday.blogapi.result.ResultData;
import org.springframework.web.bind.annotation.*;

/**
 * 异常处理
 */
@ControllerAdvice
@ResponseBody
public class ResponseExceptionHandler {

    // 自定义异常处理
    @ExceptionHandler({BaseException.class})
    public ResultData baseExceptionHandler(BaseException e) {
        e.printStackTrace();
        return ResultData.failure(e.getResultCode());
    }

    // 其他异常处理
    @ExceptionHandler
    public ResultData exceptionHandler(Exception e) {
        e.printStackTrace();
        return ResultData.failure(ResultCode.UNKNOWN_ERROR);
    }
}
