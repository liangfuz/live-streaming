package com.easy.live.streaming.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.easy.live.streaming.common.config.Constants;
import com.easy.live.streaming.data.bean.BaseOutput;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * Description: 处理zuul异常过滤器
 * Author: zhangliangfu
 * Create on: 2019-08-05 17:15
 */

@Slf4j
@Component
public class HandleZuulExceptionFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run(){
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            Object e = ctx.get("throwable");

            if (e != null && e instanceof ZuulException) {
                log.error("检测到ZuulException, message:{}", ((ZuulException) e).getMessage());
                BaseOutput baseOutput = new BaseOutput(Constants.RetMsg.EXCEPTION_LOGIN.code,
                        Constants.RetMsg.EXCEPTION_LOGIN.msg);
                ctx.setResponseBody(JSON.toJSONString(baseOutput));
                ctx.getResponse().setContentType("application/json");
            }
        }
        catch (Exception ex) {
            log.error("Exception filtering in custom error filter", ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }
}
