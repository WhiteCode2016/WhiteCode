package com.whitecode.dto;

import com.whitecode.enums.IfEnum;

/**
 * Created by White on 2017/12/14.
 */
public class SysRoleInfoDto {

    private Long roleId;
    private String roleName;
    private IfEnum enable;
    private String description;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
