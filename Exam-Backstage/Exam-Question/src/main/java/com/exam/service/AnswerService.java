package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.vo.AnswerVO;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author PlutoWu
 * @date 2021/05/24
 */
public interface AnswerService {

    IPage<AnswerVO> findAll(Page<AnswerVO> page);

    IPage<AnswerVO> findAllQuestion(Integer page, Integer size);

}
