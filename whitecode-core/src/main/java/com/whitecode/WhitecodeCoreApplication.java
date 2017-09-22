package com.whitecode;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WhitecodeCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhitecodeCoreApplication.class, args);
	}



}
