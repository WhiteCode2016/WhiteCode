package com.whitecode.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    private Integer uid;
    // 帐号
    @Column(name = "username", unique = true)
    private String username;
    // 名称（昵称或者真实姓名）
    @Column(name = "name")
    private String name;
    // 密码;
    @Column(name = "password")
    private String password;
    // 用户状态
    // 0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户
    // 1:正常状态
    // 2:用户被锁定
    @Column(name = "state")
    private byte state;
    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<SysRole> roleList;// 一个用户具有多个角色

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

}
