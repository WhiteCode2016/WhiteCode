package com.whitecode.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whitecode.dao.mapper.SysRoleMapper;
import com.whitecode.dto.SysRoleDto;
import com.whitecode.dto.SysRoleInfoDto;
import com.whitecode.entity.SysRole;
import com.whitecode.page.DataTablePage;
import com.whitecode.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by White on 2017/12/14.
 */
@Service("roleService")
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysRole getRoleById(Integer roleId) {
        return sysRoleMapper.getRoleById(roleId);
    }

    @Override
    public List<SysRole> getRolesByCondition(SysRoleDto sysRoleDto) {
        return sysRoleMapper.getRolesByCondition(sysRoleDto);
    }

    @Override
    public DataTablePage<SysRole> getRolesByPage(SysRoleDto sysRoleDto, HttpServletRequest request) {
        //使用DataTables的属性接收分页数据
        DataTablePage<SysRole> dataTable = new DataTablePage<SysRole>(request);
        //开始分页：PageHelper会处理接下来的第一个查询
        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        //还是使用List，方便后期用到
        List<SysRole> list = getRolesByCondition(sysRoleDto);
        //用PageInfo对结果进行包装
        PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(list);

        //封装数据给DataTables
        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(pageInfo.getList());
        dataTable.setRecordsTotal((int) pageInfo.getTotal());
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());

        //返回数据到页面
        return dataTable;
    }

    @Override
    public void insertRole(SysRoleInfoDto sysRoleInfoDto) {
        sysRoleMapper.insertRole(sysRoleInfoDto);
    }

    @Override
    public void updateRole(SysRoleInfoDto sysRoleInfoDto) {
        sysRoleMapper.updateRole(sysRoleInfoDto);
    }
}
