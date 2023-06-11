package com.exam.service;

import com.exam.entity.Login;
import com.exam.entity.Student;

import java.util.concurrent.TimeUnit;

public interface TokenService {

    /**
     * token 用 UUID 创建token
     * @param login
     * @return
     */
    String autoCreatToken(Login login);


    /**
     * token 根据用户信息创建
     * @param login
     * @return
     */
    String creatToken(Login login);

    /**
     * 校验 token 的合法性
     * @param token
     * @return
     */
    Boolean checkToken(String token);

    /**
     * 保存 token
     * @param token
     */
    void saveToken(String token);

    /**
     * 缓存 token 到redis
     *
     * @param key
     * @param student
     * @param timeout
     * @param timeUnit
     */
    void saveTokenCache(final String key, final Student student, final Long timeout, final TimeUnit timeUnit);


}


