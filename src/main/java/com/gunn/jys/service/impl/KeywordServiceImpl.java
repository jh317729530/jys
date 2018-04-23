package com.gunn.jys.service.impl;

import com.gunn.jys.base.impl.BaseServiceImpl;
import com.gunn.jys.entity.Keyword;
import com.gunn.jys.mapper.KeywordMapper;
import com.gunn.jys.service.KeywordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordServiceImpl extends BaseServiceImpl<KeywordMapper, Keyword> implements KeywordService {


    @Override
    public List<Keyword> getListBySelected(Integer selected) {
        return dao.findListBySelected(selected);
    }

}
