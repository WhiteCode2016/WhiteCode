package com.whitecode.webmagic.processor;

import com.whitecode.webmagic.model.Weather2Day;
import com.whitecode.webmagic.model.WeatherDetail;
import com.whitecode.webmagic.pipeline.WheatherPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * 天气爬虫
 * Created by White on 2017/9/29.
 */
public class WeatherProcessor implements PageProcessor{
    private static final Logger logger = LoggerFactory.getLogger(WeatherProcessor.class);
    private Site site = Site.me().setTimeOut(20000).setRetryTimes(3).setSleepTime(2000).setCharset("UTF-8");

    @Override
    public void process(Page page) {
        // 最近七天天气
        Selectable sevenStr = page.getHtml().xpath("//div[@id='7d']/ul[@class='t clearfix']");
        // 分时段天气
        Selectable hourStr = page.getHtml().xpath("//div[@id='7d']/script");

        WeatherDetail weather = new WeatherDetail();
        weather.setWeather2Days(_handleSevenDays(sevenStr));

        page.putField("weather", weather);
        page.putField("stationCode", page.getUrl().regex("(\\d+).shtml",1));
    }

    @Override
    public Site getSite() {
        return site;
    }

    // 封装最近7天天气情况
    private List<Weather2Day> _handleSevenDays(Selectable sevenStr) {
        List<String> sevenDays = sevenStr.xpath("//ul[@class='t clearfix']/li").all();
        List<Weather2Day> result = new ArrayList<>();
        for (String day : sevenDays) {
            Html temp = Html.create(day);
            Weather2Day weather2Day = new Weather2Day();
            weather2Day.setDateTime(temp.xpath("//h1/text()").toString());
            weather2Day.setWea(temp.xpath("//p[@class='wea']/text()").toString());
            weather2Day.setMaxTemperature(temp.xpath("//p[@class='tem']/span/text()").toString());
            weather2Day.setMinTemperature(temp.xpath("//p[@class='tem']/i/text()").toString());
            weather2Day.setSeries(temp.xpath("//p[@class='win']/i/text()").toString());
            result.add(weather2Day);
        }
        return result;
    }

    public static void main(String[] args) {
        long startTime, endTime;
        logger.info("开始爬取...");
        startTime = System.currentTimeMillis();
        Spider.create(new WeatherProcessor())
                .addUrl("http://www.weather.com.cn/weather/101060101.shtml")
                .addPipeline(new WheatherPipeline())
                .thread(1)
                .run();
        endTime = System.currentTimeMillis();
        logger.info("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }
}
