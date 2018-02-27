package com.gunn.jys.service;

import com.github.pagehelper.Page;
import com.gunn.jys.base.BaseService;
import com.gunn.jys.entity.Academic;

public interface AcademicService extends BaseService<Academic> {

    Page<Academic> findPageByName(String academicName);
}
