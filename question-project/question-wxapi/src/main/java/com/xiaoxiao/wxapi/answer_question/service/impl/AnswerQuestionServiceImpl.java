package com.xiaoxiao.wxapi.answer_question.service.impl;

import com.xiaoxiao.wxapi.answer_paper.entity.AnswerPaper;
import com.xiaoxiao.wxapi.answer_paper.service.IAnswerPaperService;
import com.xiaoxiao.wxapi.answer_paper_choice.entity.AnswerPaperChoice;
import com.xiaoxiao.wxapi.answer_paper_choice.service.IAnswerPaperChoiceService;
import com.xiaoxiao.wxapi.answer_question.entity.AnswerQuestion;
import com.xiaoxiao.wxapi.answer_question.entity.CommitParm;
import com.xiaoxiao.wxapi.answer_question.entity.PaperParm;
import com.xiaoxiao.wxapi.answer_question.mapper.AnswerQuestionMapper;
import com.xiaoxiao.wxapi.answer_question.service.IAnswerQuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AnswerQuestionServiceImpl implements IAnswerQuestionService {
    @Autowired
    private AnswerQuestionMapper answerQuestionMapper;
    @Autowired
    private IAnswerPaperChoiceService answerPaperChoiceService;
    @Autowired
    private IAnswerPaperService answerPaperService;

    @Override
    public AnswerQuestion getAnswerQuestionByQuestionIdAndOpenid(Integer questionId, String openid) {
        if (questionId != null && openid != null) {
            return answerQuestionMapper.getAnswerQuestionByQuestionIdAndOpenid(questionId, openid);
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean saveCommit(CommitParm parm) {

        if (parm.getPaperList().size() == 0) return false;

        // 保存到answer_question表
        AnswerQuestion answerQuestion = new AnswerQuestion();
        answerQuestion.setOpenid(parm.getOpenid());
        answerQuestion.setQuestionId(parm.getQuestionId());
        answerQuestion.setCreateTime(new Date());
        answerQuestionMapper.insert(answerQuestion);
        // 保存answer_paper表
        List<PaperParm> paperParmList = parm.getPaperList();
        List<AnswerPaper> list = new ArrayList<>();
        for (PaperParm paperParm : paperParmList) {
            AnswerPaper answerPaper = new AnswerPaper();

            BeanUtils.copyProperties(paperParm, answerPaper);
            answerPaper.setOpenid(parm.getOpenid());

            list.add(answerPaper);

            // 单选
            if (paperParm.getPaperType().equals("1")) {
                AnswerPaperChoice answerPaperChoice = new AnswerPaperChoice();

                answerPaperChoice.setOpenid(parm.getOpenid());
                answerPaperChoice.setPaperId(paperParm.getPaperId());
                answerPaperChoice.setChoiceId(Integer.valueOf(paperParm.getPaperValue()));

                answerPaperChoiceService.save(answerPaperChoice);
            } else if (paperParm.getPaperType().equals("2")) {   // 多选
                String regx = ",";
                String[] strings = paperParm.getPaperValue().split(regx);
                List<AnswerPaperChoice> choiceList = new ArrayList<>();

                for (String string : strings) {
                    AnswerPaperChoice answerPaperChoice = new AnswerPaperChoice();

                    answerPaperChoice.setOpenid(parm.getOpenid());
                    answerPaperChoice.setPaperId(paperParm.getPaperId());
                    answerPaperChoice.setChoiceId(Integer.valueOf(string));

                    choiceList.add(answerPaperChoice);
                }

                answerPaperChoiceService.saveList(choiceList);
            }
        }

        answerPaperService.saveList(list);

        return true;
    }
}
