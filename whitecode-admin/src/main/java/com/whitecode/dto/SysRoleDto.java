package com.whitecode.dto;

import com.whitecode.enums.IfEnum;

/**
 * Created by White on 2017/12/14.
 */
public class SysRoleDto {

    // 角色名称
    private String roleName;
    // 是否可用
    private IfEnum enable;


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public IfEnum getEnable() {
        return enable;
    }

    public void setEnable(IfEnum enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "SysRoleDto{" +
                "roleName='" + roleName + '\'' +
                ", enable=" + enable +
                '}';
    }
}
