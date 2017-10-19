package com.whitecode.quartz.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Quartz任务调度配置
 * Created by White on 2017/9/8.
 */
@Configuration
public class QuartzConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(QuartzConfiguration.class);

    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(DataSource dataSource) throws IOException {
        logger.info("schedulerFactoryBean init...");
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfigLocation(new ClassPathResource("quartz.properties"));
        factoryBean.setSchedulerName("ClusterScheduler");
        // 延时启动
        factoryBean.setStartupDelay(5);
        factoryBean.setApplicationContextSchedulerContextKey("applicationContextKey");
        factoryBean.setOverwriteExistingJobs(true);
        factoryBean.setAutoStartup(true);
        return factoryBean;
    }

    /**
     * 加载quartz数据源配置
     * @return
     * @throws IOException
     *//*
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
    */
}
