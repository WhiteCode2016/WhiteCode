package com.whitecode.webmagic.service.impl;

import com.whitecode.webmagic.dao.mapper.ZhiHuUserMapper;
import com.whitecode.webmagic.model.ZhiHuUser;
import com.whitecode.webmagic.service.ZhiHuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by White on 2017/11/3.
 */
@Service
public class ZhiHuServiceImpl implements ZhiHuService {
    @Autowired
    private ZhiHuUserMapper zhiHuUserMapper;

    @Override
    public void addZhiHuUser(ZhiHuUser zhiHuUser) {
        zhiHuUserMapper.addZhiHuUser(zhiHuUser);
    }

    @Override
    public void findByName(String zHName) {
        zhiHuUserMapper.findByName(zHName);
    }
}
