package com.whitecode.web.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by White on 2017/9/22.
 */
public class IncorrectCaptchaException extends AuthenticationException {
    private static final long serivalVersionUID = 1L;

    public IncorrectCaptchaException() {
        super();
    }

    public IncorrectCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCaptchaException(String message) {
        super(message);
    }

    public IncorrectCaptchaException(Throwable cause) {
        super(cause);
    }

}
