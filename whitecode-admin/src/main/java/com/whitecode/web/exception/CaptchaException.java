package com.whitecode.web.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 自定义验证码异常类
 * Created by White on 2017/9/22.
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException() {
        super();
    }

    public CaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaException(String message) {
        super(message);
    }

    public CaptchaException(Throwable cause) {
        super(cause);
    }

}
