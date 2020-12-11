package com.mouday.blogapi.exception;

import com.mouday.blogapi.result.ResultCode;

public class BaseException extends RuntimeException{
    private ResultCode resultCode;

    public BaseException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
