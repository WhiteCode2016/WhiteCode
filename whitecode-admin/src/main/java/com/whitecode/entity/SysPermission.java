package com.whitecode.entity;

import com.whitecode.common.WhiteCodeAdminContants;
import com.whitecode.enums.IfEnum;
import com.whitecode.enums.TypeEnum;

import javax.persistence.*;
import java.util.List;

/**
 * 权限基本信息类
 * Created by White on 2017/9/7.
 */
@Entity
@Table(name = "sys_permission",schema = WhiteCodeAdminContants.DB_SCHEMA_NAME)
public class SysPermission extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PER_ID",length = 11)
    // 主键
    private Long perId;
    // 名称.
    @Column(name = "PER_NAME",length = 30)
    private String perName;
    // 资源类型，[menu|button]
    @Column(name = "TYPE")//columnDefinition = "enum('menu','button')"
    @Enumerated(EnumType.STRING)
    private TypeEnum type;
    // 资源路径.
    @Column(name = "URL")
    private String url;
    // 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
    @Column(name = "PERMISSION")
    private String permission;
    // 父编号
    @Column(name = "PARENT_ID")
    private Long parentId;
    // 父编号列表
    @Column(name = "PARENT_IDS")
    private String parentIds;
    // 是否可用
    @Column(name = "ENABLE")
    @Enumerated(EnumType.STRING)
    private IfEnum enable;
    // 描述
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "PER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private List<SysRole> roles;

    public Long getPerId() {
        return perId;
    }

    public void setPerId(Long perId) {
        this.perId = perId;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public IfEnum getEnable() {
        return enable;
    }

    public void setEnable(IfEnum enable) {
        this.enable = enable;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
