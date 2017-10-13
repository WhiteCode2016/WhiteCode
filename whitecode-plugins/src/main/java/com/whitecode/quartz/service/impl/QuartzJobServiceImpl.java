package com.whitecode.quartz.service.impl;

import com.whitecode.quartz.tool.QuartzUtil;
import com.whitecode.quartz.dao.mapper.QuartzJobMapper;
import com.whitecode.quartz.model.ScheduleJob;
import com.whitecode.quartz.service.QuartzJobService;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by White on 2017/10/11.
 */
@Service
public class QuartzJobServiceImpl implements QuartzJobService {
    @Resource
    private QuartzJobMapper quartzJobMapper;
    @Resource(name = "schedulerFactoryBean")
    private Scheduler scheduler;

    @Override
    public List<ScheduleJob> getAllJobs() {
        return quartzJobMapper.getAllJobs();
    }

    @Override
    public ScheduleJob getScheduleJobById(int jobId) {
        return quartzJobMapper.getJobById(jobId);
    }

    @Override
    public void insertJob(ScheduleJob scheduleJob) {
        QuartzUtil.createScheduleJob(scheduler,scheduleJob);
        //添加到表task_detail

    }

    @Override
    public void updateJob(ScheduleJob scheduleJob) {
        QuartzUtil.updateScheduleJob(scheduler,scheduleJob);
        //更新表task_detail

    }

    @Override
    public void deleteJob(int jobId) {
        ScheduleJob scheduleJob = quartzJobMapper.getJobById(jobId);
        // 先删除运行的任务
        QuartzUtil.deleteScheduleJob(scheduler,scheduleJob.getJobName(),scheduleJob.getJobGroup());
        // 再删除表task_detail中的数据

    }

    @Override
    public void initScheduleJob() {
        List<ScheduleJob> scheduleJobList = quartzJobMapper.getAllJobs();
        if (!CollectionUtils.isEmpty(scheduleJobList)) {
            for (ScheduleJob scheduleJob : scheduleJobList) {
//                if (scheduleJob.getJobStatus().equals("1")) {
                    CronTrigger cronTrigger = QuartzUtil.getCronTrigger(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
                    // 不存在，创建一个
                    if (null == cronTrigger) {
                        QuartzUtil.createScheduleJob(scheduler, scheduleJob);
                    } else {
                        QuartzUtil.updateScheduleJob(scheduler, scheduleJob);
                    }
//                }
            }
        }
    }

    @Override
    public void runOnce(int jobId) {
        ScheduleJob scheduleJob = getScheduleJobById(jobId);
        QuartzUtil.runOnce(scheduler,scheduleJob.getJobName(),scheduleJob.getJobGroup());
    }

    @Override
    public void pauseJob(int jobId) {
        ScheduleJob scheduleJob = getScheduleJobById(jobId);
        QuartzUtil.pauseJob(scheduler,scheduleJob.getJobName(),scheduleJob.getJobGroup());
    }

    @Override
    public void resumeJob(int jobId) {
        ScheduleJob scheduleJob = getScheduleJobById(jobId);
        QuartzUtil.resumeJob(scheduler,scheduleJob.getJobName(),scheduleJob.getJobGroup());
    }
}
