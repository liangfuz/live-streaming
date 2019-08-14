package com.easy.live.streaming.servants.api.auth.fallback;

import com.easy.live.streaming.common.config.Constants;
import com.easy.live.streaming.data.bean.BaseOutput;
import com.easy.live.streaming.servants.api.auth.servant.AuthServant;
import com.easy.live.streaming.servants.protocol.input.user.UserInput;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * Description: 鉴权服务fallback
 * Author: zhangliangfu
 * Create on: 2019-08-14 14:16
 */

@Slf4j
public class AuthServantFallback implements FallbackFactory<AuthServant> {
    @Override
    public AuthServant create(Throwable throwable) {
        return new AuthServant() {
            @Override
            public BaseOutput login(UserInput input) {
                log.error("用户登录失败, input:{}", input);
                return new BaseOutput<>(Constants.RetMsg.FAIL.getCode(), "用户登录失败");
            }

            @Override
            public BaseOutput logout() {
                log.error("用户登出失败");
                return new BaseOutput<>(Constants.RetMsg.FAIL.getCode(), "用户登出失败");
            }

            @Override
            public BaseOutput isLogin() {
                log.error("获取用户是否登陆失败");
                return new BaseOutput<>(Constants.RetMsg.FAIL.getCode(), "获取用户是否登陆失败");
            }
        };
    }
}
