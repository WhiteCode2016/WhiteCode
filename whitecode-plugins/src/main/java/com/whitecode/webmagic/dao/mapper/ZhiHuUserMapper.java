package com.whitecode.webmagic.dao.mapper;

import com.whitecode.webmagic.model.ZhiHuUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by White on 2017/11/3.
 */
@Mapper
public interface ZhiHuUserMapper {

    void addZhiHuUser(ZhiHuUser zhiHuUser);
    void findByName(String zHName);
}
