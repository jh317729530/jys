package com.gunn.jys.mapper;

import com.github.pagehelper.Page;
import com.gunn.jys.base.BaseMapper;
import com.gunn.jys.entity.Academic;

public interface AcademicMapper extends BaseMapper<Academic> {

    Page<Academic> findPageByName(String name);

}