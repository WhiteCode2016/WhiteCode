package com.whitecode.data;

import com.whitecode.enums.DbType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据源AOP切面定义 ====>@TargetDataSource(DbType.MASTER)
 * Created by White on 2017/12/18.
 */
@Component
@Aspect
@Order(-1) //保证AOP在@Transactional之前执行
public class DynamicDataSourceAspect {

    @Before("@annotation(TargetDataSource)")
    public void changeDataSource(JoinPoint point) {
        // 获取当前访问的class
        Class<?> className = point.getTarget().getClass();
        // 获取访问的方法名
        String methodName = point.getSignature().getName();
        DbType dataSource = DbType.MASTER;
        try {
            // 得到访问方法的对象
            Method method = className.getMethod(methodName,TargetDataSource.class);
            if (method.isAnnotationPresent(TargetDataSource.class)) {
                TargetDataSource annotation = method.getAnnotation(TargetDataSource.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
            }
        }catch (Exception  e) {
            e.printStackTrace();
        }
        DataSourceContextHolder.setDataSourceType(dataSource);
    }

    @After("@annotation(TargetDataSource)")
    public void AfterSwitchDataSource(JoinPoint point) {
        // 清除
        DataSourceContextHolder.clearDataSourceType();
    }
}


