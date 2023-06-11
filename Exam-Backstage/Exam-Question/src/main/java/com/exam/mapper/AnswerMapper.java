package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.vo.AnswerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface AnswerMapper {
//    @Select("select question, subject, score, section,level, \"选择题\" as type from multi_question " +
//            "union select  question, subject, score, section,level, \"判断题\" as type  from judge_question " +
//            "union select  question, subject, score, section,level, \"填空题\" as type from fill_question")

    /**
     * SQL 翻译：
     *     从 multi_question 表中选择 question, subject, score, section, level 这几列，并在结果集中添加一列名为 type，值为 "选择题" 的字符串常量。
     * 联合（UNION）上述查询结果和：
     *     从 judge_question 表中选择 question, subject, score, section, level 这几列，并在结果集中添加一列名为 type，值为 "判断题" 的字符串常量。
     * 联合（UNION）以上查询结果和：
     *     从 fill_question  表中选择 question, subject, score, section, level 这几列，并在结果集中添加一列名为 type，值为 "填空题" 的字符串常量。
     * 最后，对整个查询结果进行去重并按照 question 列进行排序。
     *
     * @param page
     * @return
     */
    @Select("select question, subject, score, section, level, '选择题' as type from multi_question\n" +
            "union select question, subject, score, section, level, '判断题' as type from judge_question\n" +
            "union select question, subject, score, section, level, '填空题' as type from fill_question\n")
    IPage<AnswerVO> findAll(Page page);
}
