package com.easy.live.streaming.user.interceptor;

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

    private ThreadLocal<Long> invokeTime = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        invokeTime.set(System.currentTimeMillis());
        log.info("=========> 接口访问开始, URI:{},Method:{}", requestURI, method);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Long startTime = invokeTime.get();
        long endTime = System.currentTimeMillis();
        long miliSec = endTime - startTime;
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        log.info("=========> 接口访问结束, URI:{},Method:{},耗时:{}毫秒", requestURI, method,miliSec);
    }
}
