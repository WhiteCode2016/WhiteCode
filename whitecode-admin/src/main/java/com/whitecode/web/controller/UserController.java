package com.whitecode.web.controller;

import com.whitecode.bean.LabelValueBean;
import com.whitecode.common.JsonResult;
import com.whitecode.dto.SysUserDto;
import com.whitecode.dto.SysUserInfoDto;
import com.whitecode.entity.SysUser;
import com.whitecode.enums.IfEnum;
import com.whitecode.enums.ResultEnum;
import com.whitecode.enums.SexEnum;
import com.whitecode.enums.StatusEnum;
import com.whitecode.page.DataTablePage;
import com.whitecode.service.SysUserService;
import com.whitecode.tools.EnumHelper;
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
public class UserController extends BaseController {

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

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public JsonResult addUser(SysUserInfoDto sysUserInfoDto) {
        System.out.println(sysUserInfoDto);
//        sysUserService.insertUser(sysUser);
        return JsonResultUtils.success();
    }

    /*============================== View ==============================*/
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allStatus", EnumHelper.createLabelValueBeanList(StatusEnum.class,getMessageSource(),new LabelValueBean<>("--所有状态--","")));
        modelAndView.setViewName("/user/userQuery");
        return modelAndView;
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView insertUserView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allStatus", EnumHelper.createLabelValueBeanList(StatusEnum.class,getMessageSource(),false));
        modelAndView.addObject("allSex", EnumHelper.createLabelValueBeanList(SexEnum.class,getMessageSource(),false));
        modelAndView.setViewName("/user/userAdd");
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView enterEditUser(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        SysUser sysUser = sysUserService.getUserById(id);
        System.out.println(sysUser.getStatus());
        modelAndView.addObject("sysUser",sysUser);
        modelAndView.addObject("allStatus", EnumHelper.createLabelValueBeanList(StatusEnum.class,getMessageSource(),false));
        modelAndView.addObject("allSex", EnumHelper.createLabelValueBeanList(SexEnum.class,getMessageSource(),false));
        modelAndView.setViewName("/user/userEdit");
        return modelAndView;
    }
}
