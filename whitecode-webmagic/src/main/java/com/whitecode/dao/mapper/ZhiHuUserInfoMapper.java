package com.whitecode.dao.mapper;

import com.whitecode.entity.ZhiHuUser;
import com.whitecode.entity.ZhiHuUserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by White on 2017/11/3.
 */
@Mapper
public interface ZhiHuUserInfoMapper {
    /**
     * 添加知乎用户
     * @param zhiHuUserInfo
     */
    void addZhiHuUserInfo(ZhiHuUserInfo zhiHuUserInfo);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    int getZhiHuUserInfo(String id);

    /**
     * 获取知乎的所有用户
     * @return
     */
    List<ZhiHuUserInfo> getZhiHuUserInfos();

    /**
     * 获取知乎回答数前10用户
     * @return
     */
    List<ZhiHuUserInfo> getZhiUserInfoByAnswerCountTop10();

    /**
     * 获取知乎文章数前10用户
     * @return
     */
    List<ZhiHuUserInfo> getZhiUserInfoByArticlesCountTop10();

    /**
     * 获取知乎关注者数前10用户
     * @return
     */
    List<ZhiHuUserInfo> getZhiUserInfoByFollowerCountTop10();
}
