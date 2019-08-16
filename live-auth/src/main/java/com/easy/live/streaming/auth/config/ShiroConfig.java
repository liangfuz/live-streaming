package com.easy.live.streaming.auth.config;

import com.easy.live.streaming.auth.cache.ShiroRedisCacheManager;
import com.google.common.collect.Maps;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.Map;


/**
 * @Description: Shiro配置
 * @Author: zhangliangfu
 * @Create on: 2019-07-12 13:42
 */
@Configuration
public class ShiroConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilterFactoryBean"));
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
    }

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(ShiroRedisCacheManager shiroRedisCacheManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager(shiroRedisCacheManager));
        Map<String, Filter> filters = Maps.newHashMap();
        shiroFilterFactoryBean.setFilters(filters);
        Map<String, String> chains = Maps.newHashMap();
        chains.put("/**", "authc,perms");
        chains.put("/open/**", "anon");
        chains.put("/swagger-resources/**", "anon");
        chains.put("/swagger-ui.html", "anon");
        chains.put("/v2/**", "anon");
        chains.put("/webjars/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(chains);
        return shiroFilterFactoryBean;
    }

    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(ShiroRedisCacheManager shiroRedisCacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userShiroRealm());
        securityManager.setCacheManager(shiroRedisCacheManager);
        return securityManager;
    }

    @Bean
    public UserShiroRealm userShiroRealm() {
        UserShiroRealm userShiroRealm = new UserShiroRealm();
        return userShiroRealm;
    }

    @Bean(name = "shiroRedisCacheManager")
    @DependsOn(value = {"lifecycleBeanPostProcessor","redisTemplate"})
    public ShiroRedisCacheManager shiroRedisCacheManager(RedisTemplate redisTemplate){
        ShiroRedisCacheManager shiroRedisCacheManager = new ShiroRedisCacheManager(redisTemplate);
        return  shiroRedisCacheManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
