package com.gunn.jys.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gunn.jys.combiz.service.JacksonService;
import com.gunn.jys.converter.DateConverter;
import com.gunn.jys.interceptor.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter {

    @Resource
    private PageInterceptor pageInterceptor;

    @Resource
    private JacksonService jacksonService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //分页拦截器
        registry.addInterceptor(pageInterceptor).addPathPatterns("/**");
    }

    @Bean
    public ObjectMapper objectMapper() {
        return jacksonService.getObjectMapper(true);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
        super.addFormatters(registry);
    }
}
