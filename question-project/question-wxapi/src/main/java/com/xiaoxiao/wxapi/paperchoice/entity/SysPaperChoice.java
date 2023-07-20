package com.xiaoxiao.wxapi.paperchoice.entity;

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
    // 是否选中
    private Boolean checked;
}
