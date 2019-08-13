package com.easy.live.streaming.data.cache;

import org.springframework.core.NamedThreadLocal;

/**
 * Description: 用户本地缓存
 * Author: zhangliangfu
 * Create on: 2019-08-08 13:48
 */
public class ThreadLocalUserCache {
    private static final ThreadLocal<UserCache> localThreadConfig = new NamedThreadLocal<>("UserCache");

    /**
     * 设置
     *
     * @param userCache
     */
    public final static void setUserCache(UserCache userCache) {
        localThreadConfig.set(userCache);
    }

    /**
     * 获取
     * @return
     */
    public final static UserCache getUserCache(){
        return localThreadConfig.get();
    }

    /**
     * 移除
     */
    public final static void remove(){
        localThreadConfig.remove();
    }
}
