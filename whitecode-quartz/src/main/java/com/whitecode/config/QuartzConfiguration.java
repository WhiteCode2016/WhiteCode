package com.whitecode.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Quartz任务调度配置
 * Created by White on 2017/9/8.
 */
@Configuration
@EnableScheduling
public class QuartzConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(QuartzConfiguration.class);

    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(@Qualifier("masterDataSource") DataSource masterDataSource) throws IOException {
        logger.info("schedulerFactoryBean 初始化...");
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setDataSource(masterDataSource);
        factoryBean.setConfigLocation(new ClassPathResource("config/quartz.properties"));
        factoryBean.setSchedulerName("ClusterScheduler");
        // 延时启动
        factoryBean.setStartupDelay(5);
        factoryBean.setApplicationContextSchedulerContextKey("applicationContextKey");
        factoryBean.setOverwriteExistingJobs(true);
        // 设置自启动
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
