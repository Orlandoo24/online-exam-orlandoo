package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ApiResult;
import com.exam.entity.ExamManage;
import com.exam.mapper.ExamManageMapper;
import com.exam.mapper.PaperMapper;
import com.exam.service.ExamManageService;
import com.exam.service.impl.ExamManageServiceImpl;
import com.exam.util.ApiResultHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author PlutoWu
 * @date 2021/05/24
 */
@Log4j2
@RestController
public class ExamManageController {

    @Autowired
    private ExamManageService examManageService;

    @Autowired
    private PaperMapper paperMapper;

    @Autowired
    private ExamManageMapper examManageMapper;

    //不分页获取所有考试和练习信息（教师端查询）
    @GetMapping("/all/{page}/{size}")
    public ApiResult findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        System.out.println("分页查询所有试卷和练习");
        ApiResult apiResult;
        Page<ExamManage> examManage = new Page<>(page, size);
        IPage<ExamManage> all = examManageService.findAll(examManage);
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", all);
        return apiResult;
    }

    //不分页获取所有考试信息
    @GetMapping("/exams")
    public ApiResult findAllExam() {
        System.out.println("不分页查询所有试卷");
        ApiResult apiResult;
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", examManageService.findAllExam());
        return apiResult;
    }

    //分页获取当前所有考试信息
    @GetMapping("/exams/{page}/{size}")
    public ApiResult findAllExam(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        System.out.println("分页查询所有试卷");
        ApiResult apiResult;
        Page<ExamManage> examManage = new Page<>(page, size);
        IPage<ExamManage> all = examManageService.findAllExam(examManage);
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", all);
        return apiResult;
    }

    //不分页获取所有练习信息
    @GetMapping("/practices")
    public ApiResult findAllPractices() {
        System.out.println("不分页查询所有练习");
        ApiResult apiResult;
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", examManageService.findAllPractices());
        return apiResult;
    }

    //分页获取当前所有练习信息
    @GetMapping("/practices/{page}/{size}")
    public ApiResult findAllPractices(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        System.out.println("分页查询所有练习");
        ApiResult apiResult;
        Page<ExamManage> examManage = new Page<>(page, size);
        IPage<ExamManage> all = examManageService.findAllPractices(examManage);
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", all);
        return apiResult;
    }

    @GetMapping("/exam/{examCode}")
    public ApiResult findById(@PathVariable("examCode") Integer examCode) {
        System.out.println("根据ID查找");
        ExamManage res = examManageService.findById(examCode);
        if (res == null) {
            return ApiResultHandler.buildApiResult(10000, "考试编号不存在", null);
        }
        return ApiResultHandler.buildApiResult(200, "开始考试请求成功！", res);
    }

    @DeleteMapping("/exam/{examCode}")
    public ApiResult deleteById(@PathVariable("examCode") Integer examCode) {

        //将试卷进行删除
        ExamManage examManage = examManageMapper.findById(examCode);
        String examName = examManage.getSource();
        Integer paperId = examManage.getPaperId();
        int delPaperRes = paperMapper.delete(paperId);
        log.info("将试卷{}删除", delPaperRes);

        //将考试进行删除
        Boolean delExamRes = examManageService.delete(examCode);
        log.info("删除{}场考试", 1);
        if (delExamRes == true){
            return ApiResultHandler.buildApiResult(200, "考试删除成功", delExamRes);
        } else {
          return  ApiResultHandler.buildApiResult(10000, "考试删除失败", delExamRes);
        }
    }

    @PutMapping("/exam")
    public ApiResult update(@RequestBody ExamManage exammanage) {
        Boolean res;
        try {
            res = examManageService.update(exammanage);
            log.info("编辑更新成功：{}", res);
        } catch (Exception e) {
            log.info("编辑更新异常：{}", e);
            return ApiResultHandler.buildApiResult(20000,"请求参数错误", e);
        }
        System.out.print("更新操作执行---");
        return ApiResultHandler.buildApiResult(200, "更新成功", res);
    }

    @PostMapping("/exam")
    public ApiResult add(@RequestBody ExamManage exammanage) {
        int res = examManageService.add(exammanage);
        if (res == 1) {
            return ApiResultHandler.buildApiResult(200, "添加成功", res);
        } else {
            return ApiResultHandler.buildApiResult(400, "添加失败", res);
        }
    }

    @GetMapping("/examManagePaperId")
    public ApiResult findOnlyPaperId() {
        ExamManage res = examManageService.findOnlyPaperId();
        if (res != null) {
            return ApiResultHandler.buildApiResult(200, "请求成功", res);
        }
        return ApiResultHandler.buildApiResult(400, "请求失败", res);
    }
}
