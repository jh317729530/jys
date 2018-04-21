package com.gunn.jys.service;

import com.gunn.jys.base.BaseService;
import com.gunn.jys.entity.Task;

import java.util.List;

public interface TaskService extends BaseService<Task> {

    int addTask(Task task, List<Integer> teacherIds, List<String> fileUrls);
}
