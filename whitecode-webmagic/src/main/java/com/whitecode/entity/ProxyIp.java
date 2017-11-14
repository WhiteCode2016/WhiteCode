package com.whitecode.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 代理服务器信息类（来源于 快代理 http://www.kuaidaili.com/free/intr/1/）
 * Created by White on 2017/11/13.
 */
@Entity
@Table(name = "proxy_ip_info")
public class ProxyIp implements Serializable {
    private static final long serialVersionUID = 5857838117874709099L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ip;
    private Integer port;
    private String type;
    private String addr;
    private Boolean used;
    private String other;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "ProxyIp{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", type='" + type + '\'' +
                ", addr='" + addr + '\'' +
                ", used=" + used +
                ", other='" + other + '\'' +
                '}';
    }
}
