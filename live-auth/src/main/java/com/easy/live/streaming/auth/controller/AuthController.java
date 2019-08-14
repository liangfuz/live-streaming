package com.easy.live.streaming.auth.controller;

import com.easy.live.streaming.auth.service.AuthService;
import com.easy.live.streaming.data.bean.BaseOutput;
import com.easy.live.streaming.data.cache.UserCache;
import com.easy.live.streaming.servants.api.auth.servant.AuthServant;
import com.easy.live.streaming.servants.protocol.input.user.UserInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 鉴权控制器
 * Author: zhangliangfu
 * Create on: 2019-08-14 14:48
 */

@RestController
public class AuthController implements AuthServant {

    @Autowired
    private AuthService authService;

    @Override
    public BaseOutput login(UserInput input) {
        return authService.login(input);
    }

    @Override
    public BaseOutput logout() {
        return authService.logout();
    }

    @Override
    public BaseOutput<UserCache> isLogin() {
        return authService.isLogin();
    }
}
