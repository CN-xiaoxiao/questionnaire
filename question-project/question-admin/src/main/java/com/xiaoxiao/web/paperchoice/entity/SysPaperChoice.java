package com.xiaoxiao.web.paperchoice.entity;

import lombok.Data;

@Data
public class SysPaperChoice {
    // 选项id
    private Integer choiceId;
    // 试题id
    private Integer paperId;
    // 选项内容
    private String choiceText;
    // 选项序号
    private Integer choiceOrder;
    // 问卷id
    private Integer questionId;
    // 总条数
    private Integer total;
}
