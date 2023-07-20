package com.xiaoxiao.web.question.mapper;

import com.xiaoxiao.web.question.entity.SysQuestion;
import com.xiaoxiao.web.question.entity.SysQuestionParm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysQuestionMapper {

    List<SysQuestion> selectByPageAndCondition(SysQuestionParm sysQuestionParm);

    Boolean updateQuestionById(SysQuestion sysQuestion);

    SysQuestion selectQuestionById(@Param("questionId") Integer questionId);

    Boolean removeById(@Param("questionId") Integer questionId);

    Boolean save(SysQuestion sysQuestion);

    SysQuestion selectQuestionByOrder(@Param("questionOrder") Integer questionOrder);
}
