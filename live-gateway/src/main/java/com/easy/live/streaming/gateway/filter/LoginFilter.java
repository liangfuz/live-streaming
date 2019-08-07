package com.easy.live.streaming.gateway.filter;

import com.easy.live.streaming.common.config.Constants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

/**
 * Description: 登录session过滤
 * Author: zhangliangfu
 * Create on: 2019-07-20 17:05
 */

@Slf4j
@Component
public class LoginFilter extends ZuulFilter {

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

        Subject subject = SecurityUtils.getSubject();
        //确定是否已经鉴定过身份
        boolean authenticated = subject.isAuthenticated();
        if (!authenticated) {
            RequestContext ctx = RequestContext.getCurrentContext();
            ctx.remove("error.status_code");
            throw new ZuulException(Constants.RetMsg.EXCEPTION_LOGIN.msg, Constants.RetMsg.EXCEPTION_LOGIN.code,
                    Constants.RetMsg.EXCEPTION_LOGIN.msg);
        }
        return null;
    }
}
