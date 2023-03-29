package com.exam.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.exam.entity.Admin;
import com.exam.entity.Login;
import com.exam.entity.Student;
import com.exam.entity.Teacher;
import com.exam.mapper.AdminMapper;
import com.exam.mapper.LoginMapper;
import com.exam.service.LoginService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author PlutoWu
 * @date 2021/05/24
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin adminLogin(Integer username, String password) {
       return loginMapper.adminLogin(username,password);
    }

    @Override
    public Teacher teacherLogin(Integer username, String password) {
        return loginMapper.teacherLogin(username,password);
    }

    @Override
    public Student studentLogin(Integer username, String password) {
        return loginMapper.studentLogin(username,password);
    }
}
