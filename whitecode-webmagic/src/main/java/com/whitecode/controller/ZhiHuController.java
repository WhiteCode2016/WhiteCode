package com.whitecode.controller;

import com.whitecode.entity.ZhiHuUser;
import com.whitecode.service.ZhiHuService;
import com.whitecode.webmagic.pipeline.ZhiHuPipeline;
import com.whitecode.webmagic.processor.ZhihuProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * Created by White on 2017/11/3.
 */
@Controller
@RequestMapping("/common/zhihu")
public class ZhiHuController {

    @Autowired
    private ZhihuProcessor zhihuProcessor;
    @Autowired
    private ZhiHuPipeline zhiHuPipeline;
    @Autowired
    private ZhiHuService zhiHuService;

    @RequestMapping("/start")
    public String start() {
       new Thread(() -> zhihuProcessor.start(zhihuProcessor,zhiHuPipeline)).start();
       return "start";
    }

    @RequestMapping("/showDetail")
    public String showDetail() {
        return "/webmagic/show_detail";
    }

    @RequestMapping("/getZhiHuData")
    @ResponseBody
    public List<ZhiHuUser> getZhiHuData() {
        return zhiHuService.getZhiHuUsers();
    }
}
