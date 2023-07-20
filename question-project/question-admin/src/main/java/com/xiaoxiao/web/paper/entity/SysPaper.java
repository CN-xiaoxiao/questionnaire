package com.xiaoxiao.web.paper.entity;

import com.xiaoxiao.web.paperchoice.entity.SysPaperChoice;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysPaper implements Serializable {
    // 试题id
    private Integer paperId;
    // 问卷id
    private Integer questionId;
    // 试题题目
    private String paperTitle;
    // 试题类型
    private String paperType;
    // 试题序号
    private Integer paperOrder;
    // 单选、多选试题对应的选项
    private List<SysPaperChoice> paperChoice;
}
