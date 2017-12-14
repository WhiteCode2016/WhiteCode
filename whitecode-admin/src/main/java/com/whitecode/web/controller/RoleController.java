package com.whitecode.web.controller;

import com.whitecode.bean.LabelValueBean;
import com.whitecode.dto.SysRoleDto;
import com.whitecode.entity.SysRole;
import com.whitecode.enums.IfEnum;
import com.whitecode.page.DataTablePage;
import com.whitecode.service.SysRoleService;
import com.whitecode.tools.EnumHelper;
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

    /*============================== View ==============================*/
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("allEnable", EnumHelper.createLabelValueBeanList(IfEnum.class,getMessageSource(),true));
        modelAndView.setViewName("/role/roleQuery");
        return modelAndView;
    }
}
