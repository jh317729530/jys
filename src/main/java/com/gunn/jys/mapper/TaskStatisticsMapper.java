package com.gunn.jys.mapper;

import com.gunn.jys.base.BaseMapper;
import com.gunn.jys.entity.TaskStatistics;
import com.gunn.jys.vo.task.TaskStatisticsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskStatisticsMapper extends BaseMapper<TaskStatistics> {

    List<TaskStatisticsVo> findList(@Param("taskId") Integer taskId);

    int insertBatch(@Param("taskStatisticsList") List<TaskStatistics> taskStatisticsList);

}