package com.easy.live.streaming.servants.api.user.fallback;

import com.easy.live.streaming.common.config.Constants;
import com.easy.live.streaming.data.bean.BaseOutput;
import com.easy.live.streaming.servants.api.user.servant.UserServant;
import com.easy.live.streaming.servants.protocol.input.user.UserInput;
import com.easy.live.streaming.servants.protocol.output.user.UserOutput;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:用户servant回滚
 * @Author: zhangliangfu
 * @Create on: 2019-07-10 19:03
 */

@Slf4j
public class UserServantFallback implements FallbackFactory<UserServant> {
    @Override
    public UserServant create(Throwable throwable) {
        return new UserServant() {
            @Override
            public BaseOutput<UserOutput> createUser(UserInput input) {
                log.error("创建用户失败, input:{}", input);
                return new BaseOutput<>(Constants.RetMsg.FAIL.getCode(), "创建用户失败");
            }
        };
    }
}
