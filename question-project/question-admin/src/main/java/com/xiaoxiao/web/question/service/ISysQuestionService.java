package com.xiaoxiao.web.question.service;

import com.github.pagehelper.PageInfo;
import com.xiaoxiao.web.question.entity.SysQuestion;
import com.xiaoxiao.web.question.entity.SysQuestionParm;

public interface ISysQuestionService {
    Boolean save(SysQuestion sysQuestion);

    Boolean updateById(SysQuestion sysQuestion);

    Boolean removeById(Integer questionId);

    PageInfo<SysQuestion> selectQuestionInfoListByCondition(SysQuestionParm sysQuestionParm);

    SysQuestion selectQuestionById(Integer questionId);

    boolean exportQuestion(SysQuestion sysQuestion);
}
