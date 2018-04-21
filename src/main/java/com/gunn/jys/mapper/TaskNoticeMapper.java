package com.gunn.jys.mapper;

import com.gunn.jys.base.BaseMapper;
import com.gunn.jys.entity.TaskNotice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskNoticeMapper extends BaseMapper<TaskNotice> {

    int insertBatch(@Param("taskNotices") List<TaskNotice> taskNotices);

}