package com.easy.live.streaming.gateway.filter;

import com.easy.live.streaming.common.config.Constants;
import com.easy.live.streaming.data.bean.BaseOutput;
import com.easy.live.streaming.data.cache.SimpleCacheService;
import com.easy.live.streaming.data.cache.UserCache;
import com.easy.live.streaming.servants.api.auth.servant.AuthServant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: 登录session过滤
 * Author: zhangliangfu
 * Create on: 2019-07-20 17:05
 */

@Slf4j
@Component
public class LoginFilter extends ZuulFilter {

    @Autowired
    private SimpleCacheService simpleCacheService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        return needLogin(currentContext.getRequest().getRequestURI());
    }

    /**
     * 带open的为开放接口，其余的都需要验证登录
     * @param requestUri
     * @return
     */
    private boolean needLogin(String requestUri){
        if (requestUri.contains("/open/")){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Object run() throws ZuulException{
        log.debug("检查用户是否登录");

        //确定是否已经鉴定过身份
        RequestContext ctx = RequestContext.getCurrentContext();
        String sessionId = ctx.getRequest().getSession().getId();
        String key = Constants.LOGIN_CACHE + sessionId;
        UserCache userCache = simpleCacheService.get(key, UserCache.class);
        if (userCache==null) {
            ctx.remove("error.status_code");
            throw new ZuulException(Constants.RetMsg.EXCEPTION_LOGIN.msg, Constants.RetMsg.EXCEPTION_LOGIN.code,
                    Constants.RetMsg.EXCEPTION_LOGIN.msg);
        }else {
            ctx.addZuulRequestHeader("userId", userCache.getUserId()+"");
            simpleCacheService.add(key, userCache, Constants.LOGIN_CACHE_TIME);
        }
        return null;
    }
}
