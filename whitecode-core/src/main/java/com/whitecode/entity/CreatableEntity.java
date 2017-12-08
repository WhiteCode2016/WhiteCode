package com.whitecode.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by White on 2017/12/8.
 */
@MappedSuperclass
public abstract class CreatableEntity extends BaseEntity {
    /** 创建人 */
    @Column(name = "CREATOR", length = 30)
    private String creator;

    /** 创建时间 */
    @Column(name = "CREATE_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateTime;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
}
