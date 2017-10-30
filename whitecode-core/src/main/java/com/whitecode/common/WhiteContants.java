package com.whitecode.common;

/**
 * Created by White on 2017/9/26.
 */
public abstract class WhiteContants {
    // 系统邮箱地址
    public static final String SYSTEM_MAIL = "18855971263@163.com";
    // 邮箱链接地址前缀
    public static final String SYSTEM_MAIL_LINK_PREFIX = "http://localhost:8080/common/password/resetPassword";

    // 主数据源的资源
    public static final String MASTER_TYPE_ALIAS = "com.whitecode.entity";
    public static final String MASTER_CONFIG_LOCATION = "config/mybatis-config.xml";
    public static final String MASTER_MAPPER_LOCATIONS = "mybatis/*.xml";
}
