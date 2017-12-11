package com.whitecode.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * Admin模块WebMVC配置
 * Created by White on 2017/9/20.
 */
@Configuration
public class WebMvcConfiguration extends BaseMVCConfiguration {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
