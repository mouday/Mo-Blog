package com.mouday.blogapi.result;

/**
 * 状态码枚举
 *
 * https://mp.weixin.qq.com/s/Cvy6p-cOiPrHzAQYJdoouw
 */
public enum ResultCode {
    // 失败
    UNKNOWN_ERROR(-1, "未知错误"),

    // 成功
    SUCCESS(0, "成功"),

    // 参数错误 1001-1999
    PARAM_IS_INVALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    // 用户错误 2001-2999
    USER_NOT_LOGGED_IN(2001, "用户未登录"),
    USER_LOGIN_ERROR(2002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003, "账号已被禁用"),
    USER_NOT_EXIST(2004, "用户不存在"),
    USER_HAS_EXISTED(2005, "用户已存在"),

    // 接口异常 3000～3999
    RECORD_NOT_EXIST(3001, "记录不存在"),

    // 邮件错误
    EMAIL_SEND_ERROR(4001, "邮件发送失败"),

    // Token错误
    TOKEN_IS_INVALID(5001, "token无效"),
    TOKEN_IS_EXPIRED(5002, "token已过期"),

    ;

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
