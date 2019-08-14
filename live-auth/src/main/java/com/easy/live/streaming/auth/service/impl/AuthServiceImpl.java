package com.easy.live.streaming.auth.service.impl;

import com.easy.live.streaming.auth.service.AuthService;
import com.easy.live.streaming.common.config.Constants;
import com.easy.live.streaming.data.bean.BaseOutput;
import com.easy.live.streaming.data.cache.SimpleCacheService;
import com.easy.live.streaming.data.cache.ThreadLocalUserCache;
import com.easy.live.streaming.data.cache.UserCache;
import com.easy.live.streaming.data.entity.user.LiveUser;
import com.easy.live.streaming.data.service.user.LiveUserService;
import com.easy.live.streaming.servants.protocol.input.user.UserInput;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: 鉴权Service实现
 * Author: zhangliangfu
 * Create on: 2019-08-14 14:49
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SimpleCacheService simpleCacheService;
    @Autowired
    private LiveUserService userService;

    /**
     * 用户登录
     *
     * @param input
     * @return
     */
    @Override
    public BaseOutput login(UserInput input) {
        UsernamePasswordToken upt = new UsernamePasswordToken(input.getName(), input.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(upt);
            String sessionId = ThreadLocalUserCache.getUserCache().getSessionId();
            UserCache userCache = new UserCache();
            LiveUser user = userService.findLiveUserByName(input.getName());
            userCache.setUserId(user.getId());
            simpleCacheService.add(Constants.LOGIN_CACHE+sessionId, userCache, Constants.LOGIN_CACHE_TIME);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new BaseOutput(Constants.RetMsg.FAIL.code,"用户名或密码不正确!");
        }
        return new BaseOutput(Constants.RetMsg.SUCCESS.code,"登录成功!");
    }

    /**
     * 用户登出
     *
     * @return
     */
    @Override
    public BaseOutput logout() {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
            String sessionId = ThreadLocalUserCache.getUserCache().getSessionId();
            simpleCacheService.del(Constants.LOGIN_CACHE+sessionId);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new BaseOutput(Constants.RetMsg.FAIL.code,"登出失败!");
        }
        return new BaseOutput(Constants.RetMsg.SUCCESS.code,"登出成功!");
    }

    /**
     * 用户登出
     *
     * @return
     */
    @Override
    public BaseOutput isLogin() {
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (authenticated){
            String sessionId = subject.getSession().getId().toString();
            UserCache userCache = simpleCacheService.get(sessionId, UserCache.class);
            return new BaseOutput(Constants.RetMsg.LOGIN_YES.code, Constants.RetMsg.LOGIN_YES.msg, userCache);
        }else {
            return new BaseOutput(Constants.RetMsg.LOGIN_NO.code, Constants.RetMsg.LOGIN_NO.msg);
        }
    }
}
