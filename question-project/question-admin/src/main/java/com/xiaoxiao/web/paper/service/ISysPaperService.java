package com.xiaoxiao.web.paper.service;

import com.xiaoxiao.web.paper.entity.SysPaper;

import java.util.List;

public interface ISysPaperService {
    // 保存试题
    void savePaper(List<SysPaper> paperList);
    // 试题回显
    List<SysPaper> getList(Integer questionId);

}
