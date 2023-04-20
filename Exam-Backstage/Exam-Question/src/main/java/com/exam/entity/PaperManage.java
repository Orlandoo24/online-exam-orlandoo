package com.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author PlutoWu
 * @date 2021/05/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperManage {

    private Integer paperId;

    private Integer questionType;

    private Integer questionId;

}