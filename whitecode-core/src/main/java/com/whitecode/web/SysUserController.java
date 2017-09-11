package com.whitecode.web;

import com.whitecode.dao.SysUserRepository;
import com.whitecode.entity.SysUser;
import com.whitecode.service.SysUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by White on 2017/9/8.
 */
@RestController
public class SysUserController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserRepository sysUserRepository;

    @RequestMapping(value = "/user/list",method = RequestMethod.GET)
    public SysUser getSysUser() {
        return sysUserService.findByUsername("admin");
    }
}
