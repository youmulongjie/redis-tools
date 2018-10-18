package com.andy.demo.springboot03.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * RedisCommon 工具类
 */
@Component
public class RedisCommonUtil {

    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定缓存失效时间（expire key seconds）
     * @param key 建
     * @param timeout 失效时间（单位：秒，小于等于0 表示 永久有效）
     */
    public void expire(String key, long timeout){
        try {
            if(timeout > 0){
                redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
        } catch (Exception e){
            throw new RuntimeException("指定缓存失效时间 异常：",e);
        }
    }

    /**
     * 取 key键 的失效时间（ttl key）
     * @param key 键
     * @return 失效时间（单位：秒）
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断 key键 是否存在（exists key）
     * @param key 键
     * @return 存在：true；不存在：false
     */
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除key键数组的缓存（del key）
     * @param keys 要删除缓存的key键 数组
     */
    public void del(String ...keys){
        redisTemplate.delete(CollectionUtils.arrayToList(keys));
    }
}
