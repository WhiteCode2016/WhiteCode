package com.whitecode.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whitecode.dao.mapper.SysPermissionMapper;
import com.whitecode.dto.SysPermissionDto;
import com.whitecode.dto.SysPermissionInfoDto;
import com.whitecode.entity.SysPermission;
import com.whitecode.page.DataTablePage;
import com.whitecode.service.SysPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by White on 2017/12/18.
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public SysPermission getPermissionById(Integer perId) {
        return sysPermissionMapper.getPermissionById(perId);
    }

    @Override
    public List<SysPermission> getPermissionByCondition(SysPermissionDto sysPermissionDto) {
        return sysPermissionMapper.getPermissionsByCondition(sysPermissionDto);
    }

    @Override
    public DataTablePage<SysPermission> getPermissionsByPage(SysPermissionDto sysPermissionDto, HttpServletRequest request) {
        //使用DataTables的属性接收分页数据
        DataTablePage<SysPermission> dataTable = new DataTablePage<SysPermission>(request);
        //开始分页：PageHelper会处理接下来的第一个查询
        PageHelper.startPage(dataTable.getPage_num(), dataTable.getPage_size());
        //还是使用List，方便后期用到
        List<SysPermission> list = getPermissionByCondition(sysPermissionDto);
        //用PageInfo对结果进行包装
        PageInfo<SysPermission> pageInfo = new PageInfo<SysPermission>(list);

        //封装数据给DataTables
        dataTable.setDraw(dataTable.getDraw());
        dataTable.setData(pageInfo.getList());
        dataTable.setRecordsTotal((int) pageInfo.getTotal());
        dataTable.setRecordsFiltered(dataTable.getRecordsTotal());

        //返回数据到页面
        return dataTable;
    }

    @Override
    public void insertPermission(SysPermissionInfoDto sysPermissionInfoDto) {
        sysPermissionMapper.insertPermission(sysPermissionInfoDto);
    }

    @Override
    public void updatePermission(SysPermissionInfoDto sysPermissionInfoDto) {
        sysPermissionMapper.updatePermission(sysPermissionInfoDto);
    }
}
