package com.gunn.jys.mapper;

import com.gunn.jys.base.BaseMapper;
import com.gunn.jys.entity.TaskFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskFileMapper extends BaseMapper<TaskFile> {

    int insertBatch(@Param("taskFiles") List<TaskFile> taskFiles);

}