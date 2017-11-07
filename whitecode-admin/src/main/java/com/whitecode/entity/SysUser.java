package com.whitecode.entity;

import com.whitecode.enums.StatusEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户基本信息类
 * Created by White on 2017/9/7.
 */
@Entity
@Table(name = "sys_user")
public class SysUser implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    // 帐号
    @Column(name = "username", unique = true)
    private String username;
    // 名称（昵称或者真实姓名）
    @Column(name = "name")
    private String name;
    // 密码
    @Column(name = "password")
    private String password;
    // 用户状态
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    // 邮箱
    @Column(name = "mail")
    private String email;
    // 密钥（用于密码找回）
    @Column(name = "secret_key")
    private String secretKey;
    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<SysRole> roleList;// 一个用户具有多个角色

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
