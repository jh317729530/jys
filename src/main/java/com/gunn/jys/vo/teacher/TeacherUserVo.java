package com.gunn.jys.vo.teacher;

import com.gunn.jys.entity.Teacher;

public class TeacherUserVo extends Teacher {

    private Integer userStatus;

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
}
