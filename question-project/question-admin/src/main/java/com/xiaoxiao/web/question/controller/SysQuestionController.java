package com.xiaoxiao.web.question.controller;

import com.github.pagehelper.PageInfo;
import com.xiaoxiao.utils.ResultUtils;
import com.xiaoxiao.utils.ResultVo;
import com.xiaoxiao.web.paper.entity.SysPaper;
import com.xiaoxiao.web.paper.service.ISysPaperService;
import com.xiaoxiao.web.paperchoice.entity.SysPaperChoice;
import com.xiaoxiao.web.paperchoice.service.ISysPaperChoiceService;
import com.xiaoxiao.web.question.entity.SysQuestion;
import com.xiaoxiao.web.question.entity.SysQuestionParm;
import com.xiaoxiao.web.question.service.ISysQuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 问卷控制器
 */
@RestController
@RequestMapping("api/question")
public class SysQuestionController {
    @Autowired
    private ISysQuestionService sysQuestionService;
    @Autowired
    private ISysPaperService sysPaperService;
    @Autowired
    private ISysPaperChoiceService sysPaperChoiceService;

    /**
     * 新增问卷
     * @param sysQuestion
     * @return
     */
    @PostMapping
    public ResultVo add(@RequestBody SysQuestion sysQuestion) {
        Boolean flag = sysQuestionService.save(sysQuestion);

        if (flag) {
            return ResultUtils.success("新增问卷成功！");
        }

        return ResultUtils.error("新增问卷失败！");
    }

    /**
     * 编辑问卷
     * @param sysQuestion
     * @return
     */
    @PutMapping
    public ResultVo edit(@RequestBody SysQuestion sysQuestion) {
        Boolean flag = sysQuestionService.updateById(sysQuestion);

        if (flag) {
            return ResultUtils.success("编辑问卷成功！");
        }

        return ResultUtils.error("编辑问卷失败！");
    }

    /**
     * 删除问卷
     * @param questionId
     * @return
     */
    @DeleteMapping("/{questionId}")
    public ResultVo delete(@PathVariable("questionId") Integer questionId) {
        Boolean flag = sysQuestionService.removeById(questionId);

        if (flag) {
            return ResultUtils.success("删除问卷成功！");
        }

        return ResultUtils.error("删除问卷失败！");
    }

    /**
     * 分页查询
     * @param sysQuestionParm
     * @return
     */
    @GetMapping("/list")
    public ResultVo getListByCondition(SysQuestionParm sysQuestionParm) {
        PageInfo<SysQuestion> sysQuestionPageInfo = sysQuestionService.selectQuestionInfoListByCondition(sysQuestionParm);

        return ResultUtils.success("查询成功", sysQuestionPageInfo);
    }

    @GetMapping("/getTotalList")
    public ResultVo getTotalList(Integer questionId) {
        // 1、查询问卷详情
        SysQuestion sysQuestion = sysQuestionService.selectQuestionById(questionId);
        // 2、查询问卷的所有试题
        List<SysPaper> paperList = sysPaperService.getList(questionId);
        // 3、查询试题对应的选项统计
        List<SysPaperChoice> paperChoiceList = sysPaperChoiceService.getListPaperChoiceListByQuestionId(questionId);

        for (SysPaper sysPaper : paperList) {
            List<SysPaperChoice> paperChoice = new ArrayList<>();
            Integer paperId = sysPaper.getPaperId();
            paperChoiceList.stream().filter(item -> item.getPaperId().equals(paperId)).forEach(item -> {
                SysPaperChoice choice = new SysPaperChoice();
                BeanUtils.copyProperties(item, choice);
                paperChoice.add(choice);
            });
            // 设置单选、多选的选项
            sysPaper.setPaperChoice(paperChoice);
        }
        sysQuestion.setListPaper(paperList);
        return ResultUtils.success("查询成功！", sysQuestion);
    }

    @GetMapping("/exportQuestion")
    public ResultVo exportQuestion(Integer questionId) {
        // 查询到该问卷
        SysQuestion sysQuestion = sysQuestionService.selectQuestionById(questionId);

        if (sysQuestion == null) {
            return ResultUtils.error("查询失败！");
        }

        // 查询到该问卷的试题
        List<SysPaper> paperList = sysPaperService.getList(questionId);

        if (paperList == null) {
            return ResultUtils.error("查询失败！");
        }

        sysQuestion.setListPaper(paperList);

        boolean flag = sysQuestionService.exportQuestion(sysQuestion);

        if (flag) {
            return ResultUtils.success("导出成功！");
        }

        return ResultUtils.error("导出失败！");
    }


}
