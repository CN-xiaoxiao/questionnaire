package com.xiaoxiao.wxapi.answer_question.mapper;

import com.xiaoxiao.wxapi.answer_question.entity.AnswerQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AnswerQuestionMapper {
    AnswerQuestion getAnswerQuestionByQuestionIdAndOpenid(@Param("questionId") Integer questionId, @Param("openid") String openid);

    void insert(AnswerQuestion answerQuestion);
}
