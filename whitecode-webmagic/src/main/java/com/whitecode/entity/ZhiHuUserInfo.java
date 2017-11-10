package com.whitecode.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by White on 2017/11/10.
 */
@Entity
@Table(name = "zhihu_user_info")
public class ZhiHuUserInfo implements Serializable{
    private static final long serialVersionUID = -4800025470822423998L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int uid;
    // 用户Id
//    @Id
    private String id;
    // 用户名
    private String name;
    // url token
    private String urlToken;
    // 回答数
    private int answerCount;
    // 文章数
    private int articlesCount;
    // 关注者数
    private int followerCount;

    public ZhiHuUserInfo() {
    }

    public ZhiHuUserInfo(String id, String name, String urlToken, int answerCount, int articlesCount, int followerCount) {
        this.id = id;
        this.name = name;
        this.urlToken = urlToken;
        this.answerCount = answerCount;
        this.articlesCount = articlesCount;
        this.followerCount = followerCount;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlToken() {
        return urlToken;
    }

    public void setUrlToken(String urlToken) {
        this.urlToken = urlToken;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public int getArticlesCount() {
        return articlesCount;
    }

    public void setArticlesCount(int articlesCount) {
        this.articlesCount = articlesCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    @Override
    public String toString() {
        return "ZhiHuUserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", urlToken='" + urlToken + '\'' +
                ", answerCount=" + answerCount +
                ", articlesCount=" + articlesCount +
                ", followerCount=" + followerCount +
                '}';
    }
}
