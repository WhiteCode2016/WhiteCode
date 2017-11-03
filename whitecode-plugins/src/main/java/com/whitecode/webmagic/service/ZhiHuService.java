package com.whitecode.webmagic.service;

import com.whitecode.webmagic.model.ZhiHuUser;

/**
 * Created by White on 2017/11/3.
 */
public interface ZhiHuService {
    void addZhiHuUser(ZhiHuUser zhiHuUser);
    void findByName(String zHName);
}
