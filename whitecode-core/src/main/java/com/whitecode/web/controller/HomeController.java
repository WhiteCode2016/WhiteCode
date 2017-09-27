package com.whitecode.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登录、登出控制器
 * Created by White on 2017/9/11.
 */
@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String index(){
        return "/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login";
    }

   /* @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login1(HttpServletRequest request, Map<String, Object> map) {
        Object exception = request.getAttribute("shiroLoginFailure");
//        String exception = (String) request.getAttribute("shiroLoginFailure");
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.isInstance(exception)) {
                System.out.println("账户不存在");
                msg = "账户不存在或密码不正确";
            } else if (IncorrectCredentialsException.class.isInstance(exception)) {
                System.out.println("密码不正确");
                msg = "账户不存在或密码不正确";
            } else if (IncorrectCaptchaException.class.isInstance(exception)) {
                System.out.println("验证码不正确");
                msg = "验证码不正确";
            } else {
                System.out.println("其他异常");
                msg = "其他异常";
            }
        }

        map.put("msg", msg);
        return "/login";
    }*/

//    @RequestMapping(value = "/login")
//    public String login(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap model) {
//        try {
//            Subject subject = SecurityUtils.getSubject();
//            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//            subject.login(token);
//            return "/index";
//        } catch (AuthenticationException e) {
//            model.put("message", e.getMessage());
//        }
//        return "/login";
//    }

//    @RequestMapping(value = "/login")
//    public void login(@RequestParam("username") String username, @RequestParam("password") String password) {
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        subject.login(token);
//    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }
}
