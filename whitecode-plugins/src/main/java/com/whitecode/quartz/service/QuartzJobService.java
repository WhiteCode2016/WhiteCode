package com.whitecode.quartz.service;

import com.whitecode.quartz.model.ScheduleJob;
import java.util.List;

/**
 * Created by White on 2017/10/11.
 */
public interface QuartzJobService {
    // 获取所有的Jobs
    List<ScheduleJob> getAllJobs();
    // 根据id获取Job信息
    ScheduleJob getScheduleJobById(int jobId);
    // 添加Job
    void insertJob(ScheduleJob scheduleJob);
    // 更新Job
    void updateJob(ScheduleJob scheduleJob);
    // 删除Job
    void deleteJob(int jobId);

    // 初始化定时任务
    void initScheduleJob();
    // 立即执行
    void runOnce(int jobId);
    // 暂停Job
    void pauseJob(int jobId);
    // 恢复Job
    void resumeJob(int jobId);
}
