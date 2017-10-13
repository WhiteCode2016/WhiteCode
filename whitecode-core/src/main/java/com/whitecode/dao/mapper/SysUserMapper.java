package com.whitecode.dao.mapper;

import com.whitecode.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
//    @Select("select * from sys_user where uid = #{userId}")
    SysUser getUserById(@Param("userId") Integer userId);

    /**
     * 获取所有用户
     * @return
     */
    List<SysUser> getUsers();

    /**
     * 更新用户信息
     * @param sysUser
     */
    void updateUser(SysUser sysUser);
}
