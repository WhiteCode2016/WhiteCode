package com.whitecode.controller;

import com.whitecode.entity.ZhiHuUser;
import com.whitecode.entity.ZhiHuUserInfo;
import com.whitecode.service.ZhiHuService;
import com.whitecode.webmagic.pipeline.ZhiHuPipeline;
import com.whitecode.webmagic.pipeline.ZhiHuPipelineTest;
import com.whitecode.webmagic.processor.ZhihuProcessor;
import com.whitecode.webmagic.processor.ZhihuProcessorTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * Created by White on 2017/11/3.
 */
@Controller
@RequestMapping("/common/zhihu")
public class ZhiHuController {
    private static final Logger logger = LoggerFactory.getLogger(ZhiHuController.class);

    @Autowired
    private ZhihuProcessor zhihuProcessor;
    @Autowired
    private ZhiHuPipeline zhiHuPipeline;
    @Autowired
    private ZhiHuService zhiHuService;

    @RequestMapping(value = "/start",method = RequestMethod.GET)
    public String start() {
        new Thread(() -> zhihuProcessor.start(zhihuProcessor,zhiHuPipeline)).start();
        return "/webmagic/webmagic_start";
    }

    @RequestMapping("/getZhiHuData")
    @ResponseBody
    public List<ZhiHuUser> getZhiHuData() {
        return zhiHuService.getZhiHuUsers();
    }

    //--------------------------- View -------------------------------

    @RequestMapping(value = "/webmagicStart",method = RequestMethod.GET)
    public String webmagicStart() {
        return "/webmagic/webmagic_start";
    }

    @RequestMapping(value = "/showDetail",method = RequestMethod.GET)
    public String showDetail() {
        return "/webmagic/show_detail";
    }



    @Autowired
    private ZhihuProcessorTest zhihuProcessorTest;
    @Autowired
    private ZhiHuPipelineTest zhiHuPipelineTest;

    @RequestMapping(value = "/startTest",method = RequestMethod.GET)
    public String startTest() {
//        new Thread(() -> zhihuProcessorTest.start(zhihuProcessorTest,zhiHuPipelineTest)).start();
        zhihuProcessorTest.start(zhihuProcessorTest,zhiHuPipelineTest);
        return "/webmagic/webmagic_start";
    }

    @RequestMapping(value = "/stopTest",method = RequestMethod.GET)
    public String stopTest() {
//        new Thread(() -> zhihuProcessorTest.start(zhihuProcessorTest,zhiHuPipelineTest)).start();
        zhihuProcessorTest.stop(zhihuProcessorTest);
        return "/webmagic/webmagic_start";
    }

    @RequestMapping(value = "/getZhiHuInfoByAnswerCountTop10")
    @ResponseBody
    public List<ZhiHuUserInfo> getZhiHuInfoByAnswerCountTop10() {
        return zhiHuService.getZhiUserInfoByAnswerCountTop10();
    }
    @RequestMapping(value = "/getZhiHuInfoByArticlesCountTop10")
    @ResponseBody
    public List<ZhiHuUserInfo> getZhiHuInfoByArticlesCountTop10() {
        return zhiHuService.getZhiUserInfoByArticlesCountTop10();
    }
    @RequestMapping(value = "/getZhiHuInfoByFollowerCountTop10")
    @ResponseBody
    public List<ZhiHuUserInfo> getZhiHuInfoByFollowerCountTop10() {
        return zhiHuService.getZhiUserInfoByFollowerCountTop10();
    }
}
