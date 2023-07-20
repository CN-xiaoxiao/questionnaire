package com.xiaoxiao.web.paperchoice.service.impl;

import com.xiaoxiao.web.paperchoice.entity.SysPaperChoice;
import com.xiaoxiao.web.paperchoice.mapper.SysPaperChoiceMapper;
import com.xiaoxiao.web.paperchoice.service.ISysPaperChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class sysPaperChoiceServiceImpl implements ISysPaperChoiceService {
    @Autowired
    private SysPaperChoiceMapper sysPaperChoiceMapper;

    @Override
    public Boolean deletePaperChoiceByPaperId(Integer paperId) {
        return sysPaperChoiceMapper.deletePaperChoiceByPaperId(paperId);
    }

    @Override
    public void savePaperChoice(List<SysPaperChoice> paperChoiceList) {
        for (SysPaperChoice sysPaperChoice : paperChoiceList) {
            sysPaperChoiceMapper.savePaperChoice(sysPaperChoice);
        }
    }

    @Override
    public List<SysPaperChoice> selectPaperChoiceListByPaperId(Integer paperId) {
        return sysPaperChoiceMapper.selectPaperChoiceListByPaperId(paperId);
    }

    @Override
    public List<SysPaperChoice> getListPaperChoiceListByQuestionId(Integer questionId) {
        return sysPaperChoiceMapper.getListPaperChoiceListByQuestionId(questionId);
    }
}
