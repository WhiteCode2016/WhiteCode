package com.whitecode.config;

import com.whitecode.data.DynamicDataSource;
import com.whitecode.enums.DbType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by White on 2017/12/19.
 */
@Configuration
public class DynamicDataSourceConfiguration {
    @Resource
    private DataSource masterDataSource;

    @Bean
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object,Object> map = new HashMap<Object, Object>();
        map.put(DbType.MASTER,masterDataSource);
        dynamicDataSource.setTargetDataSources(map);
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        return dynamicDataSource;
    }
}
