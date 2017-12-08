package com.whitecode.dao.mapper;

import com.whitecode.dto.SysUserDto;
import com.whitecode.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper接口（增删改查）
 * Created by White on 2017/9/12.
 */
@Mapper
public interface SysUserMapper {
    /**
     * 根据userId获取用户
     * @param userId
     * @return
     */
    SysUser getUserById(@Param("userId") Integer userId);

    /**
     * 获取所有用户
     * @return
     */
    List<SysUser> getUsers();

    /**
     * 根据条件查询用户信息
     * @param sysUserDto
     * @return
     */
    List<SysUser> getUsersByCondition(SysUserDto sysUserDto);

    /**
     * 添加用户信息
     * @param sysUser
     */
    void insertUser(SysUser sysUser);

    /**
     * 更新用户信息
     * @param sysUser
     */
    void updateUser(SysUser sysUser);

    /**
     * 删除用户信息
     * @param userId
     */
    void deleteUser(Integer userId);
}
