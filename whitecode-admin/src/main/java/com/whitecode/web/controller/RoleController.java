package com.whitecode.web.controller;

import com.whitecode.bean.LabelValueBean;
import com.whitecode.common.JsonResult;
import com.whitecode.dto.SysRoleDto;
import com.whitecode.dto.SysRoleInfoDto;
import com.whitecode.entity.SysRole;
import com.whitecode.enums.IfEnum;
import com.whitecode.page.DataTablePage;
import com.whitecode.service.SysRoleService;
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
 * Created by White on 2017/12/14.
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Resource
    private SysRoleService sysRoleService;

    @RequestMapping(value = "/listByPage",method = RequestMethod.POST)
    public DataTablePage<SysRole> getUsersByPage(HttpServletRequest request, SysRoleDto sysUserDto) {
        return sysRoleService.getRolesByPage(sysUserDto,request);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public JsonResult addUser(SysRoleInfoDto sysRoleInfoDto) {
        sysRoleService.insertRole(sysRoleInfoDto);
        return JsonResultUtils.success();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public JsonResult upadteUser(SysRoleInfoDto sysRoleInfoDto) {
        sysRoleService.updateRole(sysRoleInfoDto);
        return JsonResultUtils.success();
    }

    /*============================== View ==============================*/
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allEnable", EnumHelper.createLabelValueBeanList(IfEnum.class,getMessageSource(),true));
        modelAndView.setViewName("/role/roleQuery");
        return modelAndView;
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView insertRoleView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allEnable", EnumHelper.createLabelValueBeanList(IfEnum.class,getMessageSource(),false));
        modelAndView.setViewName("/role/roleAdd");
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView enterEditRole(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        SysRole sysRole = sysRoleService.getRoleById(id);
        modelAndView.addObject("sysRole",sysRole);
        modelAndView.addObject("allEnable", EnumHelper.createLabelValueBeanList(IfEnum.class,getMessageSource(),false));
        modelAndView.setViewName("/role/roleEdit");
        return modelAndView;
    }
}
