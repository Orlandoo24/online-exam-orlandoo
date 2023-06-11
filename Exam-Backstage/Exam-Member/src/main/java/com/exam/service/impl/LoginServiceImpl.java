package com.exam.service.impl;

import com.exam.constant.redis.RedisKeyConstant;
import com.exam.entity.Admin;
import com.exam.entity.Login;
import com.exam.entity.Student;
import com.exam.entity.Teacher;
import com.exam.mapper.AdminMapper;
import com.exam.mapper.LoginMapper;
import com.exam.service.LoginService;
import com.exam.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author PlutoWu
 * @date 2021/05/24
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Admin adminLogin(Integer username, String password) {
       return loginMapper.adminLogin(username,password);
    }

    @Override
    public Teacher teacherLogin(Integer username, String password) {
        return loginMapper.teacherLogin(username,password);
    }

    /**
     * online-exam 学生端登陆逻辑
     * @param login
     * @return
     */
    @Override
    public Student studentLogin(Login login) {
        //密码验证
        Integer username = login.getUsername();
        String password = login.getPassword();
        Student student = loginMapper.studentLogin(username, password);

        //生成 token
        String token = tokenService.creatToken(login);

        //缓存 token
        tokenService.saveTokenCache(RedisKeyConstant.LOGIN + token, student , 3L, TimeUnit.HOURS);
        return student;
    }


}
