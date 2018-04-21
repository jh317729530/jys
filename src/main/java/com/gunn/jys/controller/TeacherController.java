package com.gunn.jys.controller;

import com.github.pagehelper.Page;
import com.gunn.jys.annotation.Pagination;
import com.gunn.jys.base.BaseController;
import com.gunn.jys.bo.InfoResult;
import com.gunn.jys.bo.MapResult;
import com.gunn.jys.bo.Result;
import com.gunn.jys.entity.User;
import com.gunn.jys.service.PermisService;
import com.gunn.jys.service.TeacherService;
import com.gunn.jys.service.UserService;
import com.gunn.jys.vo.node.Node;
import com.gunn.jys.vo.permis.PermisNodeVo;
import com.gunn.jys.vo.teacher.TeacherUserVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("teacher")
@RestController
public class TeacherController extends BaseController {

    @Resource
    private TeacherService teacherService;

    @Resource
    private UserService userService;

    @Resource
    private PermisService permisService;

    @RequestMapping("page")
    @Pagination
    public Result page(String name,Integer status) {
        InfoResult<Page<TeacherUserVo>> result = new InfoResult<>();
        result.setInfo(teacherService.findPage(name,status));
        return result;
    }

    @RequestMapping("/list")
    public Result list(Integer status) {
        InfoResult result = new InfoResult();
        result.setInfo(teacherService.findList(status));
        return result;
    }

    @RequestMapping("changeStatus")
    public Result changeStatus(@RequestParam Integer id, @RequestParam Integer status) {
        User user = userService.selectByPrimaryKey(id);
        user.setStatus(status);
        userService.updateByPrimaryKey(user);
        return new Result();
    }

    @RequestMapping("getPermis")
    public Result getPermis(Integer id) {
        if (null == id) {
            id = getUserId();
        }
        InfoResult<PermisNodeVo> result = new InfoResult<>();
        result.setInfo(permisService.findPermisByUserId(id));
        return result;
    }
}
