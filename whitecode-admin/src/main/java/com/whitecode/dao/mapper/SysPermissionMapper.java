package com.whitecode.dao.mapper;

import com.whitecode.dto.SysPermissionDto;
import com.whitecode.dto.SysPermissionInfoDto;
import com.whitecode.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by White on 2017/12/18.
 */
@Mapper
public interface SysPermissionMapper {
    SysPermission getPermissionById(Integer perId);
    List<SysPermission> getPermissionsByCondition(SysPermissionDto sysPermissionDto);
    void insertPermission(SysPermissionInfoDto sysPermissionInfoDto);
    void updatePermission(SysPermissionInfoDto sysPermissionInfoDto);
}
