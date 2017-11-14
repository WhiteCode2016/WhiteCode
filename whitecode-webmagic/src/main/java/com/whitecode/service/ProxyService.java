package com.whitecode.service;

import com.whitecode.entity.ProxyIp;

import java.util.List;

/**
 * Created by White on 2017/11/13.
 */
public interface ProxyService {
    /**
     * 添加proxy
     * @param proxyIp
     */
    void addProxy(ProxyIp proxyIp);

    /**
     * 获取所有的proxy
     * @return
     */
    List<ProxyIp> getProxyIps();
}
