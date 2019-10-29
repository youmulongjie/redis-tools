package com.andy.redistools.util;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description: 哈希类型相关操作
 * Author: Andy.wang
 * Date: 2019/10/29 09:08
 */
@Component
public class RedisHashUtil extends RedisCommonUtil {
    /**
     * 将hash表中放入数据,如果不存在将创建（hset key item value）
     *
     * @param key   redis key
     * @param item  hash key
     * @param value hash value
     */
    public void hset(String key, Object item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    /**
     * 将hash表中放入数据,如果不存在将创建，设置失效时间（hset key item value；expire key timeout）
     * <pre>
     *     失效时间说明：
     *      1、设置的失效时间是针对整个 redis key，并非是只能 当前的 hash key；
     *      2、如果已存在的 hash表有时间，这里将会替换原有的时间
     * </pre>
     *
     * @param key     redis key
     * @param item    hash key
     * @param value   hash value
     * @param timeout 失效时间（单位：秒，小于等于0 表示 永久有效）
     */
    public void hset(String key, Object item, Object value, long timeout) {
        redisTemplate.opsForHash().put(key, item, value);

        // 设置 key 失效时间
        expire(key, timeout);
    }

    /**
     * 将 map 表中放入数据,如果不存在将创建（hmset key item1 value1 item2 value2 item3 value3 ...）
     *
     * @param key  redis key
     * @param hash map 表中放入数据
     */
    public void hmSet(String key, Map<Object, Object> hash) {
        redisTemplate.opsForHash().putAll(key, hash);
    }

    /**
     * 将 map 表中放入数据,如果不存在将创建，设置失效时间（hmset key item1 value1 item2 value2 item3 value3 ...；expire key timeout）
     *
     * @param key     redis key
     * @param hash    map 表中放入数据
     * @param timeout 失效时间（单位：秒，小于等于0 表示 永久有效）
     */
    public void hmSet(String key, Map<Object, Object> hash, long timeout) {
        redisTemplate.opsForHash().putAll(key, hash);

        // 设置 key 失效时间
        expire(key, timeout);
    }

    /**
     * 判断是否存在 hash item（hexists key item）
     *
     * @param key  redis key
     * @param item hash item
     * @return 存在返回true，不存在返回 false
     */
    public boolean hasKey(String key, Object item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * 获取 hash item 对应的 value（hget key num）
     *
     * @param key  redis key
     * @param item hash item
     * @return item 对应的 value
     */
    public Object hget(String key, Object item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取 key 对应的 hash 数据（hgetall key）
     *
     * @param key redis key
     * @return 对应的 hash 数据
     */
    public Map<Object, Object> hmGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 删除 hash item 对应的项（hdel key item）
     *
     * @param key  redis key
     * @param item hash item
     * @return 返回删除个数
     */
    public long hdel(String key, Object... item) {
        if (null != item && item.length > 0) {
            return redisTemplate.opsForHash().delete(key, item);
        }
        return 0;
    }

    /**
     * 递增，如果不存在,就会创建一个 并把新增后的值返回（hincrby key item by）
     *
     * @param key  redis key
     * @param item hash item
     * @param by   递增量
     * @return 自增后的数量
     */
    public long hincr(String key, Object item, long by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * 递减，如果不存在,就会创建一个 并把递减后的值返回（hincrby key item by）
     *
     * @param key  redis key
     * @param item hash item
     * @param by   递减量
     * @return 递减后的数量
     */
    public long hdecr(String key, Object item, long by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    /**
     * 获取 key 对应的 hash item 集合（hkeys key）
     *
     * @param key redis key
     * @return hash item 集合
     */
    public Set<Object> hItemKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取 key 对应的 hash value 集合（hvals key）
     *
     * @param key redis key
     * @return hash value 集合
     */
    public List<Object> hItemValues(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 获取 key 对应的 hash 中元素个数（hlen key）
     *
     * @param key
     * @return hash 中元素个数
     */
    public long hSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

}
