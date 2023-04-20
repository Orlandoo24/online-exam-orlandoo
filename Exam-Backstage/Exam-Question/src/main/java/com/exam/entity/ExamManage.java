package com.exam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author PlutoWu
 * @date 2021/05/24
 */
@Data
//@TableName("exam_manege")
public class ExamManage {

    private Integer examCode;

    private String description;

    private String source;

    private Integer paperId;

    private String examDate;

    private Integer totalTime;

    private String grade;

    private String term;

    private String major;

    private String institute;

    private Integer totalScore;

    private String type;

    private String tips;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}