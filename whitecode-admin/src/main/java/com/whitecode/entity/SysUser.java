package com.whitecode.entity;

import com.whitecode.common.WhiteCodeAdminContants;
import com.whitecode.enums.SexEnum;
import com.whitecode.enums.StatusEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
    @Column(name = "USERNAME", unique = true)
    private String username;
    // 密码
    @Column(name = "PASSWORD")
    private String password;
    // 名称（昵称或者真实姓名）
    @Column(name = "NAME")
    private String name;
    // 性别
    @Column(name = "SEX")
    @Enumerated(EnumType.STRING)
    private SexEnum sex;
    // 出生日期
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;
    // 邮箱
    @Column(name = "MAIL")
    private String mail;
    // 密钥（用于密码找回）
    @Column(name = "SECRET_KEY")
    private String secretKey;
    // 用户状态（锁定、正常）
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    // 用户描述
    @Column(name = "DESCRIPTION")
    private String description;

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

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }
}
