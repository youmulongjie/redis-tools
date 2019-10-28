package com.andy.redistools;

import com.andy.redistools.util.RedisStringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot03ApplicationTests {
	@Autowired
	private RedisStringUtil redisStringUtil;

	@Test
	public void contextLoads() {
	}

	// 测试 String类型
	@Test
	public void RedisStringUtilTest(){
		// 放缓存
		redisStringUtil.set("sex","男");
		redisStringUtil.set("hobby","看书");
		redisStringUtil.set("name","Andy先生",120);
		System.out.println("sex=" + redisStringUtil.get("sex"));
		System.out.println("hobby=" + redisStringUtil.get("hobby"));
		System.out.println("name=" + redisStringUtil.get("name"));
		System.out.println("name.getExpire = " + redisStringUtil.getExpire("name"));
		System.out.println("hobby.getExpire = " + redisStringUtil.getExpire("hobby"));

		redisStringUtil.append("hobby","玩游戏");
		System.out.println("hobby.append=" + redisStringUtil.get("hobby"));
		System.out.println("hobby.getExpire=" + redisStringUtil.getExpire("hobby"));
		System.out.println("hobby.sub="+redisStringUtil.get("hobby", 0, 4));

		System.out.println("sex.getAndSet=" + redisStringUtil.getAndSet("sex", "女"));
		System.out.println("sex=" + redisStringUtil.get("sex"));
		System.out.println("hobby.size=" + redisStringUtil.size("hobby"));

		System.out.println("number.incrby=" + redisStringUtil.incrby("number", 5));
		System.out.println("number.incrby=" + redisStringUtil.incrby("number", 3));
		System.out.println("number.decrby=" + redisStringUtil.decrby("number", 2));

		System.out.println(redisStringUtil.setIfAbsent("sex", "女"));
		System.out.println(redisStringUtil.setIfAbsent("pwd", "123456"));

		Map<String,Object> map = new HashMap<>();
		map.put("a1","a1");
		map.put("a2","a2");
		map.put("a3","a3");
		map.put("a4",4);
		map.put("a5",5);
		map.put("a6",6);
		map.put("a7",new Date());
		redisStringUtil.multiSet(map);

		redisStringUtil.multiGet(new String[]{"name", "hobby", "a1", "a4", "a7"}).forEach(
				(a -> System.out.println(a)));

	}
}
