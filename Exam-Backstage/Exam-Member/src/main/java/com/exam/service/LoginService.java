package com.exam.service;

import com.exam.entity.Admin;
import com.exam.entity.Student;
import com.exam.entity.Teacher;


public interface LoginService {

    Admin adminLogin(Integer username, String password);

    Teacher teacherLogin(Integer username, String password);

    Student studentLogin(Integer username, String password);
}
