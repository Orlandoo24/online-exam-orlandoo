package com.exam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ExamManage;
import com.exam.mapper.ExamManageMapper;
import com.exam.mapper.PaperMapper;
import com.exam.service.ExamManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

/**
 * @author PlutoWu
 * @date 2021/05/24
 */
@Service
public class ExamManageServiceImpl implements ExamManageService {
    @Autowired
    private ExamManageMapper examManageMapper;

    @Autowired
    private PaperMapper paperMapper;


    @Override
    public IPage<ExamManage> findAll(Page<ExamManage> page) {
        return examManageMapper.findAll(page);
    }

    //查询所有考试
    @Override
    public List<ExamManage> findAllExam() {
        return examManageMapper.findAllExam();
    }
    //分页查询所有考试
    @Override
    public IPage<ExamManage> findAllExam(Page<ExamManage> page) {
        return examManageMapper.findAllExam(page);
    }

    //查询所有练习
    @Override
    public List<ExamManage> findAllPractices() {
        return examManageMapper.findAllPractices();
    }
    //分页查询所有练习
    @Override
    public IPage<ExamManage> findAllPractices(Page<ExamManage> page) {
        return examManageMapper.findAllPractices(page);
    }



    @Override
    public ExamManage findById(Integer examCode) {
        return examManageMapper.findById(examCode);
    }

    @Override
    public int delete(Integer examCode) {
        return examManageMapper.delete(examCode);
    }

    @Override
    public int update(ExamManage exammanage) {
        return examManageMapper.update(exammanage);
    }

    @Override
    public int add(ExamManage exammanage) {
        return examManageMapper.add(exammanage);
    }

    @Override
    public ExamManage findOnlyPaperId() {
        return examManageMapper.findOnlyPaperId();
    }
}
