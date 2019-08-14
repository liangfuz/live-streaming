package com.easy.live.streaming.data.filter;

import com.easy.live.streaming.data.cache.ThreadLocalUserCache;
import com.easy.live.streaming.data.cache.UserCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 获取用户信息拦截器
 * Author: zhangliangfu
 * Create on: 2019-08-12 17:56
 */

@Slf4j
public class AcquireUserInfoInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String userId = request.getHeader("userId");
        UserCache userCache = new UserCache();
        if (userId!=null){
            userCache.setUserId(Integer.parseInt(userId));
        }
        ThreadLocalUserCache.setUserCache(userCache);
        return true;
    }

}
