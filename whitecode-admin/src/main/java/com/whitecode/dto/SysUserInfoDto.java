package com.whitecode.dto;

import com.whitecode.common.WhiteCodeAdminContants;
import com.whitecode.entity.AuditableEntity;
import com.whitecode.entity.SysRole;
import com.whitecode.enums.SexEnum;
import com.whitecode.enums.StatusEnum;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 用户基本信息类
 * Created by White on 2017/9/7.
 */
public class SysUserInfoDto {

    private Long userId;
    private String username;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private SexEnum sex;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String email;
    private String secretKey;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private String description;

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

    @Override
    public String toString() {
        return "SysUserInfoDto{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
