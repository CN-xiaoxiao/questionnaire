package com.xiaoxiao.web.paper.controller;

import com.xiaoxiao.utils.ResultUtils;
import com.xiaoxiao.utils.ResultVo;
import com.xiaoxiao.web.paper.entity.SysPaper;
import com.xiaoxiao.web.paper.service.ISysPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paper")
public class SysPaperController {
    @Autowired
    private ISysPaperService sysPaperService;

    // 保存试题
    @PostMapping("/savePaper")
    public ResultVo savePaper(@RequestBody List<SysPaper> paperList) {
        sysPaperService.savePaper(paperList);
        return ResultUtils.success("设计试题成功！");
    }

    // 编辑回显
    @GetMapping("/getPaperList")
    public ResultVo getPaperList(Integer questionId){
        List<SysPaper> list = sysPaperService.getList(questionId);
        return ResultUtils.success("查询成功！", list);
    }
}
