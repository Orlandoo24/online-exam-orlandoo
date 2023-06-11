package com.exam.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.constant.RedisKeyConstant;
import com.exam.entity.ExamManage;
import com.exam.mapper.ExamManageMapper;
import com.exam.mapper.PaperMapper;
import com.exam.service.ExamManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.exam.constant.RedisKeyConstant.NULL_CACHE_JSONString;

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


    @Resource
    private StringRedisTemplate stringRedisTemplate;


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

    /**
     * 通过考试examCode查询试卷
     */
    @Override
    public ExamManage findById(Integer examCode) {
        //拼接key
        String key = RedisKeyConstant.EXAM_MANAGE + examCode;
        //从redis查询考试缓存
        String examCacheJson = stringRedisTemplate.opsForValue().get(key);
        //判断缓存是否存在
        if (StrUtil.isNotBlank(examCacheJson)) {
            //存在则将 JOSONString 转化为 ExamManage 返回
            ExamManage examCache = JSONUtil.toBean(examCacheJson, ExamManage.class);
            return examCache;
        }
        //不存在，则查询 DataBase
        ExamManage examDataBase = examManageMapper.findById(examCode);
        if (examDataBase != null ) {
            //DataBase 中存在则写入缓存 , 并且设置过期时间
            String examNewJson = JSONUtil.toJsonStr(examDataBase);
            stringRedisTemplate.opsForValue().set(key, examNewJson , 7, TimeUnit.DAYS);
        } else {
            // 不存在，写入空值，解决内存穿透问题
            stringRedisTemplate
                    .opsForValue()
                    .set(key, NULL_CACHE_JSONString, RedisKeyConstant.NULL_CACHE_TTL, TimeUnit.HOURS);
       }
        //返回 ExamManage
        return examDataBase;
    }
    /**
     * 考试删除实现
     * @param examCode
     * @return Boolean
     */
    @Override
    @Transactional
    public Boolean delete(Integer examCode) {
        if (examCode == null) {
            return null;
        }
        //先更新数据库，进行删除操作
        Boolean dataBaseRes = false;
        int delete = examManageMapper.delete(examCode);
        if (delete == 1) {
            dataBaseRes = true;
        }
        //再删除缓存
        String key = RedisKeyConstant.EXAM_MANAGE + examCode;
        Boolean redisRes = stringRedisTemplate.delete(key);
        return dataBaseRes && redisRes;
    }

    /**
     * 考试信息编辑实现
     * @param examManage
     * @return Boolean
     */
    @Override
    @Transactional
    public Boolean update(ExamManage examManage) {
        //先判断有无考试
        Integer examCode = examManage.getExamCode();
        if (examCode == null) {
            return null;
        }
        //先更新数据库
        Boolean dataBaseRes = false;
        int update = examManageMapper.update(examManage);
        if (update == 1) {
            dataBaseRes = true;
        }
        //再删除缓存
        String key = RedisKeyConstant.EXAM_MANAGE + examCode;
        Boolean redisRes = stringRedisTemplate.delete(key);
        return dataBaseRes && redisRes;
    }

    @Override
    public int add(ExamManage examManage) {
        int add = examManageMapper.add(examManage);
        return add;
    }

    @Override
    public ExamManage findOnlyPaperId() {
        return examManageMapper.findOnlyPaperId();
    }

}
