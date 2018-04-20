package com.gunn.jys.controller;

import com.gunn.jys.base.BaseController;
import com.gunn.jys.bo.InfoResult;
import com.gunn.jys.bo.PermisIds;
import com.gunn.jys.bo.Result;
import com.gunn.jys.entity.Permis;
import com.gunn.jys.entity.User;
import com.gunn.jys.mapper.UserPermisMapper;
import com.gunn.jys.service.PermisService;
import com.gunn.jys.service.UserService;
import com.gunn.jys.vo.permis.PermisVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/permis")
public class PermisController extends BaseController {

    @Resource
    private PermisService permisService;

    @Resource
    private UserPermisMapper userPermisMapper;

    @Resource
    private UserService userService;

    @RequestMapping("update")
    public Result update(@RequestParam Integer userId, PermisIds permisIds) {
        permisService.updatePermis(userId, permisIds.getPermisIds());
        return new Result();
    }

    @RequestMapping("getPermis")
    public Result getPermis(){
        InfoResult result = new InfoResult();
        List<Permis> permisVoByUserId = null;
        User user = userService.selectByPrimaryKey(getUserId());
        if (user.getIsAdmin().equals(1)) {
            permisVoByUserId = permisService.selectAll();
        } else {
            permisVoByUserId = userPermisMapper.findPermisByUserId(getUserId());
        }
        result.setInfo(permisVoByUserId);
        return result;
    }

}
