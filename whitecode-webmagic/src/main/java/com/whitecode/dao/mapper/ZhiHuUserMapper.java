package com.whitecode.dao.mapper;

import com.whitecode.entity.ZhiHuUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by White on 2017/11/3.
 */
@Mapper
public interface ZhiHuUserMapper {

    void addZhiHuUser(ZhiHuUser zhiHuUser);
    void findByName(String zHName);
    List<ZhiHuUser> getZhiHuUsers();
}
