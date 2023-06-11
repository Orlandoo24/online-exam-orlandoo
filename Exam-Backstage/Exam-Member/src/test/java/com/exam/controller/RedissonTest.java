package com.exam.controller;

import com.exam.util.UserHolderUtil;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
public class RedissonTest {
    @Autowired
    RedissonClient redisson;

    @Test
    void test01() {
        //测试获取锁
        RLock lock = redisson.getLock("yiyouliao-lock");

        String name = lock.getName();
        System.out.println(name);
        lock.unlock();
    }
    
    @Test
    void test02 () {

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                // 模拟业务逻辑操作
                Thread.sleep(1000);
                System.out.println("异步任务执行完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
// 异步任务提交完成，当前线程可以继续执行其他操作
        System.out.println("异步任务已提交");


// 等待异步任务执行完成
        future.join();


        System.out.println("所有任务已完成返回结果");

    }



}
