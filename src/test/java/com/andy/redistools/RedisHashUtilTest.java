package com.andy.redistools;

import com.andy.redistools.util.RedisHashUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 哈希类型相关操作 测试类
 * Author: Andy.wang
 * Date: 2019/10/29 09:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisHashUtilTest {
    @Autowired
    private RedisHashUtil redisHashUtil;

    String key = "hash_test:hash:";
    String beanKey = "hash_test:people:";
    String mapKey = "hash_test:history:";

    @Test
    public void set() {
        // hash
        String item = "id";
        int intValue = 1001;
        redisHashUtil.hset(key, item, intValue, 60 * 2);

        item = "name";
        String sValue = "Andy.wang";
        redisHashUtil.hset(key, item, sValue);

        item = "name";
        sValue = "Andy市民";
        // 设置的失效时间是针对整个 redis key，并非是只能 当前的 hash key
        redisHashUtil.hset(key, item, sValue, 60 * 10);

        // bean
        item = "1";
        People people = People.builder().id(1).name("QQ").age(15).build();
        redisHashUtil.hset(beanKey, item, people);

        item = "2";
        people = People.builder().id(2).name("WX").age(8).build();
        redisHashUtil.hset(beanKey, item, people);

        // map
        Map<Object, Object> hashMap = new HashMap<>(4);
        hashMap.put("人物", "朱元璋");
        hashMap.put("年代", "明朝");
        hashMap.put("事迹", "明朝开国皇帝");
        hashMap.put("创建时间", new Date());


        redisHashUtil.hmSet(mapKey, hashMap);
    }

    @Test
    public void get() {
        System.out.println(redisHashUtil.hasKey(key));
        System.out.println(redisHashUtil.hasKey(key, "sex"));
        System.out.println(redisHashUtil.hasKey(beanKey, "1"));
        System.out.println(redisHashUtil.hasKey(mapKey, "人物"));

        System.out.println(redisHashUtil.hget(key, "name"));
        System.out.println(redisHashUtil.hget(beanKey, "2"));
        System.out.println(redisHashUtil.hget(mapKey, "年代"));

        System.out.println(redisHashUtil.hget(mapKey, "不存在的item"));

        Map<Object, Object> map = redisHashUtil.hmGet(key);
        map.forEach((key, value) -> System.out.println(key + ":" + value));
        System.out.println("......");

        map = redisHashUtil.hmGet(beanKey);
        map.forEach((key, value) -> System.out.println(key + ":" + value));
        System.out.println("......");

        map = redisHashUtil.hmGet(mapKey);
        map.forEach((key, value) -> System.out.println(key + ":" + value));
        System.out.println("......");
    }

    @Test
    public void del() {
        System.out.println(redisHashUtil.hdel("不存在的key"));
        System.out.println(redisHashUtil.hdel(key, "name"));
        System.out.println(redisHashUtil.hdel(beanKey, "1"));
        System.out.println(redisHashUtil.hdel(mapKey, "创建时间", "事迹"));
    }

    @Test
    public void incr() {
        System.out.println(redisHashUtil.hincr(key, "num", 1));
        System.out.println(redisHashUtil.hincr(key, "num", 1));

        System.out.println(redisHashUtil.hincr(key, "num", 5));

        System.out.println(redisHashUtil.hdecr(key, "num", 3));
        System.out.println(redisHashUtil.hdecr(key, "num1", 1));
    }

    @Test
    public void item() {
        System.out.println("item.size = " + redisHashUtil.hSize(key));
        redisHashUtil.hItemKeys(key).forEach(key -> System.out.println(key));
        System.out.println("...");
        redisHashUtil.hItemValues(key).forEach(value -> System.out.println(value));
        System.out.println("......");

        System.out.println("item.size = " + redisHashUtil.hSize(beanKey));
        redisHashUtil.hItemKeys(beanKey).forEach(key -> System.out.println(key));
        System.out.println("...");
        redisHashUtil.hItemValues(beanKey).forEach(value -> System.out.println(value));
        System.out.println("......");

        System.out.println("item.size = " + redisHashUtil.hSize(mapKey));
        redisHashUtil.hItemKeys(mapKey).forEach(key -> System.out.println(key));
        System.out.println("...");
        redisHashUtil.hItemValues(mapKey).forEach(value -> System.out.println(value));
        System.out.println("......");
    }
}
