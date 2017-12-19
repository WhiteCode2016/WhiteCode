package com.whitecode.data;

import com.whitecode.enums.DbType;

/**
 * Created by White on 2017/12/18.
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<DbType> DATA_SOURCE_CONTEXT = new ThreadLocal<DbType>();

    // 设置数据源类型
    public static void setDataSourceType(DbType dataSourceType) {
        DATA_SOURCE_CONTEXT.set(dataSourceType);
    }

    // 获取数据源类型
    public static DbType getDataSourceType() {
        return DATA_SOURCE_CONTEXT.get();
    }

    // 清除数据源类型
    public static void clearDataSourceType() {
        DATA_SOURCE_CONTEXT.remove();
    }
}
