package com.easy.live.streaming.data.cache;

import com.easy.live.streaming.common.util.jpa.SerializeUtils;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.*;

/**
 * Description: Shiro缓存
 * Author: zhangliangfu
 * Create on: 2019-07-15 15:13
 */

@Slf4j
public class ShiroRedisCache<K, V> implements Cache<K, V> {

    private RedisTemplate<byte[], V> redisTemplate;
    private String prefix = "SHIRO_CACHE:";

    public ShiroRedisCache(RedisTemplate<byte[], V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public ShiroRedisCache(RedisTemplate<byte[], V> redisTemplate, String prefix) {
        this(redisTemplate);
        this.prefix = prefix;
    }


    public V get(K key) throws CacheException {
        if(log.isDebugEnabled()) {
            log.debug("Key: {}", key);
        }
        if(key == null) {
            return null;
        }

        byte[] bkey = getByteKey(key);
        return redisTemplate.opsForValue().get(bkey);
    }


    public V put(K key, V value) throws CacheException {
        if(log.isDebugEnabled()) {
            log.debug("Key: {}, value: {}", key, value);
        }

        if(key == null || value == null) {
            return null;
        }

        byte[] bkey = getByteKey(key);
        redisTemplate.opsForValue().set(bkey, value);
        return value;
    }


    public V remove(K key) throws CacheException {
        if(log.isDebugEnabled()) {
            log.debug("Key: {}", key);
        }

        if(key == null) {
            return null;
        }

        byte[] bkey = getByteKey(key);
        ValueOperations<byte[], V> vo = redisTemplate.opsForValue();
        V value = vo.get(bkey);
        redisTemplate.delete(bkey);
        return value;
    }


    public void clear() throws CacheException {
        byte[] bkey = (prefix+"*").getBytes();
        Set<byte[]> set = redisTemplate.keys(bkey);
        if(!CollectionUtils.isEmpty(set)) {
            for(byte[] key:set) {
                redisTemplate.delete(key);
            }
        }
    }

    public int size() {
        byte[] bkey = (prefix+"*").getBytes();
        Set<byte[]> set = redisTemplate.keys(bkey);
        return set.size();
    }

    public Set<K> keys() {
        byte[] bkey = (prefix+"*").getBytes();
        Set<byte[]> set = redisTemplate.keys(bkey);
        Set<K> result = Sets.newHashSet();

        if(CollectionUtils.isEmpty(set)) {
            return Collections.emptySet();
        }

        for(byte[] key: set) {
            result.add((K)key);
        }
        return result;
    }


    public Collection<V> values() {
        Set<K> keys = keys();
        List<V> values = new ArrayList<V>(keys.size());
        for(K k: keys) {
            byte[] bkey = getByteKey(k);
            values.add(redisTemplate.opsForValue().get(bkey));
        }
        return values;
    }

    private byte[] getByteKey(K key){
        if(key instanceof String){
            String preKey = this.prefix + key;
            return preKey.getBytes();
        }else{
            return SerializeUtils.serialize(key);
        }
    }
}
