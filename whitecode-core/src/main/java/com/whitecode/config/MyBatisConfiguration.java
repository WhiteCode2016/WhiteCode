package com.whitecode.config;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Properties;

/**
 * MyBatis 配置
 * Created by White on 2017/9/12.
 */
@Configuration
//@MapperScan("com.whitecode.dao.mapper")
public class MyBatisConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(MyBatisConfiguration.class);

    /**
     * PageHelper分页插件配置
     * @return
     */
    @Bean
    public PageHelper pageHelper() {
        logger.info("注册MyBatis分页插件PageHelper");
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        // 通过设置pageSize=0或者RowBounds.limit = 0就会查询出全部的结果。
        properties.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
