package com.gunn.jys.service.impl;

import com.gunn.jys.base.impl.BaseServiceImpl;
import com.gunn.jys.entity.Task;
import com.gunn.jys.entity.TaskFile;
import com.gunn.jys.entity.TaskNotice;
import com.gunn.jys.mapper.TaskFileMapper;
import com.gunn.jys.mapper.TaskMapper;
import com.gunn.jys.mapper.TaskNoticeMapper;
import com.gunn.jys.service.TaskService;
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
}
