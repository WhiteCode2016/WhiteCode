package com.whitecode.entity;

import com.whitecode.common.WhiteCodeAdminContants;
import com.whitecode.enums.IfEnum;

import javax.persistence.*;
import java.util.List;

/**
 * 角色基本信息类
 * Created by White on 2017/9/7.
 */
@Entity
@Table(name = "sys_role",schema = WhiteCodeAdminContants.DB_SCHEMA_NAME)
public class SysRole extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID",length = 11)
    // 编号
    private Long roleId;
    // 角色名称
    @Column(name = "ROLE_NAME",length = 30)
    private String roleName;
    // 是否可用
    @Column(name = "ENABLE")
    @Enumerated(EnumType.STRING)
    private IfEnum enable;
    // 角色描述
    @Column(name = "DESCRIPTION")
    private String description;

    // 角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "ROLE_ID")}, inverseJoinColumns = {@JoinColumn(name = "PER_ID")})
    private List<SysPermission> permissions;

    // 用户 - 角色关系定义;
    @ManyToMany
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "ROLE_ID")}, inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private List<SysUser> sysUsers;// 一个角色对应多个用户

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

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public List<SysUser> getSysUsers() {
        return sysUsers;
    }

    public void setSysUsers(List<SysUser> sysUsers) {
        this.sysUsers = sysUsers;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", enable=" + enable +
                ", description='" + description + '\'' +
                ", permissions=" + permissions +
                ", sysUsers=" + sysUsers +
                '}';
    }
}
