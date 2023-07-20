package com.xiaoxiao.wxapi.answer_paper.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AnswerPaper implements Serializable {
    private Integer answerPaperId;
    private Integer paperId;
    private String openid;
    private String paperType;
    private String paperValue;
}
