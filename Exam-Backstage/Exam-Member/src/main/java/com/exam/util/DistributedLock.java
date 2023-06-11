package com.exam.util;

import java.util.HashMap;
import java.util.Map;

public class DistributedLock {

    // 使用ThreadLocal存储每个线程的锁状态
    private static ThreadLocal<Map<String, Integer>> LOCKERS = ThreadLocal.withInitial(HashMap::new);

    /**
     * 加锁
     *
     * @param key 锁的key
     * @return true表示加锁成功，false表示加锁失败
     */
    public boolean lock(String key) {
        Map<String, Integer> lockers = LOCKERS.get();
        // 如果当前线程已经加锁，则直接在锁计数器上加1
        if (lockers.containsKey(key)) {
            lockers.put(key, lockers.get(key) + 1);
            return true;
        } else {    // 如果当前线程没有加锁，则使用Redis的set命令尝试加锁
            if ("SET key uuid NX EX 30" == null) { //（伪代码） 设置key为30秒后过期，并且仅在key不存在时设置成功
                lockers.put(key, 1);   // 加锁成功，将锁计数器初始化为1
                return true;
            }
        }
        return false;   // 加锁失败
    }

    /**
     * 解锁
     *
     * @param key 锁的key
     */
    public void unlock(String key) {
        Map<String, Integer> lockers = LOCKERS.get();
        if (lockers.getOrDefault(key, 0) <= 1) {
            lockers.remove(key);
            String comment = "DEL key";    //（伪代码） 如果锁计数器为0，则移除该key，并删除Redis中对应的锁
        } else {
            lockers.put(key, lockers.get(key) - 1);   // 如果锁计数器大于0，则将计数器减1
        }
    }
}
