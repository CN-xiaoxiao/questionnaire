package com.xiaoxiao.wxapi.question.mapper;

import com.xiaoxiao.wxapi.question.entity.SysQuestion;
import com.xiaoxiao.wxapi.question.entity.SysQuestionParm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysQuestionMapper {

    List<SysQuestion> getList(SysQuestionParm sysQuestionParm);

    SysQuestion getQuestionByQuestionId(@Param("questionId") Integer questionId);


    List<SysQuestion> getMyQuestionList(@Param("sysQuestionParm") SysQuestionParm sysQuestionParm);
}
