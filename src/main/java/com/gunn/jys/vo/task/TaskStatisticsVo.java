package com.gunn.jys.vo.task;

import com.gunn.jys.entity.TaskStatistics;

public class TaskStatisticsVo extends TaskStatistics {

    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
