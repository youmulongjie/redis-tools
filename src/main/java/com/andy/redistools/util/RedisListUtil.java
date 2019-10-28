package com.andy.redistools.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisListUtil<T> extends RedisCommonUtil {
    /**
     * 根据 索引获取 list缓存值
     *
     * @param key   键
     * @param start 开始索引（下标从0开始）
     * @param end   偏移量（-1，则遍历全部数据）
     * @return
     */
    public List<T> range(String key, long start, long end) {
        return (List<T>) redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取 List缓存的长度
     *
     * @param key 键
     * @return
     */
    public long size(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 获取 key键 对应集合中 index索引的值
     *
     * @param key   键
     * @param index 索引
     * @return key键 对应集合中 index索引的值
     */
    public T index(String key, long index) {
        return (T) redisTemplate.opsForList().index(key, index);
    }

    /**
     * 将 value值放入 key对应的List集合 尾部
     *
     * @param key   键
     * @param value 值
     */
    public void rightPush(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 将 value值数组放入 key对应的List集合 尾部
     *
     * @param key    键
     * @param values 值数组
     */
    public void rightPush(String key, T... values) {
        redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 将 value值放入 key对应的List集合 头部
     *
     * @param key   键
     * @param value 值
     */
    public void leftPush(String key, T value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 将 value值数组放入 key对应的List集合 头部
     *
     * @param key    键
     * @param values 值数组
     */
    public void leftPush(String key, T... values) {
        redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 修改 key键对应的List集合中 索引为index的值
     *
     * @param key   键
     * @param index 索引
     * @param value 要更改的值
     */
    public void setIndex(String key, long index, T value) {
        redisTemplate.opsForList().set(key, index, value);
    }

}
