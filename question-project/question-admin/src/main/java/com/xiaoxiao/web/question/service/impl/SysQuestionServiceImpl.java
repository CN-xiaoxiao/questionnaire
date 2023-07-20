package com.xiaoxiao.web.question.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiao.web.paper.entity.SysPaper;
import com.xiaoxiao.web.paper.mapper.SysPaperMapper;
import com.xiaoxiao.web.paperchoice.entity.SysPaperChoice;
import com.xiaoxiao.web.question.entity.SysQuestion;
import com.xiaoxiao.web.question.entity.SysQuestionParm;
import com.xiaoxiao.web.question.mapper.SysQuestionMapper;
import com.xiaoxiao.web.question.service.ISysQuestionService;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class SysQuestionServiceImpl implements ISysQuestionService {
    @Autowired
    private SysQuestionMapper questionMapper;

    @Autowired
    private SysPaperMapper sysPaperMapper;


    @Override
    public Boolean save(SysQuestion sysQuestion) {
        // 序号不能重复
        SysQuestion dataQuestion = questionMapper.selectQuestionByOrder(sysQuestion.getQuestionOrder());
        if (dataQuestion != null) {
            return false;
        }
        return questionMapper.save(sysQuestion);
    }

    @Override
    public Boolean updateById(SysQuestion sysQuestion) {
        SysQuestion dataQuestion = questionMapper.selectQuestionById(sysQuestion.getQuestionId());
        // 定义  序号不能修改
        if (!dataQuestion.getQuestionOrder().equals(sysQuestion.getQuestionOrder())) {
            return false;
        }
        return questionMapper.updateQuestionById(sysQuestion);
    }

    @Override
    public Boolean removeById(Integer questionId) {
        return questionMapper.removeById(questionId);
    }

    @Override
    public PageInfo<SysQuestion> selectQuestionInfoListByCondition(SysQuestionParm sysQuestionParm) {
        //1.分页助手开始分页
        PageHelper.startPage(sysQuestionParm.getCurrentPage(), sysQuestionParm.getPageSize());
        //2.调用dao层的select查询方法，第一个select方法会被分页
        List<SysQuestion> sysQuestions = questionMapper.selectByPageAndCondition(sysQuestionParm);
        //3。封装分页结果到PageInfo中
        return new PageInfo<>(sysQuestions, 10);
    }

    @Override
    public SysQuestion selectQuestionById(Integer questionId) {

        return questionMapper.selectQuestionById(questionId);
    }

    @Override
    public boolean exportQuestion(SysQuestion sysQuestion){

        // 1、创建world
        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = null;

        // 2、得到试题列表
        List<SysPaper> paperList = sysQuestion.getListPaper();

        // 设置标题
        XWPFParagraph titleParagraph = document.createParagraph();
        XWPFRun run1 = titleParagraph.createRun();

        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        run1.setText(sysQuestion.getQuestionTitle());
        run1.setFontSize(20);
        run1.setColor("000000");
        run1.setFontFamily("黑体");

        try {
            out = new FileOutputStream(
                    ("H://java//questionnaire//export-docx//"+ sysQuestion.getQuestionTitle()+".docx"));


            for (SysPaper sysPaper : paperList) {
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setFontFamily("仿宋");
                run.setFontSize(10);

                String paperOrder = sysPaper.getPaperOrder().toString();
                String paperTitle = sysPaper.getPaperTitle();
                run.setText(paperOrder + "、" + paperTitle);
                run.addCarriageReturn();

                // 单选或多选
                if (sysPaper.getPaperType().equals("1") || sysPaper.getPaperType().equals("2")) {
                    List<SysPaperChoice> choiceList = sysPaper.getPaperChoice();
                    for (SysPaperChoice sysPaperChoice : choiceList) {
                        String choiceOrder = numToAlphabet(sysPaperChoice.getChoiceOrder());
                        String choiceText = sysPaperChoice.getChoiceText();
                        run.setText(choiceOrder + "、" + choiceText);
                        run.addCarriageReturn();
                    }
                } else { // 单文本或多文本
                    for (int i = 0; i < 3; i++) {
                        run.addCarriageReturn();
                    }
                }

            }

            document.write(out);

            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return true;
    }

    private String numToAlphabet(Integer num) {
        // 选项不超过10个
        // ASCII表 1:  49   A:  65
        if (num > 9 || num < 1) {
            return "";
        }

        char a = (char) (Integer.toString(num).charAt(0) + 16);

        return Character.toString(a);
    }
}
