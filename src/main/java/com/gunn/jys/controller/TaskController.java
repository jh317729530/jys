package com.gunn.jys.controller;

import com.gunn.jys.base.BaseController;
import com.gunn.jys.bo.Result;
import com.gunn.jys.entity.Task;
import com.gunn.jys.service.TaskService;
import com.gunn.jys.util.ObjectUtils;
import com.gunn.jys.vo.task.TaskAddVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Resource
    private TaskService taskService;

    @RequestMapping("add")
    public Result add(TaskAddVo taskAddVo) {
        Task task = new Task();
        ObjectUtils.copy(taskAddVo, task);
        taskService.addTask(task, taskAddVo.getTeacherIds(), taskAddVo.getFileUrls());
        return new Result();
    }
}
