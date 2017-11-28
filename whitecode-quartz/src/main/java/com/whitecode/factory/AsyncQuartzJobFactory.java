package com.whitecode.factory;

import com.whitecode.dao.mapper.QuartzJobMapper;
import com.whitecode.service.QuartzJobService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 异步工厂任务
 * Created by White on 2017/10/19.
 */
public class AsyncQuartzJobFactory implements Job {
    private static final Logger logger = LoggerFactory.getLogger(AsyncQuartzJobFactory.class);

    @Autowired
    private QuartzJobService quartzJobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        /*JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        logger.info(context.getJobDetail().getKey().getName());
        for (Map.Entry entry : jobDataMap.entrySet()) {
            logger.info("key---: " + entry.getKey() + "value---: "+ entry.getValue());
        }*/
        System.out.println(quartzJobService.getScheduleJobById("1").toString());
    }
}
