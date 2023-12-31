package com.exam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.mapper.AnswerMapper;
import com.exam.service.AnswerService;
import com.exam.vo.AnswerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author PlutoWu
 * @date 2021/05/24
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public IPage<AnswerVO> findAll(Page<AnswerVO> page) {
        return answerMapper.findAll(page);
    }


    @Override
    public IPage<AnswerVO> findAllQuestion(Integer page, Integer size) {
        Page<AnswerVO> answerVOPage = new Page<>(page, size);
        IPage<AnswerVO> allQuestion = answerMapper.findAll(answerVOPage);
        return allQuestion;
    }
}
