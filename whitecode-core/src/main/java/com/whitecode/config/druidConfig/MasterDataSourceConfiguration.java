package com.whitecode.config.druidConfig;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.github.pagehelper.PageHelper;
import com.whitecode.common.WhiteContants;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * 主数据源配置
 * Created by White on 2017/9/8.
 */
@Configuration
public class MasterDataSourceConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(MasterDataSourceConfiguration.class);

    /**
     * 配置数据源
     * @return
     */
    @Bean(name = "masterDataSource")
    @Primary // 如果在多个同类 Bean 候选时，该Bean优先被考虑。（标识主数据源）
    @ConfigurationProperties("spring.datasource")
    public DataSource masterDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置SqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置mybatis-config路径
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(WhiteContants.MASTER_CONFIG_LOCATION));
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(masterDataSource());
        // 设置typeAlias包扫描路径
        sqlSessionFactoryBean.setTypeAliasesPackage(WhiteContants.MASTER_TYPE_ALIAS);
        // 设置mapper文件路径
        sqlSessionFactoryBean.setMapperLocations( new PathMatchingResourcePatternResolver()
                .getResources(WhiteContants.MASTER_MAPPER_LOCATIONS));
        // 添加分页插件PageHelper
        Interceptor[] plugins = new Interceptor[]{ pageHelper() };
        sqlSessionFactoryBean.setPlugins(plugins);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事务管理器
     * @param masterDataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception {
        return new DataSourceTransactionManager(masterDataSource);
    }

    /**
     * PageHelper分页插件配置
     * @return
     */
    @Bean
    public PageHelper pageHelper() {
        logger.info("加载分页插件PageHelper...");
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
