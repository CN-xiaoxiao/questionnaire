package com.xiaoxiao.wxapi.answer_paper.mapper;

import com.xiaoxiao.wxapi.answer_paper.entity.AnswerPaper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnswerPaperMapper {
    void saveList(@Param("list") List<AnswerPaper> list);
}
