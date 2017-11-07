package com.whitecode.entity;

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
    @Column(name = "zh_id")
    private int zhId;
    @Column(name = "zh_name")
    private String zhName;
    @Column(name = "zh_answers")
    private String zhAnswers;
    @Column(name = "zh_asks")
    private String zhAsks;
    @Column(name = "zh_posts")
    private String zhPosts;
    @Column(name = "zh_columns")
    private String zhColumns;
    @Column(name = "zh_pins")
    private String zhPins;

    public ZhiHuUser() {
    }

    public ZhiHuUser(String zhName, String zhAnswers, String zhAsks, String zhPosts, String zhColumns, String zhPins) {
        this.zhName = zhName;
        this.zhAnswers = zhAnswers;
        this.zhAsks = zhAsks;
        this.zhPosts = zhPosts;
        this.zhColumns = zhColumns;
        this.zhPins = zhPins;
    }

    public int getZhId() {
        return zhId;
    }

    public void setZhId(int zhId) {
        this.zhId = zhId;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getZhAnswers() {
        return zhAnswers;
    }

    public void setZhAnswers(String zhAnswers) {
        this.zhAnswers = zhAnswers;
    }

    public String getZhAsks() {
        return zhAsks;
    }

    public void setZhAsks(String zhAsks) {
        this.zhAsks = zhAsks;
    }

    public String getZhPosts() {
        return zhPosts;
    }

    public void setZhPosts(String zhPosts) {
        this.zhPosts = zhPosts;
    }

    public String getZhColumns() {
        return zhColumns;
    }

    public void setZhColumns(String zhColumns) {
        this.zhColumns = zhColumns;
    }

    public String getZhPins() {
        return zhPins;
    }

    public void setZhPins(String zhPins) {
        this.zhPins = zhPins;
    }

    @Override
    public String toString() {
        return "ZhiHuUser{" +
                "zhId=" + zhId +
                ", zhName='" + zhName + '\'' +
                ", zhAnswers='" + zhAnswers + '\'' +
                ", zhAsks='" + zhAsks + '\'' +
                ", zhPosts='" + zhPosts + '\'' +
                ", zhColumns='" + zhColumns + '\'' +
                ", zhPins='" + zhPins + '\'' +
                '}';
    }
}
