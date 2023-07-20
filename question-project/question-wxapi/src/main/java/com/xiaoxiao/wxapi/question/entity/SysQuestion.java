package com.xiaoxiao.wxapi.question.entity;

import com.xiaoxiao.wxapi.paper.entity.SysPaper;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysQuestion implements Serializable {
    // 问卷主键id
    private Integer questionId;
    // 问卷标题
    private String questionTitle;
    // 问卷描述
    private String questionDesc;
    // 问卷图片地址
    private String questionImg;
    // 问卷序号
    private Integer questionOrder;
    // 问卷状态 0：关闭 1：开放
    private String questionStatus;
    // 参与人数
    private Integer joinCount;
    // 是否已答题
    private String status;
    // 问卷对应的试题列表
    private List<SysPaper> paperList;
}
