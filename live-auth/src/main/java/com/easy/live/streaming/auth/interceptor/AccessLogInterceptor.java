package com.easy.live.streaming.auth.interceptor;

import com.easy.live.streaming.data.cache.ThreadLocalUserCache;
import com.easy.live.streaming.data.cache.UserCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 访问记录拦截器
 * Author: zhangliangfu
 * Create on: 2019-07-31 13:49
 */
@Slf4j
public class AccessLogInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String sessionId = request.getHeader("sessionId");
        UserCache userCache = new UserCache();
        userCache.setSessionId(sessionId);
        ThreadLocalUserCache.setUserCache(userCache);
        return true;
    }

}
