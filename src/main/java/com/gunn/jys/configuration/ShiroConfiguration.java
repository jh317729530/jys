package com.gunn.jys.configuration;


import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
//        shiroFilterFactoryBean.setFilterChainDefinitionMap();
//        shiroFilterFactoryBean.setFilters();
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        SecurityManager securityManager = new DefaultWebSecurityManager();
        return securityManager;
    }
}
