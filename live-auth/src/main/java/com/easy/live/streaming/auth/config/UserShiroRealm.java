package com.easy.live.streaming.auth.config;

import com.easy.live.streaming.data.entity.user.LiveUser;
import com.easy.live.streaming.data.service.user.LiveUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:shiro realm
 * @Author: zhangliangfu
 * @Create on: 2019-07-12 13:42
 */

@Slf4j
@Component
public class UserShiroRealm extends AuthorizingRealm {

    @Autowired
    private LiveUserService liveUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.debug("用户权限认证");
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.debug("用户身份认证");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String username = userToken.getUsername();
        LiveUser user = liveUserService.findLiveUserByName(username);
        if (user!=null){
            return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
        }else {
            log.error("查询用户出错或用户不存在");
            return null;
        }
    }
}
