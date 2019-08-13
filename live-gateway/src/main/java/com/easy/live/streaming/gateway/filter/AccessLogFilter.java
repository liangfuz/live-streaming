package com.easy.live.streaming.gateway.filter;

import com.easy.live.streaming.common.config.Constants;
import com.easy.live.streaming.data.cache.SimpleCacheService;
import com.easy.live.streaming.data.cache.UserCache;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 访问日志
 * Author: zhangliangfu
 * Create on: 2019-07-25 10:21
 */
@Slf4j
@Component
public class AccessLogFilter extends ZuulFilter {

    @Autowired
    private SimpleCacheService cacheService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String requestURI = request.getRequestURI();
        log.debug("通过网关请求接口:{}", requestURI);
        String sessionId = request.getSession().getId();
        if (StringUtils.isNotEmpty(sessionId)){
            UserCache userCache = cacheService.get(Constants.LOGIN_CACHE + sessionId, UserCache.class);
            if (userCache != null){
                requestContext.addZuulRequestHeader("userId", userCache.getUserId()+"");
            }
            requestContext.addZuulRequestHeader("sessionId", sessionId);
        }
        return null;
    }
}
