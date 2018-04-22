package com.gunn.jys.mapper;

import com.gunn.jys.base.BaseMapper;
import com.gunn.jys.entity.Task;
import com.gunn.jys.vo.task.TaskVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapper extends BaseMapper<Task> {

    List<TaskVo> findList(@Param("userId") Integer userId);

    List<TaskVo> findHasTaskList(@Param("teacherId") Integer teacherId);
}