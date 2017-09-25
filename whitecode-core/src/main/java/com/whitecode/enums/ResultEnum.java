package com.whitecode.enums;

/**
 * Created by White on 2017/9/21.
 */
public enum ResultEnum {
    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(100, "成功"),
    ERROR(0, "失败"),
    LOGIN_SUCCESS(600,"登录成功"),
    LOGIN_ACCOUNT_ERROR(601, "该用户不存在"),
    LOGIN_PASSWORD_ERROR(602,"登录密码错误"),
    LOGIN_LOCK_ERROR(603,"该用户被锁定"),
    CAPTCHA_ERROR(604,"验证码错误")
    ;

    // 错误代码
    private Integer code;
    // 错误信息
    private String message;

    ResultEnum(Integer code, String message) {
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
