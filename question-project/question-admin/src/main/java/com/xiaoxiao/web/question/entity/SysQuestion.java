package com.xiaoxiao.web.question.entity;

import com.xiaoxiao.web.paper.entity.SysPaper;
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
    private List<SysPaper> listPaper;
}
