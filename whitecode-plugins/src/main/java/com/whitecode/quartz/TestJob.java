package com.whitecode.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by White on 2017/10/10.
 */
@Configuration
@Component
@EnableScheduling
public class TestJob {
    private static final Logger logger = LoggerFactory.getLogger(TestJob.class);

    public void sayHello() {
        logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "执行ExampleJob的定时任务");
    }
}
