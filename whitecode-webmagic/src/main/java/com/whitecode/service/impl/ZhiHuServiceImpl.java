package com.whitecode.service.impl;

import com.whitecode.dao.mapper.ZhiHuUserMapper;
import com.whitecode.entity.ZhiHuUser;
import com.whitecode.service.ZhiHuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    @Override
    public List<ZhiHuUser> getZhiHuUsers() {
        return zhiHuUserMapper.getZhiHuUsers();
    }
}
