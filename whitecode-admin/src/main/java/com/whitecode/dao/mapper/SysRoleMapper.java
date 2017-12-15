package com.whitecode.dao.mapper;

import com.whitecode.dto.SysRoleDto;
import com.whitecode.dto.SysRoleInfoDto;
import com.whitecode.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Created by White on 2017/12/14.
 */
@Mapper
public interface SysRoleMapper {
    SysRole getRoleById(Integer roleId);
    List<SysRole> getRolesByCondition(SysRoleDto sysRoleDto);
    void insertRole(SysRoleInfoDto sysRoleInfoDto);
    void updateRole(SysRoleInfoDto sysRoleInfoDto);
}
