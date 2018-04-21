package com.gunn.jys.vo.task;

import com.gunn.jys.entity.Task;

import java.util.List;

public class TaskAddVo extends Task {

    private List<Integer> teacherIds;

    private List<String> fileUrls;

    public List<Integer> getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(List<Integer> teacherIds) {
        this.teacherIds = teacherIds;
    }

    public List<String> getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(List<String> fileUrls) {
        this.fileUrls = fileUrls;
    }
}
