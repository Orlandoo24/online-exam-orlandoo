package com.exam.util;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserHolderUtil {

    // 使用 ThreadLocal 来存储用户信息
    private static final ThreadLocal<Map<String,String>> THREAD_LOCAL = ThreadLocal.withInitial(HashMap::new);

    // 获取当前线程的 Map 对象
    private static Map<String,String> getLocalMap() {
        return THREAD_LOCAL.get();
    }

    // 将用户信息添加到当前线程的 Map 中
    public static void setUserInfo(String key, String obj) {
        getLocalMap().put(key, obj);
    }

    // 获取当前线程的 Map 中对应的用户信息
    public static String getUserInfo(String key) {
        return getLocalMap().getOrDefault(key, null);
    }

    // 移除当前线程的 Map
    public static void remove() {
        THREAD_LOCAL.remove();
    }

    // 返回一个只读的用户信息 Map
    public static Map<String, String> getUser() {
        return Collections.unmodifiableMap(getLocalMap());
    }



}


