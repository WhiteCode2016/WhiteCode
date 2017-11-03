package com.whitecode.webmagic.processor;

import com.whitecode.webmagic.model.ZhiHuUser;
import com.whitecode.webmagic.pipeline.ZhiHuPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 爬取知乎用户信息
 * Created by White on 2017/11/3.
 */
public class ZhihuProcessor implements PageProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ZhihuProcessor.class);
    private Site site = Site.me().setTimeOut(20000).setRetryTimes(3).setSleepTime(2000).setCharset("UTF-8");

    // https://www.zhihu.com/search?type=people&q=java
    private static String SEARCH = "https://www\\.zhihu\\.com/search\\?type=people&q=[\\s\\S]+";
    private static String LIST_USER = "//ul[@class='list users']/li/div/div[@class='body']/div[@class='line']";

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(SEARCH).match()) {
            page.addTargetRequests(page.getHtml().xpath(LIST_USER).links().all());
        } else {
            String zHName = page.getHtml().xpath("//span[@class='ProfileHeader-name']/text()").get();
            String zHAnswers = page.getHtml().xpath("//li[@aria-controls='Profile-answers']/a/span/text()").get();
            String zHAsks = page.getHtml().xpath("//li[@aria-controls='Profile-asks']/a/span/text()").get();
            String zHPosts = page.getHtml().xpath("//li[@aria-controls='Profile-posts']/a/span/text()").get();
            String zHColumns = page.getHtml().xpath("//li[@aria-controls='Profile-columns']/a/span/text()").get();
            String zHPins = page.getHtml().xpath("//li[@aria-controls='Profile-pins']/a/span/text()").get();
            ZhiHuUser zhiHuUser = new ZhiHuUser(zHName,zHAnswers,zHAsks,zHPosts,zHColumns,zHPins);
            page.putField("zhiHuUser",zhiHuUser);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        long startTime, endTime;
        logger.info("开始爬取...");
        startTime = System.currentTimeMillis();
        Spider.create(new ZhihuProcessor())
                .addUrl("https://www.zhihu.com/search?type=people&q=java")
                .addPipeline(new ZhiHuPipeline())
                .thread(5)
                .run();
        endTime = System.currentTimeMillis();
        logger.info("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }
}
