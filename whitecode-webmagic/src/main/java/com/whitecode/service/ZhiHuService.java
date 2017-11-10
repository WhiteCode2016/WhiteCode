package com.whitecode.service;

import com.whitecode.entity.ZhiHuUser;
import com.whitecode.entity.ZhiHuUserInfo;

import java.util.List;

/**
 * Created by White on 2017/11/3.
 */
public interface ZhiHuService {
    void addZhiHuUser(ZhiHuUser zhiHuUser);
    void findByName(String zHName);
    List<ZhiHuUser> getZhiHuUsers();

    void addZhiHuUserInfo(ZhiHuUserInfo zhiHuUserInfo);
    List<ZhiHuUserInfo> getZhiHuUserInfos();
    List<ZhiHuUserInfo> getZhiUserInfoByAnswerCountTop10();
    List<ZhiHuUserInfo> getZhiUserInfoByArticlesCountTop10();
    List<ZhiHuUserInfo> getZhiUserInfoByFollowerCountTop10();
}
