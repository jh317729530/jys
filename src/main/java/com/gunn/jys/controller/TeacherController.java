package com.gunn.jys.controller;

import com.gunn.jys.annotation.Pagination;
import com.gunn.jys.base.BaseController;
import com.gunn.jys.bo.InfoResult;
import com.gunn.jys.bo.Result;
import com.gunn.jys.service.TeacherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("teacher")
@RestController
public class TeacherController extends BaseController {

    @Resource
    private TeacherService teacherService;

    @Pagination
    @RequestMapping("list")
    public Result list(){
        InfoResult result = new InfoResult();
        result.setInfo(teacherService.findList());
        return result;
    }
}
