package com.whitecode.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.MultipartConfigElement;
import java.util.Locale;

/**
 * WebMVC基本配置
 * Created by White on 2017/12/11.
 */
public class BaseMVCConfiguration extends WebMvcConfigurerAdapter {

    // 跨域请求配置
    // 允许所有的外域发起跨域请求，允许外域发起请求任意HTTP Method，允许跨域请求包含任意的头信息。
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");

//        registry.addMapping("/**")
//                .allowedOrigins("*")    //允许所有前端站点调用
//                .allowCredentials(true)
//                .allowedMethods("GET", "POST", "DELETE", "PUT")
//                .maxAge(1728000);
    }

    /*// 文件上传配置
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单文件文件最大大小
        factory.setMaxFileSize("50MB"); //KB,MB
        // 设置总上传数据总大小
        factory.setMaxRequestSize("200MB");
        return factory.createMultipartConfig();
    }*/
}
