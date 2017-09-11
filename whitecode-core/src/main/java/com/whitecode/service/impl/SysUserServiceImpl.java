package com.whitecode.service.impl;

import com.whitecode.dao.SysUserRepository;
import com.whitecode.entity.SysUser;
import com.whitecode.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户（增删改查）实现类
 * Created by White on 2017/9/8.
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserRepository sysUserRepository;

    @Override
    public SysUser findByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }
}
