package com.whitecode.service.impl;

import com.whitecode.dao.mapper.ZhiHuUserInfoMapper;
import com.whitecode.dao.mapper.ZhiHuUserMapper;
import com.whitecode.entity.ZhiHuUser;
import com.whitecode.entity.ZhiHuUserInfo;
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

    @Autowired
    private ZhiHuUserInfoMapper zhiHuUserInfoMapper;

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

    @Override
    public void addZhiHuUserInfo(ZhiHuUserInfo zhiHuUserInfo) {
        zhiHuUserInfoMapper.addZhiHuUserInfo(zhiHuUserInfo);
    }

    @Override
    public List<ZhiHuUserInfo> getZhiHuUserInfos() {
        return zhiHuUserInfoMapper.getZhiHuUserInfos();
    }

    @Override
    public List<ZhiHuUserInfo> getZhiUserInfoByAnswerCountTop10() {
        return zhiHuUserInfoMapper.getZhiUserInfoByAnswerCountTop10();
    }

    @Override
    public List<ZhiHuUserInfo> getZhiUserInfoByArticlesCountTop10() {
        return zhiHuUserInfoMapper.getZhiUserInfoByArticlesCountTop10();
    }

    @Override
    public List<ZhiHuUserInfo> getZhiUserInfoByFollowerCountTop10() {
        return zhiHuUserInfoMapper.getZhiUserInfoByFollowerCountTop10();
    }
}
