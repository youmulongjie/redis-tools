package com.andy.redistools.util;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Redis String操作工具类
 */
@Component
public class RedisStringUtil extends RedisCommonUtil {

    /**
     * 获取key键对应的值（GET key）
     * @param key 键
     * @return
     */
    public Object get(String key){
        if(null == key)
            return null;
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 新增一个字符串类型的值,key是键，value是值（SET key value）
     * @param key 键
     * @param value 值
     */
    public void set(String key, Object value){
        try{
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e){
            throw new RuntimeException("放入缓存 异常：", e);
        }
    }

    /**
     * 放入缓存并设置失效时间（setex key seconds value）
     * @param key 键
     * @param value 值
     * @param timeout 失效时间（单位：秒，小于等于0 表示 永久有效）
     */
    public void set(String key, Object value, long timeout){
        set(key, value);
        expire(key, timeout);
    }

    /**
     * 在原有的值基础上新增字符串到末尾（append key value）
     * @param key 键
     * @param value 新增的字符串
     */
    public void append(String key, String value){
        redisTemplate.opsForValue().append(key, value);
    }

    /**
     * 截取key键对应值得字符串，从开始下标位置到结束下标位置(包含)的字符串（getrange key start end）
     * @param key 键
     * @param start 开始下标位置
     * @param end 结束下标的位置
     * @return 从开始下标位置到结束下标位置(包含)的字符串
     */
    public String get(String key, long start, long end){
        return redisTemplate.opsForValue().get(key, start, end);
    }

    /**
     * 获取原来key键对应的值并重新赋新值（getset key value）
     * @param key 键
     * @param value 新值
     * @return 旧值（原来key键对应的值）
     */
    public Object getAndSet(String key, Object value){
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * 获取指定key键对应值的长度（strlen key）
     * @param key 键
     * @return 返回对应值的长度
     */
    public long size(String key){
        return redisTemplate.opsForValue().size(key);
    }

    /**
     * 将存储在key键上的数字按指定的值 增加（incrby key number）
     * @param key 键
     * @param delta 指定的增加数字
     * @return 返回增加后的值（key键不存在，则在执行操作之前将其设置为0）
     */
    public long incrby(String key, long delta){
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 将存储在key键上的数字按指定的值 减少（incrby key number）
     * @param key 键
     * @param delta 指定的减少数字
     * @return 返回减少后的值（key键不存在，则在执行操作之前将其设置为0）
     */
    public long decrby(String key, long delta){
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * 如果键不存在则新增,存在则不改变已经有的值（setnx key value）
     * @param key 键
     * @param value 值
     * @return key键不存在，返回ture；存在返回false
     */
    public boolean setIfAbsent(String key, Object value){
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 一次多个键设置它们的值（mset key1 value1 key2 value2 ..）
     * @param keyValueMap key为键，value为值
     */
    public void multiSet(Map<String, Object> keyValueMap){
        redisTemplate.opsForValue().multiSet(keyValueMap);
    }

    /**
     * 根据键数组取出对应的value值（mget key1 key2 ..）
     * @param keys 键数组
     * @return
     */
    public List<?> multiGet(String ...keys){
        return redisTemplate.opsForValue().multiGet(CollectionUtils.arrayToList(keys));
    }
}
