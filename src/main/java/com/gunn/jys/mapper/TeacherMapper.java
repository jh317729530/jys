package com.gunn.jys.mapper;

import com.github.pagehelper.Page;
import com.gunn.jys.base.BaseMapper;
import com.gunn.jys.entity.Teacher;
import com.gunn.jys.vo.teacher.TeacherUserVo;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper extends BaseMapper<Teacher> {

    Page<TeacherUserVo> findPageBy(@Param("name") String name);

}