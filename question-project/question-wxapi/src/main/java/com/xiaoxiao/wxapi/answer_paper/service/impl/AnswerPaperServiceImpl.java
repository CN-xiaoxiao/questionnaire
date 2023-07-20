package com.xiaoxiao.wxapi.answer_paper.service.impl;

import com.xiaoxiao.wxapi.answer_paper.entity.AnswerPaper;
import com.xiaoxiao.wxapi.answer_paper.mapper.AnswerPaperMapper;
import com.xiaoxiao.wxapi.answer_paper.service.IAnswerPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerPaperServiceImpl implements IAnswerPaperService {
    @Autowired
    private AnswerPaperMapper answerPaperMapper;

    @Override
    public void saveList(List<AnswerPaper> list) {
        System.out.println(list.size());
        if (list.size() > 0) {
            answerPaperMapper.saveList(list);
        }
    }
}
