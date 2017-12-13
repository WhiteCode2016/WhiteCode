package com.whitecode.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import javax.servlet.MultipartConfigElement;

/**
 * 常用配置
 * Created by White on 2017/12/12.
 */
@Configuration
public class CommonConfiguration {

    /**************************文件上传下载相关配置**************************/
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单文件文件最大大小
        factory.setMaxFileSize("50MB"); //KB,MB
        // 设置总上传数据总大小
        factory.setMaxRequestSize("200MB");
        return factory.createMultipartConfig();
    }

    /**************************验证码配置**************************/
    @Bean
    public ServletRegistrationBean kaptchaServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new KaptchaServlet(), "/kaptcha.jpg");
        registrationBean.addInitParameter(Constants.KAPTCHA_SESSION_CONFIG_KEY, Constants.KAPTCHA_SESSION_KEY);
        registrationBean.addInitParameter(Constants.KAPTCHA_IMAGE_HEIGHT,"60");//高度
        registrationBean.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE,"50");//字体大小
        registrationBean.addInitParameter(Constants.KAPTCHA_BORDER_THICKNESS,"1"); //边框
        registrationBean.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "red"); //文字颜色

        //可以设置很多属性,具体看com.google.code.kaptcha.Constants
//      kaptcha.border  是否有边框  默认为true  我们可以自己设置yes，no
//      kaptcha.border.color   边框颜色   默认为Color.BLACK
//      kaptcha.border.thickness  边框粗细度  默认为1
//      kaptcha.producer.impl   验证码生成器  默认为DefaultKaptcha
//      kaptcha.textproducer.impl   验证码文本生成器  默认为DefaultTextCreator
//      kaptcha.textproducer.char.string   验证码文本字符内容范围  默认为abcde2345678gfynmnpwx
//      kaptcha.textproducer.char.length   验证码文本字符长度  默认为5
//      kaptcha.textproducer.font.names    验证码文本字体样式  默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
//      kaptcha.textproducer.font.size   验证码文本字符大小  默认为40
//      kaptcha.textproducer.font.color  验证码文本字符颜色  默认为Color.BLACK
//      kaptcha.textproducer.char.space  验证码文本字符间距  默认为2
//      kaptcha.noise.impl    验证码噪点生成对象  默认为DefaultNoise
//      kaptcha.noise.color   验证码噪点颜色   默认为Color.BLACK
//      kaptcha.obscurificator.impl   验证码样式引擎  默认为WaterRipple
//      kaptcha.word.impl   验证码文本字符渲染   默认为DefaultWordRenderer
//      kaptcha.background.impl   验证码背景生成器   默认为DefaultBackground
//      kaptcha.background.clear.from   验证码背景颜色渐进   默认为Color.LIGHT_GRAY
//      kaptcha.background.clear.to   验证码背景颜色渐进   默认为Color.WHITE
//      kaptcha.image.width   验证码图片宽度  默认为200
//      kaptcha.image.height  验证码图片高度  默认为50
        return registrationBean;
    }

   /* *//**************************Thymeleaf配置**************************//*
    // 模板解析引擎
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/templates");      //地址前缀
        templateResolver.setSuffix(".html");           //后缀
        templateResolver.setCacheable(Boolean.FALSE);  //不缓存
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    // 模板引擎
    @Bean
    public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setEnableSpringELCompiler(Boolean.TRUE);
        return templateEngine;
    }

    // 设置视图解析器
    @Bean
    public ThymeleafViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }*/
}
