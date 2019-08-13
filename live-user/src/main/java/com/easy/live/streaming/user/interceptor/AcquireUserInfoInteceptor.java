package com.easy.live.streaming.user.interceptor;

import com.easy.live.streaming.data.cache.ThreadLocalUserCache;
import com.easy.live.streaming.data.cache.UserCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 获取用户信息拦截器
 * Author: zhangliangfu
 * Create on: 2019-08-12 17:56
 */

@Slf4j
public class AcquireUserInfoInteceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String userId = request.getHeader("userId");
        String sessionId = request.getHeader("sessionId");
        UserCache userCache = new UserCache();
        userCache.setSessionId(sessionId);
        if (userId!=null){
            userCache.setUserId(Integer.parseInt(userId));
        }
        ThreadLocalUserCache.setUserCache(userCache);
        return true;
    }

}
