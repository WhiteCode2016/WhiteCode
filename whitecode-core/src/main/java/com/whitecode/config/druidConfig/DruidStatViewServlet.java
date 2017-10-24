package com.whitecode.config.druidConfig;

import com.alibaba.druid.support.http.StatViewServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Druid数据源状态监控
 * 监控地址：http://localhost:8080/druid/index.html
 * Created by White on 2017/10/24.
 */
@WebServlet(urlPatterns = "/druid/*",
        initParams={
             /*   // IP白名单 (没有配置或者为空，则允许所有访问)
                @WebInitParam(name="allow",value="192.168.16.110,127.0.0.1"),
                // IP黑名单 (存在共同时，deny优先于allow)
                @WebInitParam(name="deny",value="192.168.16.111"),*/
                // 用户名
                @WebInitParam(name="loginUsername",value="admin"),
                // 密码
                @WebInitParam(name="loginPassword",value="123"),
                // 是否能够重置数据
                @WebInitParam(name="resetEnable",value="false")
        })
public class DruidStatViewServlet extends StatViewServlet {

}
