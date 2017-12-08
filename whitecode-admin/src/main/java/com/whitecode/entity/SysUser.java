package com.whitecode.entity;

import com.whitecode.common.WhiteCodeAdminContants;
import com.whitecode.enums.StatusEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户基本信息类
 * Created by White on 2017/9/7.
 */
@Entity
@Table(name = "sys_user",schema = WhiteCodeAdminContants.DB_SCHEMA_NAME)
public class SysUser extends AuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID",length = 11)
    private Long userId;
    // 帐号
    @Column(name = "USER_NAME", unique = true)
    private String username;
    // 密码
    @Column(name = "PASSWORD")
    private String password;
    // 名称（昵称或者真实姓名）
    @Column(name = "NAME")
    private String name;
    // 用户状态（锁定、正常）
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    // 邮箱
    @Column(name = "MAIL")
    private String email;
    // 密钥（用于密码找回）
    @Column(name = "SECRET_KEY")
    private String secretKey;

    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns ={@JoinColumn(name = "ROLE_ID") })
    private List<SysRole> roleList;// 一个用户具有多个角色

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }
}
