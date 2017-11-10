package com.whitecode.webmagic.pipeline;

import com.whitecode.entity.ZhiHuUser;
import com.whitecode.entity.ZhiHuUserInfo;
import com.whitecode.service.ZhiHuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

/**
 * 自定义Pipeline，保存知乎用户数据到数据库
 * Created by White on 2017/11/3.
 */
@Service
public class ZhiHuPipelineTest implements Pipeline{
    private static final Logger logger = LoggerFactory.getLogger(ZhiHuPipelineTest.class);

    @Autowired
    private ZhiHuService zhiHuService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            if (entry.getKey().equals("zhiHuUserInfo")) {
                ZhiHuUserInfo zhiHuUserInfo = (ZhiHuUserInfo) entry.getValue();
                logger.info(String.valueOf(zhiHuUserInfo));
                zhiHuService.addZhiHuUserInfo(zhiHuUserInfo);
            }
        }
    }
}
