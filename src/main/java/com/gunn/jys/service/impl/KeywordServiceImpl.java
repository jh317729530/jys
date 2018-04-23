package com.gunn.jys.service.impl;

import com.gunn.jys.base.impl.BaseServiceImpl;
import com.gunn.jys.entity.Keyword;
import com.gunn.jys.mapper.KeywordMapper;
import com.gunn.jys.service.KeywordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KeywordServiceImpl extends BaseServiceImpl<KeywordMapper, Keyword> implements KeywordService {


    @Override
    public List<Keyword> getListBySelected(Integer selected) {
        return dao.findListBySelected(selected);
    }

    @Override
    @Transactional
    public int updateKeywords(List<Integer> selectedKeywordIds, List<Integer> noSelectedKeywordIds) {

        noSelectedKeywordIds.forEach(id -> {
            Keyword keyword = dao.selectByPrimaryKey(id);
            keyword.setSelected(0);
            dao.updateByPrimaryKey(keyword);
        });

        selectedKeywordIds.forEach(id -> {
            Keyword keyword = dao.selectByPrimaryKey(id);
            keyword.setSelected(1);
            dao.updateByPrimaryKey(keyword);
        });

        return 1;
    }

}
