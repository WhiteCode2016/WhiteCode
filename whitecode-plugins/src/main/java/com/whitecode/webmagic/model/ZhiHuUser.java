package com.whitecode.webmagic.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by White on 2017/11/3.
 */
@Entity
@Table(name = "zhihu_user")
public class ZhiHuUser implements Serializable{
    private static final long serialVersionUID = 2237815680759413160L;
    @Id
    @GeneratedValue
    private int zHId;
    @Column(name = "zh_name")
    private String zHName;
    @Column(name = "zh_answers")
    private String zHAnswers;
    @Column(name = "zh_asks")
    private String zHAsks;
    @Column(name = "zh_posts")
    private String zHPosts;
    @Column(name = "zh_columns")
    private String zHColumns;
    @Column(name = "zh_pins")
    private String zHPins;

    public ZhiHuUser() {
    }

    public ZhiHuUser(String zHName, String zHAnswers, String zHAsks, String zHPosts, String zHColumns, String zHPins) {
        this.zHName = zHName;
        this.zHAnswers = zHAnswers;
        this.zHAsks = zHAsks;
        this.zHPosts = zHPosts;
        this.zHColumns = zHColumns;
        this.zHPins = zHPins;
    }

    public int getzHId() {
        return zHId;
    }

    public void setzHId(int zHId) {
        this.zHId = zHId;
    }

    public String getzHName() {
        return zHName;
    }

    public void setzHName(String zHName) {
        this.zHName = zHName;
    }

    public String getzHAnswers() {
        return zHAnswers;
    }

    public void setzHAnswers(String zHAnswers) {
        this.zHAnswers = zHAnswers;
    }

    public String getzHAsks() {
        return zHAsks;
    }

    public void setzHAsks(String zHAsks) {
        this.zHAsks = zHAsks;
    }

    public String getzHPosts() {
        return zHPosts;
    }

    public void setzHPosts(String zHPosts) {
        this.zHPosts = zHPosts;
    }

    public String getzHColumns() {
        return zHColumns;
    }

    public void setzHColumns(String zHColumns) {
        this.zHColumns = zHColumns;
    }

    public String getzHPins() {
        return zHPins;
    }

    public void setzHPins(String zHPins) {
        this.zHPins = zHPins;
    }

    @Override
    public String toString() {
        return "ZhiHuUser{" +
                "zHId=" + zHId +
                ", zHName='" + zHName + '\'' +
                ", zHAnswers='" + zHAnswers + '\'' +
                ", zHAsks='" + zHAsks + '\'' +
                ", zHPosts='" + zHPosts + '\'' +
                ", zHColumns='" + zHColumns + '\'' +
                ", zHPins='" + zHPins + '\'' +
                '}';
    }
}
