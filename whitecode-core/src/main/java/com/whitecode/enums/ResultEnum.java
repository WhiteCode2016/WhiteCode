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
    CAPTCHA_ERROR(604,"验证码错误"),


    EMAIL_SEND_SUCCESS(700,"邮件发送成功"),
    EMAIL_SEND_ERROR(701,"邮件发送出错"),
    EMAIL_EMPTY(702,"请输入用户名或邮箱"),
    EMAIL_NOT_EXIST(703,"用户名或邮箱不存在"),
    EMAIL_LINK_NOT_FULL(704,"链接不完整，请重新生成"),
    EMIAL_LINK_MATCH_USER(705, "无法找到匹配用户，请重新申请找回密码"),
    EMAIL_LINK_EXPIRED(706,"链接已经过期")
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
