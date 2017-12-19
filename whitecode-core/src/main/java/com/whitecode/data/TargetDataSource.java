package com.whitecode.data;

import com.whitecode.enums.DbType;

import java.lang.annotation.*;

/**
 * 目标数据源注解，注解在方法上指定数据源的名称
 * Created by White on 2017/12/18.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    // 数据源的名称
    DbType value();
}
