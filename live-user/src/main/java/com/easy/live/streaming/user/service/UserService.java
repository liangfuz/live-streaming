package com.easy.live.streaming.user.service;

import com.easy.live.streaming.servants.protocol.input.user.UserInput;
import com.easy.live.streaming.data.bean.BaseOutput;
import com.easy.live.streaming.servants.protocol.output.user.UserOutput;

/**
 * @Description:用户Service
 * @Author: zhangliangfu
 * @Create on: 2019-06-13 17:53
 */
public interface UserService {
    /**
     * 创建用户
     *
     * @param input
     * @return
     */
    BaseOutput<UserOutput> createUser(UserInput input);
}
