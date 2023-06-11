package com.exam.service.impl;

import com.exam.entity.Replay;
import com.exam.mapper.ReplayMapper;
import com.exam.service.ReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author PlutoWu
 * @date 2021/05/24
 */
@Service
public class ReplayServiceImpl implements ReplayService {

    @Autowired
    private ReplayMapper replayMapper;

    @Override
    public List<Replay> findAll() {
        return replayMapper.findAll();
    }

    @Override
    public List<Replay> findAllById(Integer messageId) {
        return replayMapper.findAllById(messageId);
    }

    /**
     * 通过 id 查询回复
     * @param replayId
     * @return
     */
    @Override
    public Replay findById(Integer replayId) {
        return replayMapper.findById(replayId);
    }

    @Override
    public int delete(Integer replayId) {
        return replayMapper.delete(replayId);
    }

    @Override
    public int update(Replay replay) {
        return replayMapper.update(replay);
    }

    @Override
    public int add(Replay replay) {
        return replayMapper.add(replay);
    }
}
