package com.gunn.jys.vo.task;

import com.gunn.jys.entity.Task;

public class TaskVo extends Task {

    private String name;

    private Integer finishedStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFinishedStatus() {
        return finishedStatus;
    }

    public void setFinishedStatus(Integer finishedStatus) {
        this.finishedStatus = finishedStatus;
    }
}
