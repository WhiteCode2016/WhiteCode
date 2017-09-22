package com.whitecode.shiro.filter;

import com.whitecode.common.JsonResult;
import com.whitecode.enums.ResultEnum;
import com.whitecode.tools.JsonResultUtil;
import com.whitecode.tools.ShiroFilterUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter{

    private static final Logger logger = LoggerFactory.getLogger(CustomFormAuthenticationFilter.class);

    // 处理登录失败
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        if (!ShiroFilterUtil.isAjax(request)) {
            setFailureAttribute(request, e);
            return Boolean.TRUE;
        }
//        Map<String, String> resultMap = new HashMap<String, String>();
        JsonResult jsonResult = new JsonResult();
        String message = e.getClass().getSimpleName();
        if ("IncorrectCredentialsException".equals(message)) {
            jsonResult = JsonResultUtil.error(ResultEnum.LOGIN_PASSWORD_ERROR);
        } else if ("UnknownAccountException".equals(message)) {
            jsonResult = JsonResultUtil.error(ResultEnum.LOGIN_ACCOUNT_ERROR);
        } else if ("LockedAccountException".equals(message)) {
            jsonResult = JsonResultUtil.error(ResultEnum.LOGIN_LOCK_ERROR);
        } else {
            jsonResult = JsonResultUtil.error(ResultEnum.UNKONW_ERROR);
        }
//        ShiroFilterUtil.out(response, resultMap);
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
}


