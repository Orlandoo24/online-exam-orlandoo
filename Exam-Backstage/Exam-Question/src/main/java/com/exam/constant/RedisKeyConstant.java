package com.exam.constant;

public class RedisKeyConstant {

    /**
     * ExamManage 前缀
     */
    public static final String EXAM_MANAGE = "exam:manage:";

    /**
     * 缓存空值 TTL，解决缓存穿透问题
     */
    public static final Long NULL_CACHE_TTL = 24L;

    /**
     * 缓存空值 JSONString，解决缓存穿透问题
     */
    public static final String NULL_CACHE_JSONString = "";
}
