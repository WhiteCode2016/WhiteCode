package com.whitecode.web.controller;

import com.whitecode.common.JsonResult;
import com.whitecode.service.SysUserService;
import com.whitecode.tools.JsonResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * 测试Controller
 * Created by White on 2017/9/11.
 */
@RestController
public class TestController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String TestHello() {
        return "Hello World";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @RequiresPermissions("user:info")
    public String TestUser() {
//        SysUser sysUser = sysUserService.findByUsername("admin");
//        return sysUser.getRoleList().get(0).getPermissions();
//        return sysUser;
        return "test";
    }

    @RequestMapping(value = "/testLogin", method = RequestMethod.POST)
    public JsonResult login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, HttpServletRequest request) throws Exception{
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            return JsonResultUtil.success();
        } catch (AuthenticationException e) {
            return JsonResultUtil.error();
        }
    }

}
