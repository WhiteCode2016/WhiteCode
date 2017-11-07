package com.whitecode.service;

import com.whitecode.entity.ZhiHuUser;
import java.util.List;

/**
 * Created by White on 2017/11/3.
 */
public interface ZhiHuService {
    void addZhiHuUser(ZhiHuUser zhiHuUser);
    void findByName(String zHName);
    List<ZhiHuUser> getZhiHuUsers();
}
