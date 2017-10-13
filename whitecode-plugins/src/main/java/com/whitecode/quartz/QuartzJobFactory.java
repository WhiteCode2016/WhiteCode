package com.whitecode.quartz;

import com.whitecode.common.WhiteContants;
import com.whitecode.quartz.model.ScheduleJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务运行工厂
 * 默认：异步
 * Created by White on 2017/10/11.
 */
public class QuartzJobFactory implements Job {
    private static final Logger logger = LoggerFactory.getLogger(QuartzJobFactory.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        ScheduleJob scheduleJob = (ScheduleJob) context.getJobDetail().getJobDataMap().get("scheduleJob");
//        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(WhiteContants.QUARTZ_PARAM_KEY);
//        logger.info("任务名称 = [" + scheduleJob.getJobName() + "]");
        logger.info(context.getMergedJobDataMap().get(WhiteContants.QUARTZ_PARAM_KEY).toString());
    }
}

