package com.whitecode.webmagic.controller;

import com.whitecode.webmagic.pipeline.ZhiHuPipeline;
import com.whitecode.webmagic.processor.ZhihuProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import us.codecraft.webmagic.Spider;

/**
 * Created by White on 2017/11/3.
 */
@Controller
@RequestMapping("/common/zhihu")
public class ZhiHuController {
    @RequestMapping("/start")
    public void start() {
        Spider.create(new ZhihuProcessor())
                .addUrl("https://www.zhihu.com/search?type=people&q=java")
                .addPipeline(new ZhiHuPipeline())
                .thread(1)
                .run();
    }

}
