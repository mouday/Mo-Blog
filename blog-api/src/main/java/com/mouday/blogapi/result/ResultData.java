package com.mouday.blogapi.result;

import java.io.Serializable;

/**
 * 返回数据
 */
public class ResultData implements Serializable {
    private Integer code;

    private String message;

    private Object data;

    public ResultData() {
    }

    public ResultData(ResultCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    // 返回成功
    public static ResultData success() {
        return success(null);
    }

    // 返回成功
    public static ResultData success(Object data) {
        ResultData resultData = new ResultData();
        resultData.setResultCode(ResultCode.SUCCESS);
        resultData.setData(data);
        return resultData;
    }

    // 返回失败
    public static ResultData failure(ResultCode resultCode) {
        return failure(resultCode, null);
    }

    // 返回失败
    public static ResultData failure(ResultCode resultCode, Object data) {
        ResultData resultData = new ResultData();
        resultData.setResultCode(resultCode);
        resultData.setData(data);
        return resultData;
    }

    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
