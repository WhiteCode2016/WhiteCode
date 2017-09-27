package com.whitecode.tools;

import com.whitecode.common.JsonResult;
import com.whitecode.enums.ResultEnum;

/**
 * 自定义JSON结果封装工具类
 * Created by White on 2017/9/21.
 */
public class JsonResultUtil {

    public static JsonResult success(Object object, ResultEnum resultEnum) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(resultEnum.getCode());
        jsonResult.setMessage(resultEnum.getMessage());
        jsonResult.setData(object);
        return jsonResult;
    }

    public static JsonResult success(ResultEnum resultEnum) {
        return success(null,resultEnum);
    }

    public static JsonResult success() {
        return success(ResultEnum.SUCCESS);
    }

    public static JsonResult error(ResultEnum resultEnum) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(resultEnum.getCode());
        jsonResult.setMessage(resultEnum.getMessage());
        return jsonResult;
    }

    public static JsonResult error() {
        return error(ResultEnum.ERROR);
    }
}
