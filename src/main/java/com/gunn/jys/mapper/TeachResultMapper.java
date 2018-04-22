package com.gunn.jys.mapper;

import com.github.pagehelper.Page;
import com.gunn.jys.base.BaseMapper;
import com.gunn.jys.entity.TeachResult;
import com.gunn.jys.vo.teachResult.TeachResultVo;
import org.apache.ibatis.annotations.Param;

public interface TeachResultMapper extends BaseMapper<TeachResult> {

    Page<TeachResultVo> findPageBy(@Param("title") String title);
}