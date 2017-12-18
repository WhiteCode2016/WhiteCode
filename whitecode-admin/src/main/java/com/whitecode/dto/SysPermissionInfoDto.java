package com.whitecode.dto;

import com.whitecode.enums.IfEnum;
import com.whitecode.enums.TypeEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by White on 2017/12/18.
 */
public class SysPermissionInfoDto {
    private Long perId;
    private String perName;
    @Enumerated(EnumType.STRING)
    private TypeEnum type;
    private String url;
    private String permission;
    private Long parentId;
    private String parentIds;
    @Enumerated(EnumType.STRING)
    private IfEnum enable;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SysPermissionInfoDto{" +
                "perId=" + perId +
                ", perName='" + perName + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
                ", permission='" + permission + '\'' +
                ", parentId=" + parentId +
                ", parentIds='" + parentIds + '\'' +
                ", enable=" + enable +
                ", description='" + description + '\'' +
                '}';
    }
}
