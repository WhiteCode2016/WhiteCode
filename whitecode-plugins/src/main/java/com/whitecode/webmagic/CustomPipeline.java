package com.whitecode.webmagic;

import com.whitecode.tools.JacksonUtils;
import com.whitecode.webmagic.model.WeatherDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 自定义Pipeline，用户来处理获取的天气数据
 * Created by White on 2017/9/29.
 */
public class CustomPipeline implements Pipeline {

    private static final Logger logger = LoggerFactory.getLogger(CustomPipeline.class);

    @Override
    public void process(ResultItems resultItems, Task task) {
        WeatherDetail weather = resultItems.get("weather");
        // 获取城市代码
        String stationCode = resultItems.get("stationCode").toString();
        weather.setId(stationCode);
        try {
            logger.info(JacksonUtils.obj2json(weather));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
