package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author PlutoWu
 * @date 2021/05/24
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin>{

    @Select("select adminName,sex,tel,email,cardId,role from admin")
    public List<Admin> findAll();

    @Select("select adminName,sex,tel,email,cardId,role from admin where adminId = #{adminId}")
    public Admin findById(Integer adminId);

    @Delete("delete from admin where adminId = #{adminId}")
    public int deleteById(int adminId);

    @Update("update admin set adminName = #{adminName},sex = #{sex}," +
            "tel = #{tel}, email = #{email},pwd = #{pwd},cardId = #{cardId},role = #{role} where adminId = #{adminId}")
    public int update(Admin admin);

    @Options(useGeneratedKeys = true, keyProperty = "adminId")
    @Insert("insert into admin(adminName,sex,tel,email,pwd,cardId,role) " +
            "values(#{adminName},#{sex},#{tel},#{email},#{pwd},#{cardId},#{role})")
    public int add(Admin admin);
}
