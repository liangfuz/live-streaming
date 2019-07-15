package com.easy.live.streaming.user.cache;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Description: Shiro缓存管理
 * Author: zhangliangfu
 * Create on: 2019-07-15 15:28
 */
public class ShiroRedisCacheManager extends AbstractCacheManager {
    private RedisTemplate<byte[], Object> redisTemplate;

    public ShiroRedisCacheManager(RedisTemplate<byte[], Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected Cache createCache(String name) throws CacheException {
        return new ShiroRedisCache(redisTemplate, name);
    }
}
