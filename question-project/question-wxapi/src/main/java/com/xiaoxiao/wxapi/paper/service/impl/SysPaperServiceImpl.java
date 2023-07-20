package com.xiaoxiao.wxapi.paper.service.impl;

import com.xiaoxiao.wxapi.paper.entity.SysPaper;
import com.xiaoxiao.wxapi.paper.mapper.SysPaperMapper;
import com.xiaoxiao.wxapi.paper.service.ISysPaperService;
import com.xiaoxiao.wxapi.paperchoice.entity.SysPaperChoice;
import com.xiaoxiao.wxapi.paperchoice.service.ISysPaperChoiceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysPaperServiceImpl implements ISysPaperService {

    @Autowired
    private SysPaperMapper sysPaperMapper;
    @Autowired
    private ISysPaperChoiceService sysPaperChoiceService;

    @Override
    public List<SysPaper> getPaperListByQuestionId(Integer questionId) {
        // 试题列表
        List<SysPaper> paperList =  sysPaperMapper.selectPaperListByQuestionId(questionId);
        // 判断是否是单选或者多选
        if (paperList.size() > 0) {
            for (SysPaper sysPaper : paperList) {
                if (sysPaper.getPaperType().equals("1") || sysPaper.getPaperType().equals("2")) {
                    // 查询 选项
                    List<SysPaperChoice> paperChoiceList = sysPaperChoiceService.getPaperChoiceListByPaperId(sysPaper.getPaperId());
                    sysPaper.setPaperChoice(paperChoiceList);
                }
            }
        }
        return paperList;
    }

    @Override
    public List<SysPaper> getMyPaperListByQuestionIdAndOpenid(Integer questionId, String openid) {

        return sysPaperMapper.getMyPaperList(questionId, openid);
    }
}
