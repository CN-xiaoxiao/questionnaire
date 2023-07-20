package com.xiaoxiao.wxapi.paperchoice.service;



import com.xiaoxiao.wxapi.paperchoice.entity.SysPaperChoice;

import java.util.List;

public interface ISysPaperChoiceService {

    List<SysPaperChoice> getPaperChoiceListByPaperId(Integer paperId);
}
