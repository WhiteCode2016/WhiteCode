package com.whitecode.web.controller;

import com.whitecode.common.JsonResult;
import com.whitecode.entity.SysUser;
import com.whitecode.enums.ResultEnum;
import com.whitecode.service.SysUserService;
import com.whitecode.tools.JsonResultUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by White on 2017/9/20.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public SysUser getUserByUsername(@PathVariable("username") String username) {
        return sysUserService.findByUsername(username);
    }

    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public JsonResult getUserById(@PathVariable("userId") Integer userId) {
        try {
            SysUser sysUser = sysUserService.getUserById(userId);
            return JsonResultUtil.success(sysUser, ResultEnum.SUCCESS);
        }catch (Exception e) {
            return JsonResultUtil.error();
        }
    }
}
