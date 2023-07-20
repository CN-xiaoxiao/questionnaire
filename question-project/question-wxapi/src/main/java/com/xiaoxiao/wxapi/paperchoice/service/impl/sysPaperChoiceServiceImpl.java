package com.xiaoxiao.wxapi.paperchoice.service.impl;


import com.xiaoxiao.wxapi.paperchoice.entity.SysPaperChoice;
import com.xiaoxiao.wxapi.paperchoice.mapper.SysPaperChoiceMapper;
import com.xiaoxiao.wxapi.paperchoice.service.ISysPaperChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class sysPaperChoiceServiceImpl implements ISysPaperChoiceService {

    @Autowired
    private SysPaperChoiceMapper sysPaperChoiceMapper;
    @Override
    public List<SysPaperChoice> getPaperChoiceListByPaperId(Integer paperId) {
        return sysPaperChoiceMapper.selectPaperChoiceListByPaperId(paperId);
    }
}
