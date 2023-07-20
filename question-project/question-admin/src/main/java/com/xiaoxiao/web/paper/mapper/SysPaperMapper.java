package com.xiaoxiao.web.paper.mapper;

import com.xiaoxiao.web.paper.entity.SysPaper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPaperMapper {
    void deletePaperByQuestionId(@Param("questionId") Integer questionId);

    void insertPaper(@Param("sysPaper") SysPaper sysPaper);

    SysPaper selectPaper(SysPaper sysPaper);

    List<SysPaper> selectPaperListByQuestionId(@Param("questionId") Integer questionId);

    void deletePaperByPaperId(@Param("paperId") Integer paperId);
}
