package com.gunn.jys.service.impl;

import com.github.pagehelper.Page;
import com.gunn.jys.base.impl.BaseServiceImpl;
import com.gunn.jys.entity.Academic;
import com.gunn.jys.mapper.AcademicMapper;
import com.gunn.jys.service.AcademicService;
import org.springframework.stereotype.Service;

@Service
public class AcademicServiceImpl extends BaseServiceImpl<AcademicMapper, Academic> implements AcademicService {

    @Override
    public Page<Academic> findPageByName(String academicName) {
        return dao.findPageByName(academicName);
    }
}
