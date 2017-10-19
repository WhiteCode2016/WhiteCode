package com.whitecode.quartz;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 同步工厂任务
 * Created by White on 2017/10/11.
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SyncQuartzJobFactory implements Job {
    private static final Logger logger = LoggerFactory.getLogger(SyncQuartzJobFactory.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(WhiteContants.QUARTZ_PARAM_KEY);
//        logger.info("任务名称 = [" + scheduleJob.getJobName() + "]");
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        logger.info(context.getJobDetail().getKey().getName());
        for (Map.Entry entry : jobDataMap.entrySet()) {
            logger.info("key---: " + entry.getKey() + "value---: "+ entry.getValue());
        }
    }
}

