package com.easy.live.streaming.gateway.handler;

import com.easy.live.streaming.common.config.Constants;
import com.easy.live.streaming.common.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: 全局异常捕捉
 * Author: zhangliangfu
 * Create on: 2019-08-01 15:21
 */

@Slf4j
@ControllerAdvice(basePackages = "com.easy")
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler
    public Exception handleGlobalException(Exception e){
        log.error(e.getMessage());
        return new GlobalException(Constants.RetMsg.FAIL.code, e.getMessage());
    }
}
