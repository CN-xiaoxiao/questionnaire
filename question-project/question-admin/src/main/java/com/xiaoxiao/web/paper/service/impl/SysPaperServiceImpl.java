package com.xiaoxiao.web.paper.service.impl;

import com.xiaoxiao.web.paper.entity.SysPaper;
import com.xiaoxiao.web.paper.mapper.SysPaperMapper;
import com.xiaoxiao.web.paper.service.ISysPaperService;
import com.xiaoxiao.web.paperchoice.entity.SysPaperChoice;
import com.xiaoxiao.web.paperchoice.mapper.SysPaperChoiceMapper;
import com.xiaoxiao.web.paperchoice.service.ISysPaperChoiceService;
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
    @Transactional
    public void savePaper(List<SysPaper> paperList) {
        // 先删除再保存
        // 删除试题(整个问卷)
        if (paperList.size() > 0) {
            // 找到问卷对应的所有试题
            List<SysPaper> paperListDao = sysPaperMapper.selectPaperListByQuestionId(paperList.get(0).getQuestionId());
            if (paperListDao.size() > 0) {
                for (SysPaper sysPaper : paperListDao) {
                    // 判断是否是单选、多选；需要删除对应的选项数据
                    if (sysPaper.getPaperType().equals("1") || sysPaper.getPaperType().equals("2")) {
                        // 删除选项
                        sysPaperChoiceService.deletePaperChoiceByPaperId(sysPaper.getPaperId());
                    }
                    sysPaperMapper.deletePaperByPaperId(sysPaper.getPaperId());
                }
            }
        }

        // 保存
        for (int k = 0; k < paperList.size(); k++) {
            // 先保存试题
            SysPaper sysPaper = new SysPaper();
            BeanUtils.copyProperties(paperList.get(k), sysPaper);
            // 试题序号
            sysPaper.setPaperOrder(k+1);
            // 保存试题
            sysPaperMapper.insertPaper(sysPaper);
            // 得到当前试题id
            int paperId = foundPaperId(sysPaper);
            // 判断是否是单选或多选
            if (paperList.get(k).getPaperType().equals("1") || paperList.get(k).getPaperType().equals("2") ) {
                // 批量保存，保存之前，设置试题的id
                paperList.get(k).getPaperChoice().forEach(item -> {
                    // 设置试题的id
                    item.setPaperId(paperId);
                });
                // 保存选项
                sysPaperChoiceService.savePaperChoice(paperList.get(k).getPaperChoice());
            }
        }
    }

    @Override
    public List<SysPaper> getList(Integer questionId) {
        // 试题列表
        List<SysPaper> paperList =  sysPaperMapper.selectPaperListByQuestionId(questionId);
        // 判断是否是单选或者多选
        if (paperList.size() > 0) {
            for (SysPaper sysPaper : paperList) {
                if (sysPaper.getPaperType().equals("1") || sysPaper.getPaperType().equals("2")) {
                    // 查询 选项
                    List<SysPaperChoice> paperChoiceList = sysPaperChoiceService.selectPaperChoiceListByPaperId(sysPaper.getPaperId());
                    sysPaper.setPaperChoice(paperChoiceList);
                }
            }
        }
        return paperList;
    }


    private Integer foundPaperId(SysPaper sysPaper) {
        SysPaper sysPaperDao = sysPaperMapper.selectPaper(sysPaper);
        return sysPaperDao.getPaperId();
    }
}
