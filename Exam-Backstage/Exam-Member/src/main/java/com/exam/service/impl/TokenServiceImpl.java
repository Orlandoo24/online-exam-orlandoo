package com.exam.service.impl;

import cn.hutool.core.util.IdUtil;
import com.exam.entity.Login;
import com.exam.entity.Student;
import com.exam.service.TokenService;
import com.exam.util.UserHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import cn.hutool.jwt.JWT;
import java.util.concurrent.TimeUnit;
import static com.exam.constant.ContextConstants.PASSWORD;
import static com.exam.constant.ContextConstants.USERNAME;
import static com.exam.constant.redis.RedisKeyConstant.TOKEN_PREFIX;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     *  JWT Secret Key
     */
    private static final String SECRET_KEY = "6FE6C445B68AF78EB5D93F2594FF5D89BB7B8232FFB9F1FC4F5AE8E66B8B0A71";

    @Override
    public String autoCreatToken(Login login) {
        return IdUtil.simpleUUID();
    }

    @Override
    public void saveTokenCache(String key, Student student, Long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, student, timeout, timeUnit);
    }

    @Override
    public String creatToken(Login login) {
        String token = JWT.create()
                .setKey(SECRET_KEY.getBytes())
                .setPayload(USERNAME , login.getUsername())
                .setPayload(PASSWORD, login.getPassword())
                .sign();
        redisTemplate.opsForValue().set(TOKEN_PREFIX + token, token,1, TimeUnit.DAYS);
        return token;
    }

    @Override
    public Boolean checkToken(String token) {
        boolean check = false;
        try {
            check = JWT.of(token).setKey(SECRET_KEY.getBytes()).verify();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public void saveToken(String token) {
        JWT jwt = JWT.of(token).setKey(SECRET_KEY.getBytes());
        String username = String.valueOf(jwt.getPayload(USERNAME));
        String password = String.valueOf(jwt.getPayload(PASSWORD));

        UserHolderUtil.setUserInfo(USERNAME, username);
        UserHolderUtil.setUserInfo(PASSWORD, password);
    }
}
