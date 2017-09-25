package com.whitecode.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义UsernamePasswordToken
 * 增加了验证码信息
 * Created by White on 2017/9/25.
 */
public class CustomUsernamePassordToken extends UsernamePasswordToken {

    // 验证码
    private String captcha;

    public CustomUsernamePassordToken(String username, char[] password, boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
    }

    public CustomUsernamePassordToken(String username, char[] password, boolean rememberMe, String host, String captcha) {
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
