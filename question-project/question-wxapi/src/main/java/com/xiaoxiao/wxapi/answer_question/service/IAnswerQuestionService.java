package com.xiaoxiao.wxapi.answer_question.service;

import com.xiaoxiao.wxapi.answer_question.entity.AnswerQuestion;
import com.xiaoxiao.wxapi.answer_question.entity.CommitParm;

public interface IAnswerQuestionService {
    AnswerQuestion getAnswerQuestionByQuestionIdAndOpenid(Integer questionId, String openid);

    Boolean saveCommit(CommitParm parm);
}
