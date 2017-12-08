package com.whitecode.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whitecode.dao.SysUserRepository;
import com.whitecode.dao.mapper.SysUserMapper;
import com.whitecode.dto.SysUserDto;
import com.whitecode.entity.SysUser;
import com.whitecode.page.DataTablePage;
import com.whitecode.service.SysUserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户（增删改查）实现类
 * Created by White on 2017/9/8.
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    @Cacheable(value = "sysUser1")
    public SysUser findByUsername(String username) {
        System.out.println("无缓存的时候调用这里---数据库查询");
        return sysUserRepository.findByUsername(username);
    }

    @Override
    public SysUser getUserById(Integer userId) {
        return sysUserMapper.getUserById(userId);
    }

    @Override
    public List<SysUser> getUsers() {
        return sysUserMapper.getUsers();
    }

    @Override
    public List<SysUser> getUsersByCondition(SysUserDto sysUserDto) {
        return sysUserMapper.getUsersByCondition(sysUserDto);
    }

    @Override
    public DataTablePage<SysUser> getUsersByPage(SysUserDto sysUserDto, HttpServletRequest request) {
        //使用DataTables的属性接收分页数据
        DataTablePage<SysUser> dataTable = new DataTablePage<SysUser>(request);
        //开始分页：PageHelper会处理接下来的第一个查询
        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        //还是使用List，方便后期用到
        List<SysUser> list = getUsersByCondition(sysUserDto);
        //用PageInfo对结果进行包装
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);

        //封装数据给DataTables
        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(pageInfo.getList());
        dataTable.setRecordsTotal((int) pageInfo.getTotal());
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());

        //返回数据到页面
        return dataTable;
    }

    @Override
    public void insertUser(SysUser sysUser) {
        sysUserMapper.insertUser(sysUser);
    }

    @Override
    public void updateUser(SysUser sysUser) {
        sysUserMapper.updateUser(sysUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        sysUserMapper.deleteUser(userId);
    }
}
