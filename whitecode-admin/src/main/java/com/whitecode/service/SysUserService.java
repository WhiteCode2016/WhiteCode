package com.whitecode.service;

import com.whitecode.dto.SysUserDto;
import com.whitecode.entity.SysUser;
import com.whitecode.page.DataTablePage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户Service接口（增删改查）
 * Created by White on 2017/9/8.
 */
public interface SysUserService {
    /** 根据username查询用户信息 */
    SysUser findByUsername(String username);
    /** 根据userId查询用户信息 */
    SysUser getUserById(Integer userId);
    /** 获取所有用户信息 */
    List<SysUser> getUsers();
    /** 根据条件查询用户 */
    List<SysUser> getUsersByCondition(SysUserDto sysUserDto);
    /** 按条件查询用户信息(DataTable分页) */
    DataTablePage<SysUser> getUsersByPage(SysUserDto sysUserDto, HttpServletRequest request);
    /** 添加用户信息 */
    void insertUser(SysUser sysUser);
    /** 更新用户信息 */
    void updateUser(SysUser sysUser);
    /**  删除用户信息 */
    void deleteUser(Integer userId);
}
