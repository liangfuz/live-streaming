package com.easy.live.streaming.user.controller;

import com.easy.live.streaming.servants.api.user.servant.UserServant;
import com.easy.live.streaming.servants.protocol.input.user.UserInput;
import com.easy.live.streaming.servants.protocol.output.BaseOutput;
import com.easy.live.streaming.servants.protocol.output.user.UserOutput;
import com.easy.live.streaming.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 用户controller
 * @Author: zhangliangfu
 * @Create on: 2019-07-10 19:17
 */

@Slf4j
@RestController
public class UserController implements UserServant {
    @Autowired
    private UserService userService;

    /**
     * 创建用户
     *
     * @param input
     * @return
     */
    @Override
    public BaseOutput<UserOutput> createUser(UserInput input) {
        log.debug("创建用户,input:{}", input);
        return userService.createUser(input);
    }

    /**
     * 用户登录
     *
     * @param input
     * @return
     */
    @Override
    public BaseOutput userLogin(UserInput input) {
        log.debug("用户登录,input:{}", input);
        return userService.userLogin(input);
    }
}
