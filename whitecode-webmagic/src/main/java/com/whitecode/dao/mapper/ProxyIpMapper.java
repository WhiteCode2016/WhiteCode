package com.whitecode.dao.mapper;

import com.whitecode.entity.ProxyIp;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by White on 2017/11/13.
 */
@Mapper
public interface ProxyIpMapper {

    /**
     * 添加proxy
     * @param proxyIp
     */
    void addProxy(ProxyIp proxyIp);
    /**
     * 根据Ip和port查询
     * @param ip
     * @param port
     * @return
     */
    ProxyIp selectByIpAndPort(@Param("ip") String ip, @Param("port") Integer port);

    /**
     * 获取所有的proxy
     * @return
     */
    List<ProxyIp> getProxyIps();

}
