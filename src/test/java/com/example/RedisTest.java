package com.example;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;


//如果在測試類添加這個標籤,那麼在單元測試方法執行之前,會先初始spring容器
//@SpringBootTest
//public class RedisTest {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Test
//    public void testSet(){
//        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
//        ops.set("userName", "Ben");
//    }
//
//    @Test
//    public void testGet(){
//        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
//
//        System.out.println(ops.get("userName"));
//    }
//
//}
