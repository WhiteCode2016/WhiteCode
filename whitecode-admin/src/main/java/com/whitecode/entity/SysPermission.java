package com.whitecode.entity;

import com.whitecode.common.WhiteCodeAdminContants;

import javax.persistence.*;
import java.io.Serializable;
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
    private String preName;
    // 资源类型，[menu|button]
    @Column(name = "TYPE",columnDefinition = "enum('menu','button')")
    private String type;
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
    private Boolean enable = Boolean.FALSE;

    @ManyToMany
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "PER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private List<SysRole> roles;

    public Long getPerId() {
        return perId;
    }

    public void setPerId(Long perId) {
        this.perId = perId;
    }

    public String getPreName() {
        return preName;
    }

    public void setPreName(String preName) {
        this.preName = preName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
