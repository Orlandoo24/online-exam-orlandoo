package com.exam.service;

import com.exam.entity.Admin;
import com.exam.entity.Login;
import com.exam.entity.Student;
import com.exam.entity.Teacher;


import java.util.concurrent.TimeUnit;


public interface LoginService {

    Admin adminLogin(Integer username, String password);

    Teacher teacherLogin(Integer username, String password);

    Student studentLogin(Login login);

}
