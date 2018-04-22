package com.gunn.jys.service.impl;

import com.gunn.jys.base.impl.BaseServiceImpl;
import com.gunn.jys.entity.*;
import com.gunn.jys.mapper.*;
import com.gunn.jys.service.TaskService;
import com.gunn.jys.vo.task.TaskDetailVo;
import com.gunn.jys.vo.task.TaskStatisticsVo;
import com.gunn.jys.vo.task.TaskVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl extends BaseServiceImpl<TaskMapper, Task> implements TaskService {

    @Resource
    private TaskNoticeMapper taskNoticeMapper;

    @Resource
    private TaskFileMapper taskFileMapper;

    @Resource
    private TaskStatisticsMapper taskStatisticsMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    @Transactional
    public int addTask(Task task, List<Integer> teacherIds, List<String> fileUrls) {

        dao.insert(task);
        Date now = new Date();
        List<TaskNotice> taskNotices = new ArrayList<>();
        teacherIds.forEach(teacherId -> {
            TaskNotice taskNotice = new TaskNotice();
            taskNotice.setTaskId(task.getId());
            taskNotice.setTeacherId(teacherId);
            taskNotice.setCreated(now);
            taskNotices.add(taskNotice);
        });
        taskNoticeMapper.insertBatch(taskNotices);

        List<TaskStatistics> taskStatisticsList = new ArrayList<>();
        teacherIds.forEach(teacherId -> {
            TaskStatistics taskStatistics = new TaskStatistics();
            taskStatistics.setTaskId(task.getId());
            taskStatistics.setTeacherId(teacherId);
            taskStatistics.setStatus(0);
            taskStatisticsList.add(taskStatistics);
        });
        taskStatisticsMapper.insertBatch(taskStatisticsList);

        if (null != fileUrls && 0 != fileUrls.size()) {
            List<TaskFile> taskFiles = new ArrayList<>();
            fileUrls.forEach(fileUrl -> {
                TaskFile taskFile = new TaskFile();
                taskFile.setFileUrl(fileUrl);
                taskFile.setTaskId(task.getId());
                taskFile.setCreated(now);
                taskFiles.add(taskFile);
            });
            taskFileMapper.insertBatch(taskFiles);
        }

        return 1;
    }

    @Override
    public List<TaskVo> findList(Integer userId) {
        UserRole query = new UserRole();
        query.setUserId(userId);
        UserRole userRole = userRoleMapper.selectOne(query);
        if (2 == userRole.getRoleId()) {
            return dao.findList(userId);
        } else {
            Teacher teacherQuery = new Teacher();
            teacherQuery.setUserId(userId);
            Teacher teacher = teacherMapper.selectOne(teacherQuery);
            return dao.findHasTaskList(teacher.getId());
        }
    }

    @Override
    public TaskDetailVo findDetail(Integer taskId) {
        TaskDetailVo taskDetailVo = new TaskDetailVo();
        taskDetailVo.setTask(dao.selectByPrimaryKey(taskId));
        TaskFile query = new TaskFile();
        List<TaskFile> taskFiles = taskFileMapper.select(query);
        taskDetailVo.setTaskFiles(taskFiles);
        return taskDetailVo;
    }

    @Override
    public List<TaskStatisticsVo> findTaskStatisticsVo(Integer taskId) {
        return taskStatisticsMapper.findList(taskId);
    }

}
