package com.xiaoxiao.wxapi.paper.service;



import com.xiaoxiao.wxapi.paper.entity.SysPaper;

import java.util.List;

public interface ISysPaperService {

    List<SysPaper> getPaperListByQuestionId(Integer questionId);

    List<SysPaper> getMyPaperListByQuestionIdAndOpenid(Integer questionId, String openid);
}
