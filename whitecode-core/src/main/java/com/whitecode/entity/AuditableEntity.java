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
public abstract class AuditableEntity extends CreatableEntity{
    /** 修改人 */
    @Column(name = "MODIFIER", length = 30)
    private String modifier;

    /** 修改时间 */
    @Column(name = "MODIFY_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDateTime;

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifyDateTime() {
        return modifyDateTime;
    }

    public void setModifyDateTime(Date modifyDateTime) {
        this.modifyDateTime = modifyDateTime;
    }
}
