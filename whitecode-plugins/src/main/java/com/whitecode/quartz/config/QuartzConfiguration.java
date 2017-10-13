/*
package com.whitecode.quartz;

import com.whitecode.config.DruidConfiguration;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

*/
/**
 * Created by White on 2017/10/10.
 *//*

@Configuration
public class QuartzConfiguration {
    */
/*//*
/ 配置定时任务
    @Bean(name = "jobDetailFactoryBean")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(TestJob task) {// ScheduleTask为需要执行的任务
        MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        *//*
*/
/*
         *  是否并发执行
         *  例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
         *  如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
         *//*
*/
/*
        jobDetailFactoryBean.setConcurrent(false);
        // 设置任务的名字
        jobDetailFactoryBean.setName("quartz_test");
        // 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用
        jobDetailFactoryBean.setGroup("test");

        jobDetailFactoryBean.setTargetObject(task);
        jobDetailFactoryBean.setTargetMethod("sayHello");
        return jobDetailFactoryBean;
    }

    // 配置定时任务的触发器，也就是什么时候触发执行定时任务
    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean jobDetailFactoryBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(jobDetailFactoryBean.getObject());
        // 每5秒执行一次
        tigger.setCronExpression("0/5 * * * * ? ");
        return tigger;
    }

    // 定义quartz调度工厂
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        bean.setOverwriteExistingJobs(true);
        // 延时启动，应用启动1秒后
        bean.setStartupDelay(1);
        // 注册触发器
        bean.setTriggers(cronJobTrigger);
        return bean;
    }*//*


    @Bean(name = "schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactory() throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        // 延时启动
        schedulerFactoryBean.setStartupDelay(3);
//        schedulerFactoryBean.setAutoStartup(false);
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        return schedulerFactoryBean;
    }


    */
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

}
*/
