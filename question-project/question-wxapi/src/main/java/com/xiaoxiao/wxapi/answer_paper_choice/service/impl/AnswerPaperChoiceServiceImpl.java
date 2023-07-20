package com.xiaoxiao.wxapi.answer_paper_choice.service.impl;

import com.xiaoxiao.wxapi.answer_paper_choice.entity.AnswerPaperChoice;
import com.xiaoxiao.wxapi.answer_paper_choice.mapper.AnswerPaperChoiceMapper;
import com.xiaoxiao.wxapi.answer_paper_choice.service.IAnswerPaperChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerPaperChoiceServiceImpl implements IAnswerPaperChoiceService {
    @Autowired
    private AnswerPaperChoiceMapper answerPaperChoiceMapper;

    @Override
    public void save(AnswerPaperChoice answerPaperChoice) {
        if (answerPaperChoice == null) {
            return;
        }
        answerPaperChoiceMapper.save(answerPaperChoice);
    }

    @Override
    public void saveList(List<AnswerPaperChoice> choiceList) {
        if (choiceList.size() > 0) {
            answerPaperChoiceMapper.saveList(choiceList);
        }
    }
}
