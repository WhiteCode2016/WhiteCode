package com.whitecode.entity;

import javax.persistence.Column;
import java.util.Date;

/**
 * 带有创建、更新时间的基类
 * Created by White on 2017/10/10.
 */
public abstract class DateEntity extends BaseEntity {
    // 创建时间
    @Column(name = "create_date")
    private Date createDate;
    // 更新时间
    @Column(name = "update_date")
    private Date updateDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
