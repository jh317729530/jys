package com.gunn.jys.service.impl;

import com.github.pagehelper.Page;
import com.gunn.jys.base.impl.BaseServiceImpl;
import com.gunn.jys.entity.Teacher;
import com.gunn.jys.mapper.TeacherMapper;
import com.gunn.jys.service.TeacherService;
import com.gunn.jys.vo.teacher.TeacherUserVo;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends BaseServiceImpl<TeacherMapper,Teacher> implements TeacherService {

    @Override
    public Page<TeacherUserVo> findList(String name) {
        Page<TeacherUserVo> page = dao.findPageBy(name);
        return page;
    }

}
