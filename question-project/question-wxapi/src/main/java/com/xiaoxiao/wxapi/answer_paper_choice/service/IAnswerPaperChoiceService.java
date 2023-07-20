package com.xiaoxiao.wxapi.answer_paper_choice.service;

import com.xiaoxiao.wxapi.answer_paper_choice.entity.AnswerPaperChoice;

import java.util.List;

public interface IAnswerPaperChoiceService {
    void save(AnswerPaperChoice answerPaperChoice);

    void saveList(List<AnswerPaperChoice> choiceList);
}
