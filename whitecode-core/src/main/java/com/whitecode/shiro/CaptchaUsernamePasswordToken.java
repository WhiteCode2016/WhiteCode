package com.whitecode.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by White on 2017/9/22.
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

    // 验证码
    private String captcha;

    public CaptchaUsernamePasswordToken(String username, char[] password, boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
