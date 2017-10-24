package com.whitecode.config.druidConfig;

import com.alibaba.druid.support.http.WebStatFilter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Druid过滤器
 * Created by White on 2017/10/24.
 */
@WebFilter(urlPatterns="/*",
        initParams={
                // 忽略资源（过滤的格式）
                @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")
        })
public class DruidStatFilter extends WebStatFilter {

}
