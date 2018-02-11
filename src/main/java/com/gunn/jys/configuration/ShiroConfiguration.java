package com.gunn.jys.configuration;


import com.gunn.jys.constant.shiro.ShiroConst;
import com.gunn.jys.security.SystemAuthority;
import com.gunn.jys.security.filter.PermissionsFilter;
import com.gunn.jys.security.realm.JWTRealm;
import com.gunn.jys.security.realm.UsernamePasswordRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
    public JWTRealm jwtRealm() {
        JWTRealm jwtRealm = new JWTRealm();
        return jwtRealm;
    }

    @Bean
    public List<Realm> realmList() {
        List<Realm> realmList = new ArrayList<>();
        realmList.add(usernamePasswordRealm());
        realmList.add(jwtRealm());
        return  realmList;
    }

    @Bean
    public SystemAuthority systemAuthority() {
        SystemAuthority systemAuthority = new SystemAuthority();
        return systemAuthority;
    }

    @Bean
    public PermissionsFilter permissionsFilter() {
        PermissionsFilter permissionsFilter = new PermissionsFilter();
        return permissionsFilter;
    }

    @Bean
    public Map<String, Filter> authenticatingFilterMap() {
        Map<String, Filter> map = new LinkedHashMap<>();
        map.put(ShiroConst.PERMS, permissionsFilter());
        return  map;
    }

    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealms(realmList());
        return securityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setFilterChainDefinitionMap(systemAuthority().getAllPermission());
        shiroFilterFactoryBean.setFilters(authenticatingFilterMap());
        return shiroFilterFactoryBean;
    }
}
