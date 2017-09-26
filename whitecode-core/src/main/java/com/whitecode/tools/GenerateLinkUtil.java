package com.whitecode.tools;

import com.whitecode.common.WhiteContants;
import com.whitecode.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 生成邮箱链接地址
 * 用户名/邮箱地址+随机数进行MD5加密
 * Created by White on 2017/9/26.
 */
public class GenerateLinkUtil {
    private static final Logger logger = LoggerFactory.getLogger(GenerateLinkUtil.class);

    /**
     * 生成帐户激活链接
     */
    public static String generateActivateLink(SysUser sysUser) {
        return "http://localhost:8080/AccountActivate/activateAccount" +
                "?id=" + sysUser.getUid() + "&" +
                "checkCode=" + generateCheckcode(sysUser);
    }

    /**
     * 生成重设密码的链接
     */
    public static String generateResetPwdLink(SysUser sysUser) {
        return WhiteContants.SYSTEM_MAIL_LINK_PREFIX +
                "?userName=" + sysUser.getUsername() + "&" +
                "checkCode=" + generateCheckcode(sysUser);
    }


    // 根据用户名、随机数生成加密字符串
    public static String generateCheckcode(SysUser sysUser) {
        String username = sysUser.getUsername();
        String secretKey = sysUser.getSecretKey();
        return EncryptUtil.md5(username + "&" + secretKey);
    }


    // 验证校验码是否和注册时发送的验证码一致
    public static Boolean verifyCheckcode(SysUser sysUser, HttpServletRequest request) {
        String checkCode = request.getParameter("checkCode");
        return generateCheckcode(sysUser).equals(checkCode);
    }
}
