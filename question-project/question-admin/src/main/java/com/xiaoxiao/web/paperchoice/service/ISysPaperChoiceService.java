package com.xiaoxiao.web.paperchoice.service;

import com.xiaoxiao.web.paperchoice.entity.SysPaperChoice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISysPaperChoiceService {
    Boolean deletePaperChoiceByPaperId(Integer paperId);

    void savePaperChoice(List<SysPaperChoice> paperChoice);

    List<SysPaperChoice> selectPaperChoiceListByPaperId(Integer paperId);

    List<SysPaperChoice> getListPaperChoiceListByQuestionId(Integer questionId);
}
