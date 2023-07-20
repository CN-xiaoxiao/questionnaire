package com.xiaoxiao.wxapi.answer_question.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaperParm implements Serializable {
    private Integer paperId;
    private String paperType;
    private String paperValue;
    private Integer questionId;
}
