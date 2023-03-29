package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.Admin;
import com.exam.entity.Login;
import com.exam.entity.Student;
import com.exam.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;




@Mapper
public interface LoginMapper extends BaseMapper<Login> {

    @Select("select adminId,adminName,sex,tel,email,cardId,role from admin where adminId = #{username} and pwd = #{password}")
    public Admin adminLogin(@Param("username") Integer username, @Param("password") String password);

    @Select("select teacherId,teacherName,institute,sex,tel,email,cardId," +
            "type,role from teacher where teacherId = #{username} and pwd = #{password}")
    public Teacher teacherLogin(@Param("username") Integer username, @Param("password") String password);

    @Select("select studentId,studentName,grade,major,clazz,institute,tel," +
            "email,cardId,sex,role from student where studentId = #{username} and pwd = #{password}")
    public Student studentLogin(@Param("username") Integer username, @Param("password") String password);
}
