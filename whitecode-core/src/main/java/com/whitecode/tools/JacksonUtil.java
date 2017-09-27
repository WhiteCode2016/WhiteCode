package com.whitecode.tools;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Jackson 工具类
 * Created by White on 2017/9/27.
 */
public class JacksonUtil {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 把javaBean,list,array 转换为json字符串
     */
    public static String obj2json(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }
}
