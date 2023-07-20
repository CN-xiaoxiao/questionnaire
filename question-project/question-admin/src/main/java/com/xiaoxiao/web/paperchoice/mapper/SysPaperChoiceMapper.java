package com.xiaoxiao.web.paperchoice.mapper;

import com.xiaoxiao.web.paperchoice.entity.SysPaperChoice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPaperChoiceMapper {

    Boolean deletePaperChoiceByPaperId(@Param("paperId") Integer paperId);

    void savePaperChoice(SysPaperChoice sysPaperChoice);

    List<SysPaperChoice> selectPaperChoiceListByPaperId(@Param("paperId") Integer paperId);

    List<SysPaperChoice> getListPaperChoiceListByQuestionId(@Param("questionId") Integer questionId);
}
