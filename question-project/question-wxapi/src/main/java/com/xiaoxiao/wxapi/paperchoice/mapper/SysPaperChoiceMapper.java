package com.xiaoxiao.wxapi.paperchoice.mapper;


import com.xiaoxiao.wxapi.paperchoice.entity.SysPaperChoice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPaperChoiceMapper {

    List<SysPaperChoice> selectPaperChoiceListByPaperId(Integer paperId);
}
