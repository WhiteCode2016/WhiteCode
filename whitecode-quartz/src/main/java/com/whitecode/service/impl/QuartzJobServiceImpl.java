package com.whitecode.service.impl;

import com.whitecode.QuartzManager;
import com.whitecode.dao.mapper.QuartzJobMapper;
import com.whitecode.entity.ScheduleJob;
import com.whitecode.service.QuartzJobService;
import org.apache.commons.collections4.CollectionUtils;
import org.quartz.CronTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuartzJobServiceImpl implements QuartzJobService {
    private static final Logger logger = LoggerFactory.getLogger(QuartzJobServiceImpl.class);

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @Autowired
    private QuartzManager quartzManager;

    @Override
    public List<ScheduleJob> getAllJobs() {
        return quartzManager.getAllJobDetail();
    }

    @Override
    public ScheduleJob getScheduleJobById(String jobId) {
        return quartzJobMapper.getJobById(jobId);
    }

    @Override
    public void insertJob(ScheduleJob scheduleJob) {
        quartzManager.createScheduleJob(scheduleJob);
        //添加到表task_detail
        quartzJobMapper.insertJob(scheduleJob);
    }

    @Override
    public void updateJob(ScheduleJob scheduleJob) {
        quartzManager.updateScheduleJob(scheduleJob);
        //更新表task_detail
        quartzJobMapper.updateJob(scheduleJob);
    }

    @Override
    public void deleteJob(String jobId) {
        ScheduleJob scheduleJob = quartzJobMapper.getJobById(jobId);
        // 先删除运行的任务
        quartzManager.deleteScheduleJob(scheduleJob.getJobName(),scheduleJob.getJobGroup());
        // 再删除表task_detail中的数据
        quartzJobMapper.deleteJob(jobId);
    }

    @Override
    public void initScheduleJob() {
        // 获取所有的jobs
        List<ScheduleJob> scheduleJobList = quartzJobMapper.getAllJobs();
        if (!CollectionUtils.isEmpty(scheduleJobList)) {
            for (ScheduleJob scheduleJob : scheduleJobList) {
                CronTrigger cronTrigger = quartzManager.getCronTrigger(scheduleJob.getJobName(), scheduleJob.getJobGroup());
                if (cronTrigger == null) {
                    // 不存在，创建一个
                    quartzManager.createScheduleJob(scheduleJob);
                } else {
                    // 已存在，那么更新相应的定时设置
                    quartzManager.updateScheduleJob(scheduleJob);
                }
            }
        }
    }

    @Override
    public void runOnce(String jobId) {
        ScheduleJob scheduleJob = getScheduleJobById(jobId);
        quartzManager.runOnce(scheduleJob.getJobName(),scheduleJob.getJobGroup());
    }

    @Override
    public void pauseJob(String jobId) {
        ScheduleJob scheduleJob = getScheduleJobById(jobId);
        quartzManager.pauseJob(scheduleJob.getJobName(),scheduleJob.getJobGroup());
    }

    @Override
    public void stopJob(String jobId) {
        ScheduleJob scheduleJob = getScheduleJobById(jobId);
        quartzManager.unscheduleJob(scheduleJob.getJobName(),scheduleJob.getJobGroup());
    }

    @Override
    public void resumeJob(String jobId) {
        ScheduleJob scheduleJob = getScheduleJobById(jobId);
        quartzManager.resumeJob(scheduleJob.getJobName(),scheduleJob.getJobGroup());
    }

    @Override
    public List<ScheduleJob> getRunJobs() {
        return quartzManager.getAllRunJobDetail();
    }

    @Override
    public void startAllTrigger() {
        quartzManager.startAllTrigger();
    }

    @Override
    public void pauseAllTrigger() {
        quartzManager.pauseAllTrigger();
    }

}
