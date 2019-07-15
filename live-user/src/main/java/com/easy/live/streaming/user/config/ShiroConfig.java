package com.easy.live.streaming.user.config;

import com.easy.live.streaming.user.cache.ShiroRedisCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @Description: Shiro配置
 * @Author: zhangliangfu
 * @Create on: 2019-07-12 13:42
 */
@Configuration
public class ShiroConfig {
    @Bean
    public UserShiroRealm userShiroRealm() {
        UserShiroRealm userShiroRealm = new UserShiroRealm();
        return userShiroRealm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(ShiroRedisCacheManager shiroRedisCacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userShiroRealm());
        securityManager.setCacheManager(shiroRedisCacheManager);
        return securityManager;
    }

    @Bean(name = "shiroRedisCacheManager")
    @DependsOn(value = "redisTemplate")
    public ShiroRedisCacheManager shiroRedisCacheManager(RedisTemplate redisTemplate){
        ShiroRedisCacheManager shiroRedisCacheManager = new ShiroRedisCacheManager(redisTemplate);
        return  shiroRedisCacheManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //不设置URL权限，交由Zuul处理
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
