package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ExamManage;

import java.util.List;

/**
 * @author PlutoWu
 * @date 2021/05/24
 */
public interface ExamManageService {


    /**
     * 分页查询所有考试和练习信息
     */
    IPage<ExamManage> findAll(Page<ExamManage> page);

    /**
     * 不分页查询所有考试信息
     */
    List<ExamManage> findAllExam();

    /**
     * 不分页查询所有练习信息
     */
    List<ExamManage> findAllPractices();

    //分页查询所有练习信息
    IPage<ExamManage> findAllPractices(Page<ExamManage> page);

    IPage<ExamManage> findAllExam(Page<ExamManage> page);

    ExamManage findById(Integer examCode);

    Boolean delete(Integer examCode);

    Boolean update(ExamManage examManage);

    int add(ExamManage examManage);

    ExamManage findOnlyPaperId();


}
