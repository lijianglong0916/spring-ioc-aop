package org.spring.infra.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */

public class App {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public  void use(){
        //设置值
        redisTemplate.opsForValue().set("test-string-value", "Hello Redis");
        //获取值
        String value = stringRedisTemplate.opsForValue().get("test-string-value");
        //设置值的时候设置过期时间
        stringRedisTemplate.opsForValue().set("test-string-key-time-out", "Hello Redis", 3, TimeUnit.HOURS);
        //删除数据
        stringRedisTemplate.delete("test-string-value");
        //---操作数组---
        //leftPush(K key, V value)，往 List 左侧插入一个元素
        redisTemplate.opsForList().leftPush("TestList", "TestLeftPush");
        //rightPush(K key, V value)，往 List 右侧插入一个元素
        redisTemplate.opsForList().rightPush("TestList", "TestRightPush");
        //leftPop(K key)，从 List 左侧取出第一个元素，并移除
        Object leftFirstElement = redisTemplate.opsForList().leftPop("TestList");
        //rightPop(K key)，从 List 右侧取出第一个元素，并移除
        Object rightFirstElement = redisTemplate.opsForList().rightPop("TestList");
        //---操作 Hash---
        //Hash 中新增元素
        redisTemplate.opsForHash().put("TestHash", "FirstElement", "Hello,Redis hash.");
        //获取指定 key 对应的 Hash 中指定键的值
        Object element = redisTemplate.opsForHash().get("TestHash", "FirstElement");
        //删除指定 key 对应 Hash 中指定键的键值对。
        redisTemplate.opsForHash().delete("TestHash", "FirstElement");
        //---操作集合---
        //向集合中添加元素。
        redisTemplate.opsForSet().add("TestSet", "e1", "e2", "e3");
        //获取集合中的元素。
        Set<String> testSet = redisTemplate.opsForSet().members("TestSet");
        //移除集合中的元素。
        redisTemplate.opsForSet().remove("TestSet", "e1", "e2");

    }
}

