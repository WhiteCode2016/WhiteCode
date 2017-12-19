package com.whitecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WhitecodeAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhitecodeAdminApplication.class, args);
	}
}
