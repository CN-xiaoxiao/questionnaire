package com.xiaoxiao.wxapi.answer_question.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommitParm {
    private String openid;
    private List<PaperParm> paperList = new ArrayList<>();
    private Integer questionId;
}
