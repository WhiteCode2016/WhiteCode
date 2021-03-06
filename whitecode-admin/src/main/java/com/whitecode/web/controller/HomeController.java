package com.whitecode.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 主页、登录、登出控制器
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

    @RequestMapping(value = "/common/register", method = RequestMethod.GET)
    public String register() {
        return "/register";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String unauthorizedRole(){
        return "/403";
    }
}
