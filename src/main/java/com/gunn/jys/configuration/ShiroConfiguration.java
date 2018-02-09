package com.gunn.jys.configuration;


import com.gunn.jys.security.SystemAuthority;
import com.gunn.jys.security.realm.UsernamePasswordRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    @Bean
    public UsernamePasswordRealm usernamePasswordRealm() {
        UsernamePasswordRealm usernamePasswordRealm = new UsernamePasswordRealm();
        return usernamePasswordRealm;
    }

    @Bean
    public List<Realm> realmList() {
        List<Realm> realmList = new ArrayList<>();
        realmList.add(usernamePasswordRealm());
        return  realmList;
    }

    @Bean
    public SystemAuthority systemAuthority() {
        SystemAuthority systemAuthority = new SystemAuthority();
        return systemAuthority;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setFilterChainDefinitionMap(systemAuthority().getAllPermission());
//        shiroFilterFactoryBean.setFilters();
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealms(realmList());
        return securityManager;
    }
}
