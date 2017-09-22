package com.whitecode;

import com.github.pagehelper.PageHelper;
import com.whitecode.dao.mapper.SysUserMapper;
import com.whitecode.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceImplTest {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysUserMapper sysUserMapper;

    @Test
    public void findByUsername(){
        System.out.println(sysUserService.findByUsername("admin"));
    }

    @Test
    public void getUserById() {
        PageHelper.startPage(1,1);
        System.out.println(sysUserMapper.getUserById(1));
    }

    @Test
    public void getUsers() {
        System.out.println(sysUserMapper.getUsers());
    }
}
