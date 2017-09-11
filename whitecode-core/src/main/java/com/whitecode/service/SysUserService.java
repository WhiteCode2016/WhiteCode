package com.whitecode.service;

import com.whitecode.entity.SysUser;

/**
 * 用户Service接口（增删改查）
 * Created by White on 2017/9/8.
 */
public interface SysUserService {
    /** 根据username查询用户信息 */
    public SysUser findByUsername(String username);
}
