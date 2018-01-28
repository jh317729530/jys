package com.gunn.jys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.gunn")
@MapperScan("com.gunn.jys.mapper")
@SpringBootApplication
public class JysApplication {

	public static void main(String[] args) {
		SpringApplication.run(JysApplication.class, args);
	}
}
