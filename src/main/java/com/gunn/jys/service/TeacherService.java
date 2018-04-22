package com.gunn.jys.service;

import com.github.pagehelper.Page;
import com.gunn.jys.base.BaseService;
import com.gunn.jys.entity.Teacher;
import com.gunn.jys.vo.teacher.TeacherUserVo;

import java.util.List;

public interface TeacherService extends BaseService<Teacher> {

    Page<TeacherUserVo> findPage(String name,Integer status);

    List<Teacher> findList(Integer status);

    Teacher findByUserId(Integer userId);
}
