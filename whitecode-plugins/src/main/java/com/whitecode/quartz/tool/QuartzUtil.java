package com.whitecode.quartz.tool;

import com.whitecode.common.WhiteContants;
import com.whitecode.quartz.QuartzJobFactory;
import com.whitecode.quartz.model.ScheduleJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时任务工具类
 * Created by White on 2017/10/12.
 */
public class QuartzUtil {
    private static final Logger logger = LoggerFactory.getLogger(QuartzUtil.class);

    /**
     * 获取触发器key
     * @param jobName
     * @param jobGroup
     * @return
     */
    public static TriggerKey getTriggerKey(String jobName, String jobGroup) {
        return TriggerKey.triggerKey(jobName, jobGroup);
    }

    /**
     * 获取表达式触发器
     * @param scheduler
     * @param jobName
     * @param jobGroup
     * @return
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, String jobName, String jobGroup) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            return (CronTrigger) scheduler.getTrigger(triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取jobKey
     * @param jobName
     * @param jobGroup
     * @return
     */
    public static JobKey getJobKey(String jobName, String jobGroup) {
        return JobKey.jobKey(jobName, jobGroup);
    }

    /**
     * 创建定时任务
     * @param scheduler
     * @param scheduleJob
     */
    public static void createScheduleJob(Scheduler scheduler, ScheduleJob scheduleJob) {
        // 构建job信息
        JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                .withIdentity(scheduleJob.getJobName(),scheduleJob.getJobGroup())
                .withDescription(scheduleJob.getJobDesc())
                .build();
        // 自定义参数
        jobDetail.getJobDataMap().put(WhiteContants.QUARTZ_PARAM_KEY, scheduleJob);

        // 表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
        // 按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(scheduleJob.getJobName(),scheduleJob.getJobGroup())
                .withSchedule(scheduleBuilder)
                .build();
        try {
            scheduler.scheduleJob(jobDetail,trigger);
        } catch (SchedulerException e) {
            logger.error("创建定时任务失败",e);
            e.printStackTrace();
        }
    }

    /**
     * 更新定时任务
     * @param scheduler
     * @param scheduleJob
     */
    public static void updateScheduleJob(Scheduler scheduler, ScheduleJob scheduleJob) {
        try {
            TriggerKey triggerKey = getTriggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder()
                    .withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder)
                    .build();
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 运行一次任务
     * @param scheduler
     * @param jobName
     * @param jobGroup
     */
    public static void runOnce(Scheduler scheduler, String jobName, String jobGroup) {
        JobKey jobKey = getJobKey(jobName, jobGroup);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            logger.error("运行一次定时任务失败",e);
            e.printStackTrace();
        }
    }

    /**
     * 暂停任务
     * @param scheduler
     * @param jobName
     * @param jobGroup
     */
    public static void pauseJob(Scheduler scheduler, String jobName, String jobGroup) {

        JobKey jobKey = getJobKey(jobName, jobGroup);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复任务
     * @param scheduler
     * @param jobName
     * @param jobGroup
     */
    public static void resumeJob(Scheduler scheduler, String jobName, String jobGroup) {

        JobKey jobKey = getJobKey(jobName, jobGroup);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除定时任务
     * @param scheduler
     * @param jobName
     * @param jobGroup
     */
    public static void deleteScheduleJob(Scheduler scheduler, String jobName, String jobGroup) {
        JobKey jobKey = getJobKey(jobName, jobGroup);
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
