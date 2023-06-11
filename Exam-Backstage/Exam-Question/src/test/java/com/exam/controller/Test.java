package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;


@SpringBootTest
public class Test {
    // 使用示例
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    public void operateHash() {
        // 添加数据
        redisTemplate.opsForHash().put("myhash", "field1", "value1");
        redisTemplate.opsForHash().put("myhash", "field2", "value2");
        Map<String, String> map = new HashMap<>();
        map.put("field3", "value3");
        map.put("field4", "value4");
        redisTemplate.opsForHash().putAll("myhash", map);

        // 获取单个属性的值
        Object field1Value = redisTemplate.opsForHash().get("myhash", "field1");

        // 获取多个属性的值
        List<Object> fieldValues = redisTemplate.opsForHash().multiGet("myhash", Arrays.asList("field1", "field2"));

        // 获取所有属性的键值对
        Map<Object, Object> allFields = redisTemplate.opsForHash().entries("myhash");

        // 更新属性值
        redisTemplate.opsForHash().put("myhash", "field1", "new_value");

        // 删除属性
        redisTemplate.opsForHash().delete("myhash", "field2");

    }

    @org.junit.jupiter.api.Test
    public void test2() {

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                // 模拟业务逻辑操作
                Thread.sleep(1000);
                System.out.println("异步任务执行完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } , threadPoolExecutor);
        // 异步任务提交完成，当前线程可以继续执行其他操作
        System.out.println("异步任务已提交");


        // 等待异步任务执行完成
        future.join();
        System.out.println("所有任务已完成返回结果");

    }


}
