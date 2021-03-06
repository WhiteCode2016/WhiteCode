package com.whitecode;

import com.whitecode.service.QuartzJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 初始化任务调度
 * Created by White on 2017/10/13.
 */
@Component
public class ScheduleJobInit {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleJobInit.class);

    @Autowired
    private QuartzJobService quartzJobService;

    // 项目启动时，Job任务初始化
    @PostConstruct
    public void init() {
        if (logger.isInfoEnabled()) {
            logger.info("Job初始化开始...");
        }

        quartzJobService.initScheduleJob();

        if (logger.isInfoEnabled()) {
            logger.info("Job初始化结束...");
        }
    }
}
