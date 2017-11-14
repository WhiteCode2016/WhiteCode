package com.whitecode.webmagic.pipeline;

import com.whitecode.ProxyUtils;
import com.whitecode.entity.ProxyIp;
import com.whitecode.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Map;

/**
 * 自定义Pipeline，保存可用的IP代理服务器信息到数据库
 * Created by White on 2017/11/13.
 */
@Service
public class ProxyPipeline implements Pipeline {
    @Autowired
    private ProxyService proxyService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            if (entry.getKey().equals("proxyIps")) {
                List<ProxyIp> proxyIps = (List<ProxyIp>) entry.getValue();
                if (proxyIps != null && proxyIps.size() > 0) {
                    for (ProxyIp proxyIp : proxyIps) {
                        if (checkTime(proxyIp.getOther())) {
                            // 检测ip是否可用
                            if (ProxyUtils.checkVaildProxy(proxyIp.getIp(),proxyIp.getPort())) {
                                proxyService.addProxy(proxyIp);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 判断该Ip所持续的时间是否超过7天
     * @param time
     * @return
     */
    boolean checkTime(String time) {
        if(ProxyUtils.checkChineseCharacter(time)) {
            String timeSuffix = ProxyUtils.returnChineseCharacter(time);
            String timePrefix = ProxyUtils.returnNumber(time);
            if (timeSuffix.equals("天") && Integer.valueOf(timePrefix) >= 7) {
                return true;
            }
        }
        return false;
    }
}
