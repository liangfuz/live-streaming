package com.easy.live.streaming.user.service.impl;

import com.easy.live.streaming.common.config.Constants;
import com.easy.live.streaming.data.entity.user.LiveUser;
import com.easy.live.streaming.data.service.user.LiveUserService;
import com.easy.live.streaming.servants.protocol.input.user.UserInput;
import com.easy.live.streaming.data.bean.BaseOutput;
import com.easy.live.streaming.servants.protocol.output.user.UserOutput;
import com.easy.live.streaming.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: 用户Service实现
 * Author: zhangliangfu
 * Create on: 2019-06-13 17:54
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private LiveUserService liveUserService;

    /**
     * 创建用户
     *
     * @param input
     * @return
     */
    @Override
    public BaseOutput<UserOutput> createUser(UserInput input) {
        LiveUser user = new LiveUser();
        BeanUtils.copyProperties(input, user);
        LiveUser save = liveUserService.save(user);
        UserOutput userOutput = new UserOutput();
        BeanUtils.copyProperties(save, userOutput);
        return new BaseOutput<>(Constants.RetMsg.SUCCESS.code, "注册用户成功", userOutput);
    }

    /**
     * 用户登录
     *
     * @param input
     * @return
     */
    @Override
    public BaseOutput userLogin(UserInput input) {
        UsernamePasswordToken upt = new UsernamePasswordToken(input.getName(), input.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(upt);
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
    public BaseOutput userLogout() {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new BaseOutput(Constants.RetMsg.FAIL.code,"登出失败!");
        }
        return new BaseOutput(Constants.RetMsg.SUCCESS.code,"登出成功!");
    }
}
