package com.whitecode.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 简单基类
 * Created by White on 2017/9/11.
 */
public abstract class BaseEntity implements Serializable {
    // 唯一标识符，即Id
    @Id
    @GeneratedValue
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
