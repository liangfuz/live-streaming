package com.easy.live.streaming.servants.api.user.servant;

import com.easy.live.streaming.data.bean.BaseOutput;
import com.easy.live.streaming.servants.api.user.fallback.UserServantFallback;
import com.easy.live.streaming.servants.protocol.input.user.UserInput;
import com.easy.live.streaming.servants.protocol.output.user.UserOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:用户Api
 * @Author: zhangliangfu
 * @Create on: 2019-07-10 19:02
 */

@FeignClient(name = "live-user-servant", fallbackFactory = UserServantFallback.class)
@Api(value = "UserServant", description = "用户服务接口")
public interface UserServant {

    /**
     * 创建用户
     * @param input
     * @return
     */
    @ApiOperation(value="创建用户接口", notes="创建用户接口")
    @RequestMapping("/open/live/user/createUser")
    BaseOutput<UserOutput> createUser(UserInput input);
}
