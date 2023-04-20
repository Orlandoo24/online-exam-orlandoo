package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ExamManage;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author PlutoWu
 * @date 2021/05/24
 */
@Mapper
public interface ExamManageMapper extends BaseMapper<ExamManage> {

    /**
     * 分页查询所有考试和练习
     */
    //分页查询所有考试
    @Select("SELECT * FROM `exam_manage`")
    IPage<ExamManage> findAll(Page page);


    //不分页查询所有考试
    @Select("SELECT * FROM `exam_manage` WHERE `type` NOT LIKE '%练习%'")
    List<ExamManage> findAllExam();
    //分页查询所有考试
    @Select("SELECT * FROM `exam_manage` WHERE `type` NOT LIKE '%练习%'")
    IPage<ExamManage> findAllExam(Page page);

    //不分页查询所有练习
    @Select("SELECT * FROM `exam_manage` WHERE `type` LIKE '%练习%'")
    List<ExamManage> findAllPractices();
    //分页查询所有练习
    @Select("SELECT * FROM `exam_manage` WHERE `type` LIKE '%练习%'")
    IPage<ExamManage> findAllPractices(Page page);


    @Select("select * from exam_manage where examCode = #{examCode}")
    ExamManage findById(Integer examCode);

    @Delete("delete from exam_manage where examCode = #{examCode}")
    int delete(Integer examCode);

    @Update("update exam_manage set description = #{description},source = #{source},paperId = #{paperId}," +
            "examDate = #{examDate},totalTime = #{totalTime},grade = #{grade},term = #{term}," +
            "major = #{major},institute = #{institute},totalScore = #{totalScore}," +
            "type = #{type},tips = #{tips},startTime = #{startTime},endTime = #{endTime} where examCode = #{examCode}")
    int update(ExamManage exammanage);

    @Options(useGeneratedKeys = true, keyProperty = "examCode")
    @Insert("insert into exam_manage(description,source,paperId,examDate,totalTime,grade,term,major,institute,totalScore,type,tips)" +
            " values(#{description},#{source},#{paperId},#{examDate},#{totalTime},#{grade},#{term},#{major},#{institute},#{totalScore},#{type},#{tips})")
    int add(ExamManage exammanage);

    /**
     * 查询最后一条记录的paperId,返回给前端达到自增效果
     *
     * @return paperId
     */
    @Select("select paperId from exam_manage order by paperId desc limit 1")
    ExamManage findOnlyPaperId();
}
