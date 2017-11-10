package com.whitecode.webmagic.processor;

import com.whitecode.entity.ZhiHuUserInfo;
import com.whitecode.tool.WebmagicUtils;
import com.whitecode.tools.JacksonUtils;
import com.whitecode.webmagic.pipeline.ZhiHuPipelineTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ZhihuProcessorTest implements PageProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ZhihuProcessorTest.class);
    private Site site = Site.me().setTimeOut(20000).setRetryTimes(3).setSleepTime(2000).setCharset("UTF-8")
            .addHeader("Accept-Encoding","/")
            .addHeader("authorization","Bearer 2|1:0|10:1510192359|4:z_c0|92:Mi4xTC1mYkF3QUFBQUFBTUlJd3F5bWtEQ1lBQUFCZ0FsVk41d0x4V2dDN2lqemgtXzRDcUZVWHI3U2pBWVlUSDVQSW5n|f9558ed9d84bb5a72174c4d1684ead0a80eb9a1c56e92ee04e2ab71eb132fc4c")
            .setUserAgent(WebmagicUtils.getRandomUserAgent());

    public final static String USER_FOLLOWEES_URL = "https://www\\.zhihu\\.com/api/v4/members/%s/followees*";
    public final static String USER_URL = "https://www\\.zhihu\\.com/people/%s/activities";
    public final static String URL_TOKEN = "https://www.zhihu.com/people/{url_token}/activities";

    private long startTime, endTime;

    @Override
    public void process(Page page) {
        List<String> userUrls = new ArrayList<String>();
        try {
            List<String> userFollowees = page.getJson().jsonPath("$.data[*]").all();
            for (String jsonStr : userFollowees) {
                Map map = JacksonUtils.json2Map(jsonStr);
                // 获取关注人url_token,拼接成链接
                String url_token = (String) map.get("url_token");
//                String user_url = URL_TOKEN.replace("{url_token}",url_token);
                String user_followees_url = "https://www.zhihu.com/api/v4/members/"+ url_token +"/followees?" +
                        "include=data[*].answer_count,articles_count,gender,follower_count,is_followed" +
                        ",is_following,badge[?(type=best_answerer)].topics&offset=0&limit=20";
                userUrls.add(user_followees_url);

                String id = (String) map.get("id");
                String name = (String) map.get("name");
                int answerCount = (int) map.get("answer_count");
                int articlesCount = (int) map.get("articles_count");
                int followerCount = (int) map.get("follower_count");
                ZhiHuUserInfo zhiHuUserInfo = new ZhiHuUserInfo(id,name,url_token,answerCount,articlesCount,followerCount);
                page.putField("zhiHuUserInfo",zhiHuUserInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        page.addTargetRequests(userUrls);
    }

    @Override
    public Site getSite() {
        return site;
    }


    /**
     * 获取用户所关注的人的地址
     * @param page
     * @return
     */
    List<String> getTargetUserUrls(Page page) {
        List<String> userUrls = new ArrayList<String>();
        try {
            List<String> userFollowees = page.getJson().jsonPath("$.data[*]").all();
            for (String jsonStr : userFollowees) {
                Map map = JacksonUtils.json2Map(jsonStr);
                // 获取关注人url_token,拼接成链接
                String url_token = (String) map.get("url_token");
                String user_url = URL_TOKEN.replace("{url_token}",url_token);
                userUrls.add(user_url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userUrls;
    }


    public static void main(String[] args) {
        long startTime, endTime;
        logger.info("开始爬取...");
        startTime = System.currentTimeMillis();
        Spider.create(new ZhihuProcessorTest())
                // 设置从哪个url开始爬取
                .addUrl("https://www.zhihu.com/api/v4/members/excited-vczh/followees?" +
                        "include=data[*].answer_count,articles_count,gender,follower_count,is_followed" +
                        ",is_following,badge[?(type=best_answerer)].topics&offset=0&limit=20")
//                    .addUrl("https://www.zhihu.com/people/www.zuidaima.com/activities")
                .thread(5)
                .run();
        endTime = System.currentTimeMillis();
        logger.info("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }

    /**
     * 爬虫启动入口
     * @param processor
     * @param pipeline
     */
    public void start(ZhihuProcessorTest processor, ZhiHuPipelineTest pipeline) {
        logger.info("开始爬取...");
        startTime = System.currentTimeMillis();
        Spider spider = getSpider(processor);
        spider.addUrl("https://www.zhihu.com/api/v4/members/excited-vczh/followees?" +
                "include=data[*].answer_count,articles_count,gender,follower_count,is_followed" +
                ",is_following,badge[?(type=best_answerer)].topics&offset=0&limit=20")
                .addPipeline(pipeline)
                .setScheduler(new FileCacheQueueScheduler("E:/webmagicUrls"))
                .thread(20)
                .start();
    }

    /**
     * 爬虫停止
     * @param processor
     */
    public void stop(ZhihuProcessorTest processor) {
        getSpider(processor).stop();
        endTime = System.currentTimeMillis();
        logger.info("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }

    /**
     * 获取spider
     * @param processor
     * @return
     */
    private Spider getSpider(ZhihuProcessorTest processor) {
        return Spider.create(processor);
    }
}
