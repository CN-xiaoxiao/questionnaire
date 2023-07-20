package com.xiaoxiao.web.question.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysQuestionParm implements Serializable {
    // 当前页
    private Integer currentPage;
    // 页容量
    private Integer pageSize;
    // 问卷标题
    private String questionTitle;
}
