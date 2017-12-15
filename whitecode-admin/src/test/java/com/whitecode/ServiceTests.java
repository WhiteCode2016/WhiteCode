package com.whitecode;

import com.whitecode.dto.SysRoleDto;
import com.whitecode.enums.IfEnum;
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

	@Test
	public void getRolesByCondition() {
//		SysRoleDto sysRoleDto = new SysRoleDto();
//		System.out.println(sysRoleService.getRolesByCondition(sysRoleDto));
		System.out.println(sysRoleService.getRoleById(1));
	}

}
