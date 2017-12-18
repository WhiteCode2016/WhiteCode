package com.whitecode;

import com.whitecode.dto.SysPermissionDto;
import com.whitecode.dto.SysPermissionInfoDto;
import com.whitecode.dto.SysRoleDto;
import com.whitecode.enums.IfEnum;
import com.whitecode.enums.TypeEnum;
import com.whitecode.service.SysPermissionService;
import com.whitecode.service.SysRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests {

	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysPermissionService sysPermissionService;

	@Test
	public void getRolesByCondition() {
//		SysRoleDto sysRoleDto = new SysRoleDto();
//		System.out.println(sysRoleService.getRolesByCondition(sysRoleDto));
		System.out.println(sysRoleService.getRoleById(1));
	}

	@Test
	public void getPermissionByCondition() {
	/*	SysPermissionDto sysPermissionDto = new SysPermissionDto();
		sysPermissionDto.setPerName("首页");
		System.out.println(sysPermissionService.getPermissionByCondition(sysPermissionDto));*/

		SysPermissionInfoDto sysPermissionInfoDto = new SysPermissionInfoDto();
		sysPermissionInfoDto.setPerId((long) 1);
		sysPermissionInfoDto.setEnable(IfEnum.NO);
		sysPermissionInfoDto.setPerName("首页");
		sysPermissionInfoDto.setType(TypeEnum.MENU);
		sysPermissionInfoDto.setDescription("ssss");
		sysPermissionService.updatePermission(sysPermissionInfoDto);
	}

}
