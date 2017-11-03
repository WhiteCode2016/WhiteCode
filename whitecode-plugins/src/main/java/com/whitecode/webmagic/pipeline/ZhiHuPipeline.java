package com.whitecode.webmagic.pipeline;

import com.whitecode.webmagic.model.ZhiHuUser;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import java.util.Map;

/**
 * Created by White on 2017/11/3.
 */
public class ZhiHuPipeline implements Pipeline{

    @Override
    public void process(ResultItems resultItems, Task task) {
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            if (entry.getKey().equals("zhiHuUser")) {
                ZhiHuUser zhiHuUser = (ZhiHuUser) entry.getValue();
                System.out.println(zhiHuUser);
            }
        }
    }
}
