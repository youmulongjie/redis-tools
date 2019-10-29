redis-tools
================================
Redis 工具类，依赖 springboot（版本：2.0.6.RELEASE），进一步封装 redisTemplate 的操作

## 引入maven依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```
## Redis封装工具类
源码路径：com.andy.redistools.util
<ul>
    <li>RedisStringUtil.java（操作String类型）</li>
    <li>RedisListUtil.java（操作List类型）</li>
    <li>RedisSetUtil.java（操作Set类型）</li>
    <li>RedisHashUtil.java（操作Hash类型）</li>
</ul>

(使用可参看)测试路径：com.andy.redistools
<ul>
    <li>RedisHashUtilTest.java</li>
</ul>

## Andy.wang，欢迎交流

<img src="doc/594580820.jpg" width="15%" alt="Andy.wang的QQ"/>