package com.whitecode.webmagic.processor;

import com.whitecode.entity.ZhiHuUser;
import com.whitecode.webmagic.pipeline.ZhiHuPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 爬取知乎用户信息
 * Created by White on 2017/11/3.
 */
@Service
public class ZhihuProcessor implements PageProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ZhihuProcessor.class);
    private Site site = Site.me().setTimeOut(20000).setRetryTimes(3).setSleepTime(2000).setCharset("UTF-8");

    // https://www.zhihu.com/search?type=people&q=java
    private static final String SEARCH = "https://www\\.zhihu\\.com/search\\?type=people&q=[\\s\\S]+";
    private static final String LIST_USER = "//ul[@class='list users']/li/div/div[@class='body']/div[@class='line']";

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(SEARCH).match()) {
            page.addTargetRequests(page.getHtml().xpath(LIST_USER).links().all());
        } else {
            String zhName = page.getHtml().xpath("//span[@class='ProfileHeader-name']/text()").get();
            String zhAnswers = page.getHtml().xpath("//li[@aria-controls='Profile-answers']/a/span/text()").get();
            String zhAsks = page.getHtml().xpath("//li[@aria-controls='Profile-asks']/a/span/text()").get();
            String zhPosts = page.getHtml().xpath("//li[@aria-controls='Profile-posts']/a/span/text()").get();
            String zhColumns = page.getHtml().xpath("//li[@aria-controls='Profile-columns']/a/span/text()").get();
            String zhPins = page.getHtml().xpath("//li[@aria-controls='Profile-pins']/a/span/text()").get();
            ZhiHuUser zhiHuUser = new ZhiHuUser(zhName,zhAnswers,zhAsks,zhPosts,zhColumns,zhPins);
            page.putField("zhiHuUser",zhiHuUser);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 爬虫启动入口
     * @param processor
     * @param pipeline
     */
    public void start(ZhihuProcessor processor, ZhiHuPipeline pipeline) {
        long startTime, endTime;
        logger.info("开始爬取...");
        startTime = System.currentTimeMillis();
        Spider.create(processor)
                .addUrl("https://www.zhihu.com/search?type=people&q=java")
                .addPipeline(pipeline)
                .thread(5)
                .run();
        endTime = System.currentTimeMillis();
        logger.info("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }

}
