package com.exam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.constant.redis.RedisKeyConstant;
import com.exam.entity.Student;
import com.exam.mapper.StudentMapper;
import com.exam.service.StudentService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author PlutoWu
 * @date 2021/05/24
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public IPage<Student> findAll(Integer page, Integer size) {
        Page<Object> allStudent = new Page<>(page, size);
        //1 构建返回结果
        List<Student> res = new ArrayList<>(size);
        //2 先根据条件从数据库中查询分页 idList
        IPage<Student> all = studentMapper.findAll(allStudent);

        return all;
    }

    @Override
    public IPage<Student> findAllStudent(Integer page, Integer size) {
        Page<Student> allStudent = new Page<>(page, size);

        //1 构建返回结果
        List<Student> res = new ArrayList<>(size);

        //2 先根据条件从数据库中查询分页所有符合条件的 idList
        List<Integer> idList = studentMapper.getStudentIdList(page, size);

        //3 批量从缓存中获取所有学生对象 studentList
        String key = RedisKeyConstant.STUDENT;
        List<Student> studentList = redisTemplate.opsForValue().multiGet(idList);

        //4 组装没有命中的问题 noHitIdList
        List<Long> noHitIdList = new ArrayList<>();
        for (Student studentId : studentList) {

        }


        //5 将没有命中缓存的数据从数据库中查出来，加载到缓存里

        //5.1将没有命中的数据加入到缓存里

        //5.2将没有命中的数据聚合到结果 resultMap 中

        //6 组装返回结果

        return null;
    }


    // 将学生对象存储到 Redis 中
    public void save(Student student) {
        redisTemplate.opsForValue().set(student.getStudentId(), student);
    }

    // 根据 id 列表从 Redis 中获取学生对象列表
    public List<Student> getByIds(List<String> idList) {
        return redisTemplate.opsForValue().multiGet(idList);
    }

    @Override
    public Student findById(Integer studentId) {
        return studentMapper.findById(studentId);
    }

    @Override
    public int deleteById(Integer studentId) {
        return studentMapper.deleteById(studentId);
    }

    @Override
    public int update(Student student) {
        return studentMapper.update(student);
    }

    @Override
    public int updatePwd(Student student) {
        return studentMapper.updatePwd(student);
    }

    @Override
    public int add(Student student) {
        return studentMapper.add(student);
    }

}
