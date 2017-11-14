package com.whitecode.service.impl;

import com.whitecode.ProxyUtils;
import com.whitecode.dao.mapper.ProxyIpMapper;
import com.whitecode.entity.ProxyIp;
import com.whitecode.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by White on 2017/11/13.
 */
@Service
public class ProxyServiceImpl implements ProxyService {
    @Autowired
    private ProxyIpMapper proxyIpMapper;

    @Override
    public void addProxy(ProxyIp proxyIp) {
        proxyIpMapper.addProxy(proxyIp);
    }

    @Override
    public List<ProxyIp> getProxyIps() {
        List<ProxyIp> proxyIps = proxyIpMapper.getProxyIps();
        List<ProxyIp> proxyIpList = new ArrayList<ProxyIp>();
        for (ProxyIp proxyIp : proxyIps) {
            if (ProxyUtils.checkVaildProxy(proxyIp.getIp(),proxyIp.getPort())) {
                proxyIpList.add(proxyIp);
            }
        }
        return proxyIps;
    }
}
