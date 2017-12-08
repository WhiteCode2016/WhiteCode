package com.whitecode.web.controller;

import com.whitecode.common.JsonResult;
import com.whitecode.dto.SysUserDto;
import com.whitecode.entity.SysUser;
import com.whitecode.enums.ResultEnum;
import com.whitecode.page.DataTablePage;
import com.whitecode.service.SysUserService;
import com.whitecode.tools.JsonResultUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
            return JsonResultUtils.success(sysUser, ResultEnum.SUCCESS);
        }catch (Exception e) {
            return JsonResultUtils.error();
        }
    }

    @RequestMapping(value = "/listByPage",method = RequestMethod.POST)
    public DataTablePage<SysUser> getUsersByPage(HttpServletRequest request, SysUserDto sysUserDto) {
        return sysUserService.getUsersByPage(sysUserDto,request);
    }

    /*============================== View ==============================*/
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("/user/userQuery");
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView insertUserView() {
        return new ModelAndView("/user/userAdd");
    }
}
