package com.whitecode;

import com.whitecode.entity.SysUser;
import com.whitecode.tools.GenerateLinkUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MailTest {
    @Autowired
    private JavaMailSender javaMailSender;
  /*  @Test
    public void sendSimpleMail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("18855971263@163.com");
        message.setTo("18855971263@163.com");
        message.setSubject("主题：简单邮件");
//        message.setText("测试邮件内容");
        message.setText("要使用新的密码, 请使用以下链接启用密码:<br/><a href='" + GenerateLinkUtils.generateResetPwdLink() +"'>点击重新设置密码</a>");
        javaMailSender.send(message);
    }
*/
    @Test
    public void sendResetPasswordEmail() {
        Session session =getSession();
        MimeMessage message = new MimeMessage(session);
        try {
            SysUser user = new SysUser();
            user.setUsername("whitecode");
            message.setSubject("找回您的帐户与密码");
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress("18855971263@163.com"));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("18855971263@163.com"));
            message.setContent("要使用新的密码, 请使用以下链接启用密码:<br/><a href='" + GenerateLinkUtils.generateResetPwdLink(user) +"'>点击重新设置密码</a>","text/html;charset=utf-8");
            // 发送邮件
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Session getSession() {
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
