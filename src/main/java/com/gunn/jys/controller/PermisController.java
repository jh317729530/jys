package com.gunn.jys.controller;

import com.gunn.jys.base.BaseController;
import com.gunn.jys.bo.PermisIds;
import com.gunn.jys.bo.Result;
import com.gunn.jys.service.PermisService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/permis")
public class PermisController extends BaseController {

    @Resource
    private PermisService permisService;

    @RequestMapping("update")
    public Result update(@RequestParam Integer userId, PermisIds permisIds) {
        permisService.updatePermis(userId, permisIds.getPermisIds());
        return new Result();
    }
}
