package com.whitecode.tools;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * Jackson 工具类
 * Created by White on 2017/9/27.
 */
public class JacksonUtils {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 把javaBean,list,array 转换为json字符串
     * @param obj
     * @return
     * @throws Exception
     */
    public static String obj2json(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * 将json字符串转换成Map对象
     * @param json
     * @return
     * @throws Exception
     */
    public static Map<String,Object> json2Map(String json) throws Exception {
        return objectMapper.readValue(json,Map.class);
    }
}
