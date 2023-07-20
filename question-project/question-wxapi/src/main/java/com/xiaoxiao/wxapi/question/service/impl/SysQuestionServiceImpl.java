package com.xiaoxiao.wxapi.question.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiao.wxapi.paper.entity.SysPaper;
import com.xiaoxiao.wxapi.paper.service.ISysPaperService;
import com.xiaoxiao.wxapi.paperchoice.entity.SysPaperChoice;
import com.xiaoxiao.wxapi.paperchoice.service.ISysPaperChoiceService;
import com.xiaoxiao.wxapi.question.entity.SysQuestion;
import com.xiaoxiao.wxapi.question.entity.SysQuestionParm;
import com.xiaoxiao.wxapi.question.mapper.SysQuestionMapper;
import com.xiaoxiao.wxapi.question.service.ISysQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysQuestionServiceImpl implements ISysQuestionService {

    @Autowired
    private SysQuestionMapper questionMapper;

    @Autowired
    private ISysPaperService sysPaperService;

    @Autowired
    private ISysPaperChoiceService sysPaperChoiceService;

    public PageInfo<SysQuestion> getList(SysQuestionParm sysQuestionParm) {
        //1.分页助手开始分页
        PageHelper.startPage(sysQuestionParm.getCurrentPage(), sysQuestionParm.getPageSize());
        //2.调用dao层的select查询方法，第一个select方法会被分页
        List<SysQuestion> sysQuestions = questionMapper.getList(sysQuestionParm);
        //3。封装分页结果到PageInfo中
        return new PageInfo<>(sysQuestions, 10);
    }

    @Override
    public SysQuestion getQuestionByQuestionId(Integer questionId) {
        return questionMapper.getQuestionByQuestionId(questionId);
    }

    @Override
    public PageInfo<SysQuestion> getMyQuestionList(SysQuestionParm sysQuestionParm) {
        //1.分页助手开始分页
        PageHelper.startPage(sysQuestionParm.getCurrentPage(), sysQuestionParm.getPageSize());
        //2.调用dao层的select查询方法，第一个select方法会被分页
        List<SysQuestion> sysQuestions = questionMapper.getMyQuestionList(sysQuestionParm);
        //3。封装分页结果到PageInfo中
        return new PageInfo<>(sysQuestions, 10);
    }

    @Override
    public SysQuestion getMyPaperList(Integer questionId, String openid) {
        // 根据问卷id查询问卷
        SysQuestion sysQuestion = questionMapper.getQuestionByQuestionId(questionId);
        // 根据问卷id查询试题列表
        List<SysPaper> paperList = sysPaperService.getPaperListByQuestionId(questionId);
        // 查询用户做的对应的试题列表
        List<SysPaper> myPaperList = sysPaperService.getMyPaperListByQuestionIdAndOpenid(questionId, openid);

        if (paperList.size() == 0) {
            return null;
        }

        for (SysPaper sysPaper : paperList) {
            for (SysPaper paper : myPaperList) {
                if (sysPaper.getPaperId().equals(paper.getPaperId())) {
                    // 设置用户的答案
                    sysPaper.setPaperValue(paper.getPaperValue());

                    // 判断是否是单选或多选
                    if (sysPaper.getPaperType().equals("1") || sysPaper.getPaperType().equals("2")) {

                        // 试题选项
                        List<SysPaperChoice> paperChoiceList = sysPaperChoiceService.getPaperChoiceListByPaperId(sysPaper.getPaperId());

                        for (SysPaperChoice sysPaperChoice : paperChoiceList) {
                            String[] split = paper.getPaperValue().split(",");
                            for (String s : split) {
                                // 设置选中的选项
                                if (sysPaperChoice.getChoiceId().equals(Integer.valueOf(s))) {
                                    sysPaperChoice.setChecked(true);
                                }
                            }
                        }
                        sysPaper.setPaperChoice(paperChoiceList);
                    }
                }
            }
        }
        sysQuestion.setPaperList(paperList);

        return sysQuestion;
    }
}
