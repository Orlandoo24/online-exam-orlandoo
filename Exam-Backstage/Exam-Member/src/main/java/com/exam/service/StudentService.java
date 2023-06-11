package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Student;

import java.util.List;

/**
 * @author PlutoWu
 * @date 2021/05/24
 */
public interface StudentService {

    IPage<Student> findAll(Integer page, Integer size);

    Student findById(Integer studentId);

    int deleteById(Integer studentId);

    int update(Student student);

    int updatePwd(Student student);

    int add(Student student);

    IPage<Student> findAllStudent(Integer page, Integer size);

}
