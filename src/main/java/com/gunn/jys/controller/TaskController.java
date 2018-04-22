package com.gunn.jys.controller;

import com.gunn.jys.base.BaseController;
import com.gunn.jys.bo.InfoResult;
import com.gunn.jys.bo.Result;
import com.gunn.jys.constant.common.DatePattern;
import com.gunn.jys.entity.Task;
import com.gunn.jys.entity.TaskStatistics;
import com.gunn.jys.service.TaskService;
import com.gunn.jys.util.DateUtils;
import com.gunn.jys.util.ObjectUtils;
import com.gunn.jys.vo.task.TaskAddVo;
import com.gunn.jys.vo.task.TaskDetailVo;
import com.gunn.jys.vo.task.TaskStatisticsVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Resource
    private TaskService taskService;

    @RequestMapping("add")
    public Result add(TaskAddVo taskAddVo) {
        Task task = new Task();
        ObjectUtils.copy(taskAddVo, task);
        task.setUserId(getUserId());
        task.setCreated(new Date());
        if (task.getReleaseTime().before(new Date())) {
            task.setStatus(1);
        }
        taskService.addTask(task, taskAddVo.getTeacherIds(), taskAddVo.getFileUrls());
        return new Result();
    }

    @RequestMapping("/list")
    public Result list() {
        InfoResult result = new InfoResult();
        result.setInfo(taskService.findList(getUserId()));
        return result;
    }

    @RequestMapping("/detail")
    public Result detail(Integer taskId) {
        InfoResult<TaskDetailVo> result = new InfoResult<>();
        result.setInfo(taskService.findDetail(taskId));
        return result;
    }

    @RequestMapping("/statistics")
    public Result statistics(Integer taskId) {
        InfoResult<List<TaskStatisticsVo>> result = new InfoResult<>();
        result.setInfo(taskService.findTaskStatisticsVo(taskId));
        return result;
    }

    @RequestMapping("endTask")
    public Result endTask(Integer taskId) {
        Task task = taskService.selectByPrimaryKey(taskId);
        task.setStatus(2);
        taskService.updateByPrimaryKey(task);
        return new Result();
    }
}
