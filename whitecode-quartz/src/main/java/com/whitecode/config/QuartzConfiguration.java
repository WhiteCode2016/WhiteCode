package com.whitecode.config;

import com.whitecode.factory.SpringJobFactory;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Quartz任务调度配置
 * Created by White on 2017/9/8.
 */
@Configuration
public class QuartzConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(QuartzConfiguration.class);

    @Autowired
    private SpringJobFactory springJobFactory;

    @Bean(name = "schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactory(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception {
        logger.info("schedulerFactoryBean 初始化...");
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setDataSource(masterDataSource);
//        factoryBean.setConfigLocation(new ClassPathResource("config/quartz.properties"));
        factoryBean.setQuartzProperties(quartzProperties());
        factoryBean.setSchedulerName("ClusterScheduler");
        factoryBean.setJobFactory(springJobFactory);
        // 延时启动
        factoryBean.setStartupDelay(5);
        factoryBean.setApplicationContextSchedulerContextKey("applicationContextKey");
        factoryBean.setOverwriteExistingJobs(true);
        // 设置自启动
        factoryBean.setAutoStartup(true);
        return factoryBean;
    }

    /**
     * 加载quartz.properties相关配置
     * @return
     * @throws Exception
     */
    @Bean
    public Properties quartzProperties() throws Exception {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("config/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean(name = "scheduler")
    public Scheduler scheduler(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception {
        return schedulerFactory(masterDataSource).getScheduler();
    }

}
