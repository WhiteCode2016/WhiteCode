package com.whitecode.service;

import com.whitecode.dto.SysPermissionDto;
import com.whitecode.dto.SysPermissionInfoDto;
import com.whitecode.entity.SysPermission;
import com.whitecode.page.DataTablePage;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by White on 2017/12/18.
 */
public interface SysPermissionService {
    SysPermission getPermissionById(Integer perId);
    List<SysPermission> getPermissionByCondition(SysPermissionDto sysPermissionDto);
    DataTablePage<SysPermission> getPermissionsByPage(SysPermissionDto sysPermissionDto, HttpServletRequest request);
    void insertPermission(SysPermissionInfoDto sysPermissionInfoDto);
    void updatePermission(SysPermissionInfoDto sysPermissionInfoDto);
}
