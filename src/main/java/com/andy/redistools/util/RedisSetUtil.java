package com.andy.redistools.util;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RedisSetUtil<T> extends RedisCommonUtil {

    /**
     * 获取 key键 对应的 Set集合
     * @param key 键
     * @return key键 对应的 Set集合
     */
    public Set<T> members(String key){
        return (Set<T>) redisTemplate.opsForSet().members(key);
    }

    /**
     * 查找 key键 对应的Set集合中 是否包含value值
     * @param key 键
     * @param value 值
     * @return 包含：true；不包含：false
     */
    public boolean isMember(String key, Object value){
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 将数据放在 Set缓存
     * @param key 键
     * @param values 值数组
     * @return 成功个数
     */
    public long addSet(String key, Object ...values){
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 将数据放在 Set缓存，并设置 失效时间
     * @param key 键
     * @param values 值数组
     * @param timeout 失效时间（单位：秒，小于等于0 表示 永久有效）
     * @return
     */
    public long addSet(String key, T []values, long timeout){
        long count = redisTemplate.opsForSet().add(key, values);
        expire(key, timeout);
        return count;
    }

    /**
     * 获取 key键 对应的Set集合的长度
     * @param key 键
     * @return
     */
    public long size(String key){
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 移除 key键 对应的Set集合中 value数组
     * @param key 键
     * @param values 要移除的值数组
     * @return 移除成功的个数
     */
    public long remove(String key, T ...values){
        return redisTemplate.opsForSet().remove(key, values);
    }
}
