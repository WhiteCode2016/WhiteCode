package com.whitecode.service;


import com.whitecode.entity.ScheduleJob;
import java.util.List;

/**
 * Created by White on 2017/10/11.
 */
public interface QuartzJobService {
    // 获取所有的Jobs
    List<ScheduleJob> getAllJobs();
    // 根据id获取Job信息
    ScheduleJob getScheduleJobById(String jobId);
    // 添加Job
    void insertJob(ScheduleJob scheduleJob);
    // 更新Job
    void updateJob(ScheduleJob scheduleJob);
    // 删除Job
    void deleteJob(String jobId);

    // 初始化定时任务
    void initScheduleJob();
    // 立即执行
    void runOnce(String jobId);
    // 暂停Job
    void pauseJob(String jobId);
    // 停止Job
    void stopJob(String jobId);
    // 恢复Job
    void resumeJob(String jobId);
    // 获取运行中的jobs
    List<ScheduleJob> getRunJobs();

    // 启动所有触发器
    void startAllTrigger();
    // 暂停所有触发器
    void pauseAllTrigger();

}
