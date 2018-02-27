package com.gunn.jys.controller;

import com.github.pagehelper.Page;
import com.gunn.jys.annotation.Pagination;
import com.gunn.jys.base.BaseController;
import com.gunn.jys.bo.InfoResult;
import com.gunn.jys.bo.Result;
import com.gunn.jys.entity.Academic;
import com.gunn.jys.service.AcademicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("academic")
public class AcademicController extends BaseController {

    @Resource
    private AcademicService academicService;

    @RequestMapping("list")
    @Pagination
    public Result list(String academicName) {
        InfoResult<Page<Academic>> result = new InfoResult<>();
        result.setInfo(academicService.findPageByName(academicName));
        return result;
    }

    @RequestMapping("add")
    public Result add(Academic academic) {
        InfoResult result = new InfoResult();
        academicService.insertSelective(academic);
        return result;
    }
}
