package com.whitecode.entity;

import com.whitecode.enums.QuartzStatusEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * 计划任务信息
 * Created by White on 2017/10/11.
 */
public class ScheduleJob implements Serializable {
    private static final long serialVersionUID = -5572338399266720383L;
    // 任务Id
    private String jobId;
    // 任务名称
    private String jobName;
    // 任务分组
    private String jobGroup;
    // 任务状态 0禁用 1启用 2删除
    @Enumerated(EnumType.STRING)
    private QuartzStatusEnum jobStatus;
    // 任务类型（是否异步）
    private String jobType;
    // 任务运行时间表达式
    private String cronExpression;
    // 任务执行类
    private String beanClass;
    // 任务执行方法
    private String executeMethod;
    // 任务创建时间
    private String createTime;
    // 任务更新时间
    private String updateTime;
    // 任务描述
    private String jobDesc;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public QuartzStatusEnum getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(QuartzStatusEnum jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    public String getExecuteMethod() {
        return executeMethod;
    }

    public void setExecuteMethod(String executeMethod) {
        this.executeMethod = executeMethod;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    @Override
    public String toString() {
        return "ScheduleJob{" +
                "jobId='" + jobId + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", jobStatus='" + jobStatus + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", beanClass='" + beanClass + '\'' +
                ", executeMethod='" + executeMethod + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", jobDesc='" + jobDesc + '\'' +
                '}';
    }
}
