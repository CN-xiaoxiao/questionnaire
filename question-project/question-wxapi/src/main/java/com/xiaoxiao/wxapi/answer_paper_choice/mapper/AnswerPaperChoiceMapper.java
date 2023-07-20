package com.xiaoxiao.wxapi.answer_paper_choice.mapper;

import com.xiaoxiao.wxapi.answer_paper_choice.entity.AnswerPaperChoice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnswerPaperChoiceMapper {
    void save(@Param("answerPaperChoice") AnswerPaperChoice answerPaperChoice);

    void saveList(@Param("choiceList") List<AnswerPaperChoice> choiceList);
}
