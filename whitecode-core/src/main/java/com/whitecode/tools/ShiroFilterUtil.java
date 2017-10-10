package com.whitecode.tools;

import com.whitecode.common.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Shiro 工具类
 * Created by White on 2017/9/22.
 */
public class ShiroFilterUtil {

    private static final Logger logger = LoggerFactory.getLogger(ShiroFilterUtil.class);

    // 判断是否为ajax请求
    public static boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            logger.info("当前请求为ajax请求");
            return Boolean.TRUE;
        }
        logger.info("当前请求为非ajax请求");
        return Boolean.FALSE;
    }

    // Response 返回Json数据
    public static void out(ServletResponse response, Map<String, String> resultMap){
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");//设置编码
            response.setContentType("application/json");//设置返回类型
            out = response.getWriter();
//            out.println(JSONObject.fromObject(resultMap).toString());//输出
            out.println(JacksonUtil.obj2json(resultMap));
        } catch (Exception e) {
            logger.info("输出JSON出错");
        }finally{
            if(null != out){
                out.flush();
                out.close();
            }
        }
    }

    // Response 返回Json数据
    public static void out(ServletResponse response, JsonResult jsonResult){
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");//设置编码
            response.setContentType("application/json");//设置返回类型
            out = response.getWriter();
//            out.println(JSONObject.fromObject(jsonResult).toString());//输出
            // 使用jackson转化对象,通过@JsonInclude注解方便过滤 NULL字段
            out.println(JacksonUtil.obj2json(jsonResult));
        } catch (Exception e) {
            logger.info("输出JSON出错");
        }finally{
            if(null != out){
                out.flush();
                out.close();
            }
        }
    }
}
