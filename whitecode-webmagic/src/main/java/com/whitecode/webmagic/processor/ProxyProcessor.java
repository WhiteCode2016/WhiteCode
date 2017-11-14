package com.whitecode.webmagic.processor;

import com.whitecode.entity.ProxyIp;
import com.whitecode.tool.WebmagicUtils;
import com.whitecode.webmagic.pipeline.ProxyPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

/**
 * 爬取可用的IP代理服务器
 * Created by White on 2017/11/13.
 */
public class ProxyProcessor implements PageProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ProxyProcessor.class);
    private Site site = Site.me().setTimeOut(6000).setRetryTimes(3)
            .setSleepTime(1000).setCharset("UTF-8").addHeader("Accept-Encoding", "/")
            .setUserAgent(WebmagicUtils.getRandomUserAgent());

    @Override
    public void process(Page page) {
        List<String> ipList = page.getHtml().xpath("//table[@id='ip_list']/tbody/tr").all();
        List<ProxyIp> proxyIps = new ArrayList<ProxyIp>();
        if (ipList != null && ipList.size() > 0) {
            // 移除标题
            ipList.remove(0);
            for (String tmp : ipList) {
                Html html = Html.create(tmp);
                String[] data = html.xpath("//body//text()").toString().trim().split("\\s+");
                ProxyIp proxyIp = new ProxyIp();
                proxyIp.setIp(data[0]);
                proxyIp.setPort(Integer.valueOf(data[1]));
                proxyIp.setAddr(html.xpath("//a//text()").toString());
                proxyIp.setType(data[3]);
                proxyIp.setOther(data[4]);
                proxyIps.add(proxyIp);
            }
            page.putField("proxyIps",proxyIps);
            page.addTargetRequest("http://www.xicidaili.com/nn/2");
            page.addTargetRequest("http://www.xicidaili.com/nn/3");
            page.addTargetRequest("http://www.xicidaili.com/nn/4");
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        logger.info("开始爬取...");
        long startTime, endTime;
        startTime = System.currentTimeMillis();
        OOSpider.create(new ProxyProcessor())
                .addUrl("http://www.xicidaili.com/nn/1")
                .addPipeline(new ProxyPipeline())
                .thread(3)
                .run();
        endTime = System.currentTimeMillis();
        logger.info("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }
}
