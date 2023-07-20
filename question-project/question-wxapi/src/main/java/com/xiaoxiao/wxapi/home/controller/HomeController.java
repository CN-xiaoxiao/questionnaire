package com.xiaoxiao.wxapi.home.controller;

import com.github.pagehelper.PageInfo;
import com.xiaoxiao.utils.ResultUtils;
import com.xiaoxiao.utils.ResultVo;
import com.xiaoxiao.wxapi.answer_question.entity.AnswerQuestion;
import com.xiaoxiao.wxapi.answer_question.entity.CommitParm;
import com.xiaoxiao.wxapi.answer_question.service.IAnswerQuestionService;
import com.xiaoxiao.wxapi.paper.entity.SysPaper;
import com.xiaoxiao.wxapi.paper.service.ISysPaperService;
import com.xiaoxiao.wxapi.question.entity.SysQuestion;
import com.xiaoxiao.wxapi.question.entity.SysQuestionParm;
import com.xiaoxiao.wxapi.question.service.ISysQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小程序首页控制器
 */
@RestController
@RequestMapping("/wxapi/home")
public class HomeController {
    @Autowired
    private ISysQuestionService questionService;
    @Autowired
    private IAnswerQuestionService answerQuestionService;
    @Autowired
    private ISysPaperService paperService;

    /**
     * 查询首页列表
     * @param sysQuestionParm
     * @return
     */
    @GetMapping("/getList")
    public ResultVo getList(SysQuestionParm sysQuestionParm) {
        PageInfo<SysQuestion> sysQuestionPageInfo = questionService.getList(sysQuestionParm);

        return ResultUtils.success("查询成功", sysQuestionPageInfo);
    }

    /**
     * 问卷详情查询
     * @param questionId
     * @param openid
     * @return
     */
    @GetMapping("/getDetails")
    public ResultVo getDetails(Integer questionId, String openid) {
        // 1、根据问卷id查询
        SysQuestion question = questionService.getQuestionByQuestionId(questionId);
        // 2、根据问卷id和openid查询
        AnswerQuestion answerQuestion = answerQuestionService.getAnswerQuestionByQuestionIdAndOpenid(questionId, openid);
        if (answerQuestion != null) {
            question.setStatus("1"); // 已答卷
        } else {
            question.setStatus("0"); // 未答卷
        }

        return ResultUtils.success("查询成功！", question);
    }

    /**
     * 根据问卷id查询试题列表
     * @param questionId
     * @return
     */
    @GetMapping("/getPaperList")
    public ResultVo getPaperList(Integer questionId) {
        // 1、查询问卷
        SysQuestion question = questionService.getQuestionByQuestionId(questionId);
        // 2、根据问卷id查询试题列表
        List<SysPaper> paperList = paperService.getPaperListByQuestionId(questionId);

        question.setPaperList(paperList);

        return ResultUtils.success("查询成功！", question);
    }

    /**
     * 提交保存问卷
     * @param parm
     * @return
     */
    @PostMapping("/saveCommit")
    public ResultVo saveCommit(@RequestBody CommitParm parm) {
        Boolean flag = answerQuestionService.saveCommit(parm);

        if (flag) {
            return ResultUtils.success("提交成功！");
        }
        return ResultUtils.error("提交失败！");
    }

    /**
     * 查询首页列表
     * @param sysQuestionParm
     * @return
     */
    @GetMapping("/getMyQuestionList")
    public ResultVo getMyQuestionList(SysQuestionParm sysQuestionParm) {
        PageInfo<SysQuestion> sysQuestionPageInfo = questionService.getMyQuestionList(sysQuestionParm);

        return ResultUtils.success("查询成功", sysQuestionPageInfo);
    }

    /**
     * 查询试题答案回显
     * @param questionId
     * @param openid
     * @return
     */
    @GetMapping("/getMyPaperListShow")
    public ResultVo getMyPaperListShow(Integer questionId, String openid) {
        SysQuestion list = questionService.getMyPaperList(questionId, openid);

        return ResultUtils.success("查询成功！", list);
    }
}
