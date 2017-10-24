package com.whitecode.config.druidConfig;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;

/**
 * 主数据源配置
 * Created by White on 2017/9/8.
 */
@Configuration
public class MasterDataSourceConfiguration {
    private static final String TYPE_ALIAS = "com.whitecode.entity";
    private static final String CONFIG_LOCATION = "config/mybatis-config.xml";
    private static final String MAPPER_LOCATIONS = "mybatis/*.xml";

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
    public SqlSessionFactory masterSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置mybatis-config路径
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(CONFIG_LOCATION));
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(masterDataSource());
        // 设置typeAlias包扫描路径
        sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_ALIAS);
        // 设置mapper文件路径
        sqlSessionFactoryBean.setMapperLocations( new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATIONS));
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事务管理器
     * @param masterDataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception {
        return new DataSourceTransactionManager(masterDataSource);
    }
}
