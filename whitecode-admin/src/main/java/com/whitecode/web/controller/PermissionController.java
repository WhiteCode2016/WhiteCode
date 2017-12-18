package com.whitecode.web.controller;

import com.whitecode.common.JsonResult;
import com.whitecode.dto.SysPermissionDto;
import com.whitecode.dto.SysPermissionInfoDto;
import com.whitecode.entity.SysPermission;
import com.whitecode.enums.IfEnum;
import com.whitecode.enums.TypeEnum;
import com.whitecode.page.DataTablePage;
import com.whitecode.service.SysPermissionService;
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
 * Created by White on 2017/12/18.
 */
@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController {
    @Resource
    private SysPermissionService sysPermissionService;

    @RequestMapping(value = "/listByPage",method = RequestMethod.POST)
    public DataTablePage<SysPermission> getUsersByPage(HttpServletRequest request, SysPermissionDto sysPermissionDto) {
        return sysPermissionService.getPermissionsByPage(sysPermissionDto,request);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public JsonResult addPermission(SysPermissionInfoDto sysPermissionInfoDto) {
        sysPermissionService.insertPermission(sysPermissionInfoDto);
        return JsonResultUtils.success();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public JsonResult updatePermission(SysPermissionInfoDto sysPermissionInfoDto) {
        System.out.println(sysPermissionInfoDto);
        sysPermissionService.updatePermission(sysPermissionInfoDto);
        return JsonResultUtils.success();
    }
    /*============================== View ==============================*/
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allEnable", EnumHelper.createLabelValueBeanList(IfEnum.class,getMessageSource(),true));
        modelAndView.addObject("allType", EnumHelper.createLabelValueBeanList(TypeEnum.class,getMessageSource(),true));
        modelAndView.setViewName("/permission/perQuery");
        return modelAndView;
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView insertPermissionView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allEnable", EnumHelper.createLabelValueBeanList(IfEnum.class,getMessageSource(),false));
        modelAndView.addObject("allType", EnumHelper.createLabelValueBeanList(TypeEnum.class,getMessageSource(),false));
        modelAndView.setViewName("/permission/perAdd");
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView enterEditPermission(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        SysPermission sysPermission = sysPermissionService.getPermissionById(id);
        modelAndView.addObject("sysPermission",sysPermission);
        modelAndView.addObject("allEnable", EnumHelper.createLabelValueBeanList(IfEnum.class,getMessageSource(),false));
        modelAndView.addObject("allType", EnumHelper.createLabelValueBeanList(TypeEnum.class,getMessageSource(),false));
        modelAndView.setViewName("/permission/perEdit");
        return modelAndView;
    }
}
