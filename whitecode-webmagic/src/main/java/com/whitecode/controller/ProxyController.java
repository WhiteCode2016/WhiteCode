package com.whitecode.controller;

import com.whitecode.webmagic.pipeline.ProxyPipeline;
import com.whitecode.webmagic.processor.ProxyProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import us.codecraft.webmagic.model.OOSpider;

/**
 * Created by White on 2017/11/13.
 */
@Controller
@RequestMapping("common/proxy")
public class ProxyController {
    @Autowired
    private ProxyPipeline proxyPipeline;

    @RequestMapping(value = "/start",method = RequestMethod.GET)
    public ResponseEntity start() {
        OOSpider.create(new ProxyProcessor())
                .addPipeline(proxyPipeline)
                .addUrl("http://www.xicidaili.com/nn/1")
                .thread(3)
                .start();
        return new ResponseEntity(HttpStatus.OK);
    }
}
