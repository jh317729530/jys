package com.gunn.jys.service;

import com.gunn.jys.base.BaseMapper;
import com.gunn.jys.base.BaseService;
import com.gunn.jys.entity.Keyword;
import com.gunn.jys.mapper.KeywordMapper;

import java.util.List;


public interface KeywordService extends BaseService<Keyword> {

    List<Keyword> getListBySelected(Integer selected);
}
