package com.whitecode;

import com.whitecode.entity.ScheduleJob;
import com.whitecode.enums.QuartzStatusEnum;
import com.whitecode.factory.AsyncQuartzJobFactory;
import com.whitecode.factory.SyncQuartzJobFactory;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 定时任务工具类
 * Created by White on 2017/10/12.
 */
@Component
public class QuartzManager {
    private static final Logger logger = LoggerFactory.getLogger(QuartzManager.class);

    @Resource(name = "scheduler")
    private Scheduler scheduler;

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
     * @param jobName
     * @param jobGroup
     * @return
     */
    public CronTrigger getCronTrigger(String jobName, String jobGroup) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
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
    public JobKey getJobKey(String jobName, String jobGroup) {
        return JobKey.jobKey(jobName, jobGroup);
    }

    /**
     * 创建定时任务
     * @param scheduleJob
     */
    public void createScheduleJob(ScheduleJob scheduleJob) {
        String jobName = scheduleJob.getJobName();
        String jobGroup = scheduleJob.getJobGroup();
        String cronExpression = scheduleJob.getCronExpression();
        String jobType = scheduleJob.getJobType();

        Class<? extends Job> jobClass = jobType.equals("ASYNC") ? AsyncQuartzJobFactory.class : SyncQuartzJobFactory.class;

        // 构建job信息
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(jobName, jobGroup)
                .storeDurably(true)
                .build();
        // 表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        // 按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName, jobGroup)
                .forJob(jobDetail)
                .withSchedule(scheduleBuilder)
                .build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            logger.error("创建定时任务失败", e);
        }
    }

    /**
     * 更新定时任务
     * @param scheduleJob
     */
    public void updateScheduleJob(ScheduleJob scheduleJob) {
        updateScheduleJob(scheduleJob.getJobName(),scheduleJob.getJobGroup()
                ,scheduleJob.getCronExpression());
    }

    /**
     * 更新定时任务
     * @param jobName
     * @param jobGroup
     * @param cronExpression
     */
    public void updateScheduleJob(String jobName,String jobGroup,String cronExpression) {
        try {
            TriggerKey triggerKey = getTriggerKey(jobName, jobGroup);
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder()
                    .withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder)
                    .build();
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            // 忽略状态为PAUSED的任务，解决集群环境中在其他机器设置定时任务为PAUSED状态后，集群环境启动另一台主机时定时任务全被唤醒的bug
            if(!triggerState.name().equalsIgnoreCase("PAUSED")){
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (SchedulerException e) {
            logger.error("更新定时任务失败", e);
        }
    }

    /**
     * 启动所有触发器
     * @return
     */
    public void startAllTrigger() {
        try {
            if (scheduler.isInStandbyMode()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            logger.error("启动所有触发器失败", e);
            e.printStackTrace();
        }
    }

    /**
     * 暂停所有触发器
     * @return
     */
    public void pauseAllTrigger() {
        try {
            scheduler.standby();
        } catch (SchedulerException e) {
            logger.error("暂停所有触发器失败", e);
            e.printStackTrace();
        }
    }

    /**
     * 运行一次任务（用于测试）
     * @param jobName
     * @param jobGroup
     */
    public void runOnce(String jobName, String jobGroup) {
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
     * @param jobName
     * @param jobGroup
     */
    public void pauseJob(String jobName, String jobGroup) {
        JobKey jobKey = getJobKey(jobName, jobGroup);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复任务
     * @param jobName
     * @param jobGroup
     */
    public void resumeJob(String jobName, String jobGroup) {
        JobKey jobKey = getJobKey(jobName, jobGroup);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除定时任务
     * @param jobName
     * @param jobGroup
     */
    public void deleteScheduleJob(String jobName, String jobGroup) {
        JobKey jobKey = getJobKey(jobName, jobGroup);
        TriggerKey triggerKey = getTriggerKey(jobName,jobGroup);
        try {
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止定时任务
     * @param jobName
     * @param jobGroup
     */
    public void unscheduleJob(String jobName, String jobGroup) {
        TriggerKey triggerKey = getTriggerKey(jobName, jobGroup);
        try {
            scheduler.unscheduleJob(triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从JobStore里获取所有的jobs
     * @return
     */
    public List<ScheduleJob> getAllJobDetail() {
        List<ScheduleJob> result = new LinkedList<ScheduleJob>();
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.jobGroupContains("");
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            for (JobKey jobKey : jobKeys) {
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    ScheduleJob job = new ScheduleJob();
                    job.setJobName(jobKey.getName());
                    job.setJobGroup(jobKey.getGroup());
                    JobDataMap jobDataMap = trigger.getJobDataMap();
                    job.setJobId(jobDataMap.getString("jobId"));
                    job.setJobType(jobDataMap.getString("jobType"));
                    job.setBeanClass(jobDataMap.getString("beanClass"));
                    job.setExecuteMethod(jobDataMap.getString("executeMethod"));
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
//                    job.setJobStatus(triggerState.name());
                    job.setJobStatus(QuartzStatusEnum.valueOf(triggerState.name()));
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger)trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        job.setCronExpression(cronExpression);
                    }
                    job.setJobDesc(jobDetail.getDescription());
                    result.add(job);
                }
            }
        } catch (Exception e) {
            logger.error("获取所有jobStore中Job数据失败 ", e);
        }
        return result;
    }

    /**
     * 从JobStore里获取所有运行的jobs
     * @return
     */
    public List<ScheduleJob> getAllRunJobDetail() {
        List<ScheduleJob> result = new LinkedList<ScheduleJob>();
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.jobGroupContains("");
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            for (JobKey jobKey : jobKeys) {
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    ScheduleJob job = new ScheduleJob();
                    job.setJobName(jobKey.getName());
                    job.setJobGroup(jobKey.getGroup());
                    JobDataMap jobDataMap = trigger.getJobDataMap();
                    job.setJobId(jobDataMap.getString("jobId"));
                    job.setBeanClass(jobDataMap.getString("beanClass"));
                    job.setExecuteMethod(jobDataMap.getString("excuteMethod"));
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
//                    job.setJobStatus(triggerState.name());
                    job.setJobStatus(QuartzStatusEnum.valueOf(triggerState.name()));
                    job.setJobDesc(jobDetail.getDescription());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger)trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        job.setCronExpression(cronExpression);
                    }
                    // 获取正常运行的任务列表
                    if(triggerState.name().equals("NORMAL")) {
                        result.add(job);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取所有jobStore中Job数据失败 ", e);
        }
        return result;
    }
}
