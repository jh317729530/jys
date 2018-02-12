package com.gunn.jys.service;

import com.github.pagehelper.Page;
import com.gunn.jys.base.BaseService;
import com.gunn.jys.entity.Teacher;

public interface TeacherService extends BaseService<Teacher> {

    Page<Teacher> findList();
}
