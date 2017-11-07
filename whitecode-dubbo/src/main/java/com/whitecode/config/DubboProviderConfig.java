package com.whitecode.config;

import com.alibaba.dubbo.config.spring.ServiceBean;
import org.springframework.context.annotation.Bean;

/**
 * Dubbo生产者配置
 * 使用这种方式配置需要在SysUserServiceImpl上加上
 * 注解@Service("sysUserService")
 * Created by White on 2017/10/31.
 */
//@Configuration
public class DubboProviderConfig extends DubboBaseConfig {

  /*  @Bean
    public ServiceBean<SysUserService> sysUserServiceExport(SysUserService sysUserService) {
        ServiceBean<SysUserService> serviceBean = new ServiceBean<SysUserService>();
        serviceBean.setInterface(SysUserService.class.getName());
        serviceBean.setRef(sysUserService);
        serviceBean.setRetries(0);
        serviceBean.setTimeout(6000);
        return serviceBean;
    }*/
}
