package com.gunn.jys.mapper;

import com.github.pagehelper.Page;
import com.gunn.jys.base.BaseMapper;
import com.gunn.jys.entity.Teacher;

public interface TeacherMapper extends BaseMapper<Teacher> {

    Page<Teacher> findPageBy();

}