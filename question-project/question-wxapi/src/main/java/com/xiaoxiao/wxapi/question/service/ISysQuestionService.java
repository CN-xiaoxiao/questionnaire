package com.xiaoxiao.wxapi.question.service;


import com.github.pagehelper.PageInfo;
import com.xiaoxiao.wxapi.question.entity.SysQuestion;
import com.xiaoxiao.wxapi.question.entity.SysQuestionParm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISysQuestionService {

    PageInfo<SysQuestion> getList(SysQuestionParm sysQuestionParm);

    SysQuestion getQuestionByQuestionId(Integer questionId);

    PageInfo<SysQuestion> getMyQuestionList(SysQuestionParm sysQuestionParm);

    // 问卷回显（答卷）
    SysQuestion getMyPaperList(Integer questionId, String openid);
}
