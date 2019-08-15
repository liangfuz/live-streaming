package com.easy.live.streaming.servants.api.auth.servant;


import com.easy.live.streaming.data.bean.BaseOutput;
import com.easy.live.streaming.data.cache.UserCache;
import com.easy.live.streaming.servants.api.auth.fallback.AuthServantFallback;
import com.easy.live.streaming.servants.protocol.input.user.UserInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: 鉴权服务
 * Author: zhangliangfu
 * Create on: 2019-08-14 14:13
 */
@FeignClient(name = "live-auth-servant", fallbackFactory = AuthServantFallback.class)
@Api(value = "AuthServant", description = "鉴权服务接口")
public interface AuthServant {

    @ApiOperation(value="用户登录接口", notes="用户登录接口")
    @RequestMapping("/open/live/auth/login")
    BaseOutput login(UserInput input);

    @ApiOperation(value="用户登出接口", notes="用户登出接口")
    @RequestMapping("/open/live/auth/logout")
    BaseOutput logout();

    @RequestMapping("/open/live/auth/isLogin")
    BaseOutput<UserCache> isLogin();
}
