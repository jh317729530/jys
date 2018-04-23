package com.gunn.jys.mapper;

import com.gunn.jys.base.BaseMapper;
import com.gunn.jys.entity.Keyword;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KeywordMapper extends BaseMapper<Keyword> {

    List<Keyword> findListBySelected(@Param("selected") Integer selected);
}