package com.whitecode.tools;

import com.whitecode.common.WhiteContants;
import com.whitecode.entity.SysUser;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件工具类（重设密码、激活账户）
 * Created by White on 2017/9/26.
 */
public class EmailUtil {

    /**
     * 注册成功后,向用户发送帐户激活链接的邮件
     * @param user 未激活的用户
     */
    public static void sendAccountActivateEmail(SysUser user) {
        Session session = getSession();
        MimeMessage message = new MimeMessage(session);
        try {
            message.setSubject("帐户激活邮件");
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(WhiteContants.SYSTEM_MAIL));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setContent("<a href='" + GenerateLinkUtil.generateActivateLink(user)+"'>点击激活帐户</a>","text/html;charset=utf-8");
            // 发送邮件
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 发送重设密码链接的邮件
    public static void sendResetPasswordEmail(SysUser user) {
        Session session = getSession();
        MimeMessage message = new MimeMessage(session);
        try {
            message.setSubject("找回您的帐户与密码");
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(WhiteContants.SYSTEM_MAIL));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setContent("要使用新的密码, 请使用以下链接启用密码:<br/><a href='" + GenerateLinkUtil.generateResetPwdLink(user) +"'>点击重新设置密码</a>","text/html;charset=utf-8");
            // 发送邮件
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Session getSession() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.163.com");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String password = null;
//                InputStream is = EmailUtils.class.getResourceAsStream("spring.mail.password");
//                byte[] b = new byte[1024];
//                try {
//                    int len = is.read(b);
//                    password = new String(b,0,len);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                return new PasswordAuthentication("18855971263@163.com", "lk12345");
            }

        });
        return session;
    }
}
