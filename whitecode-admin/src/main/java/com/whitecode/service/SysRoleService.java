package com.whitecode.service;

import com.whitecode.dto.SysRoleDto;
import com.whitecode.entity.SysRole;
import com.whitecode.page.DataTablePage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by White on 2017/12/14.
 */
public interface SysRoleService {
    /** 根据条件查询用户 */
    List<SysRole> getRolesByCondition(SysRoleDto sysRoleDto);
    /** 按条件查询用户信息(DataTable分页) */
    DataTablePage<SysRole> getRolesByPage(SysRoleDto sysRoleDto, HttpServletRequest request);
}
