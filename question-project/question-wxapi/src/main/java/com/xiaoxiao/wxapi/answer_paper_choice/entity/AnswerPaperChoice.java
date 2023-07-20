package com.xiaoxiao.wxapi.answer_paper_choice.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AnswerPaperChoice implements Serializable {
    private Integer answerChoiceId;
    private Integer choiceId;
    private Integer paperId;
    private String openid;
//    private String paperValue;
}
