package com.xiaoxiao.wxapi.paper.mapper;


import com.xiaoxiao.wxapi.paper.entity.SysPaper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPaperMapper {

    List<SysPaper> selectPaperListByQuestionId(Integer questionId);

    List<SysPaper> getMyPaperList(@Param("questionId") Integer questionId, @Param("openid") String openid);
}
