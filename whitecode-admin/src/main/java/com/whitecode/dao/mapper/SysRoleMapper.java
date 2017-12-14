package com.whitecode.dao.mapper;

import com.whitecode.dto.SysRoleDto;
import com.whitecode.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Created by White on 2017/12/14.
 */
@Mapper
public interface SysRoleMapper {

    List<SysRole> getRolesByCondition(SysRoleDto sysRoleDto);
}
