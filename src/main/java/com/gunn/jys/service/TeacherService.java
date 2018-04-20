package com.gunn.jys.service;

import com.github.pagehelper.Page;
import com.gunn.jys.base.BaseService;
import com.gunn.jys.entity.Teacher;
import com.gunn.jys.vo.teacher.TeacherUserVo;

public interface TeacherService extends BaseService<Teacher> {

    Page<TeacherUserVo> findList(String name);
}
