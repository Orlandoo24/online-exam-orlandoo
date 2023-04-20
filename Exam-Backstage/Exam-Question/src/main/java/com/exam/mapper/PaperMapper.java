package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.PaperManage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author PlutoWu
 * @date 2021/05/24
 */
@Mapper
public interface PaperMapper extends BaseMapper<PaperManage> {
    @Select("select paperId, questionType,questionId from paper_manage")
    List<PaperManage> findAll();

    @Select("select paperId, questionType,questionId from paper_manage where paperId = #{paperId}")
    List<PaperManage> findById(Integer paperId);

    @Insert("insert into paper_manage(paperId,questionType,questionId) values " +
            "(#{paperId},#{questionType},#{questionId})")
    int add(PaperManage paperManage);

    //删除试卷
    @Delete("delete from paper_manage where paperId = #{paperId}")
    int delete(Integer paperId);
}
