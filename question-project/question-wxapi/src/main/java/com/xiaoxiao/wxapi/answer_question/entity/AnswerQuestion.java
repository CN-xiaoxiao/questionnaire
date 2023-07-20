package com.xiaoxiao.wxapi.answer_question.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AnswerQuestion implements Serializable {
    private Integer answerId;
    private Integer questionId;
    private String openid;
    private Date createTime;
}
