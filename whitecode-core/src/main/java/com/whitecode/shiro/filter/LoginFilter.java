package com.whitecode.shiro.filter;

import com.google.code.kaptcha.Constants;
import com.whitecode.common.JsonResult;
import com.whitecode.enums.ResultEnum;
import com.whitecode.shiro.CustomUsernamePassordToken;
import com.whitecode.tools.JsonResultUtil;
import com.whitecode.tools.ShiroFilterUtil;
import com.whitecode.web.exception.CaptchaException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 自定义FormAuthenticationFilter
 * 包含验证码、记住我等功能
 */
public class LoginFilter extends FormAuthenticationFilter{

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    // 处理登录失败
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        if (!ShiroFilterUtil.isAjax(request)) {
            setFailureAttribute(request, e);
            return Boolean.TRUE;
        }
        JsonResult jsonResult = new JsonResult();
        String message = e.getClass().getSimpleName();
        if ("IncorrectCredentialsException".equals(message)) {
            jsonResult = JsonResultUtil.error(ResultEnum.LOGIN_PASSWORD_ERROR);
        } else if ("UnknownAccountException".equals(message)) {
            jsonResult = JsonResultUtil.error(ResultEnum.LOGIN_ACCOUNT_ERROR);
        } else if ("LockedAccountException".equals(message)) {
            jsonResult = JsonResultUtil.error(ResultEnum.LOGIN_LOCK_ERROR);
        } else if ("DisabledAccountException".equals(message)) {
            jsonResult = JsonResultUtil.error(ResultEnum.LOGIN_DISABLED_ERROR);
        } else if ("CaptchaException".equals(message)) {
            jsonResult = JsonResultUtil.error(ResultEnum.CAPTCHA_ERROR);
        } else {
            jsonResult = JsonResultUtil.error(ResultEnum.UNKONW_ERROR);
        }
        ShiroFilterUtil.out(response,jsonResult);
        return Boolean.FALSE;
    }

    // 处理登录成功
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        if (!ShiroFilterUtil.isAjax(request)) {
           issueSuccessRedirect(request, response);
            return Boolean.TRUE;
        }
        ShiroFilterUtil.out(response,JsonResultUtil.success(ResultEnum.LOGIN_SUCCESS));
        return Boolean.FALSE;
    }

    // 登录
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        if (!ShiroFilterUtil.isAjax(request)) {
            return Boolean.TRUE;
        }
        CustomUsernamePassordToken token = createToken(request,response);
        try {
            // 校验验证码（去掉，可不使用验证码）
//            doCaptchaValidate((HttpServletRequest) request,token);
            Subject subject = getSubject(request, response);
            subject.login(token);
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token,e,request,response);
        }
    }

    // 封装token信息（账号、密码、地址、记住我）
    @Override
    protected CustomUsernamePassordToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String host = getHost(request);
        boolean rememberMe = isRememberMe(request);
        String captcha = getCaptcha(request);
//        return new CustomUsernamePassordToken(username,password.toCharArray(),rememberMe,host);
        return new CustomUsernamePassordToken(username,password.toCharArray(),rememberMe,host,captcha);
    }

    // 验证码校验
    protected void doCaptchaValidate(HttpServletRequest request, CustomUsernamePassordToken token) {
        // 从session中获取图形字符串
        String captcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        logger.info(captcha +" "+ token.getCaptcha());
        // 校验
        if (captcha == null || !captcha.equals(token.getCaptcha())) {
            // 抛出验证码异常
            throw new CaptchaException();
        }
    }

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    // 保存异常对象到request
    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException e) {
        request.setAttribute(getFailureKeyAttribute(), e);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("++++++++++++++++++");
        return super.onAccessDenied(request, response);
    }
}


