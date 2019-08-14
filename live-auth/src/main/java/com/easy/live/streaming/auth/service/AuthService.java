package com.easy.live.streaming.auth.service;

import com.easy.live.streaming.data.bean.BaseOutput;
import com.easy.live.streaming.data.cache.UserCache;
import com.easy.live.streaming.servants.protocol.input.user.UserInput;

/**
 * Description: 鉴权service
 * Author: zhangliangfu
 * Create on: 2019-08-14 14:49
 */
public interface AuthService {

    /**
     * 用户登录
     *
     * @param input
     * @return
     */
    BaseOutput login(UserInput input);

    /**
     * 用户登录
     *
     * @return
     */
    BaseOutput logout();

    /**
     * 用户是否登陆登录
     *
     * @return
     */
    BaseOutput<UserCache> isLogin();
}
