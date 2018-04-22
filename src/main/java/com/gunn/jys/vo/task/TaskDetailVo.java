package com.gunn.jys.vo.task;

import com.gunn.jys.entity.Task;
import com.gunn.jys.entity.TaskFile;

import java.util.List;

public class TaskDetailVo {

    private Task task;

    private List<TaskFile> taskFiles;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<TaskFile> getTaskFiles() {
        return taskFiles;
    }

    public void setTaskFiles(List<TaskFile> taskFiles) {
        this.taskFiles = taskFiles;
    }
}
