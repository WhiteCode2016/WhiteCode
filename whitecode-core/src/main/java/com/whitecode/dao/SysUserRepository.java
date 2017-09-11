package com.whitecode.dao;

import com.whitecode.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户DAO接口（增删改查）
 * Created by White on 2017/9/8.
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    /** 根据username查询用户信息 */
    public SysUser findByUsername(String username);
}
