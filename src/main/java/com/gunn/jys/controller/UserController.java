package com.gunn.jys.controller;

import com.gunn.jys.annotation.Anon;
import com.gunn.jys.base.BaseController;
import com.gunn.jys.bo.InfoResult;
import com.gunn.jys.bo.JysSubject;
import com.gunn.jys.bo.MapResult;
import com.gunn.jys.bo.Result;
import com.gunn.jys.entity.Teacher;
import com.gunn.jys.entity.User;
import com.gunn.jys.entity.UserRole;
import com.gunn.jys.mapper.UserRoleMapper;
import com.gunn.jys.service.ResourceService;
import com.gunn.jys.service.TeacherService;
import com.gunn.jys.service.UserService;
import com.gunn.jys.vo.user.UserResourceVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private ResourceService resourceService;

    @Resource
    private TeacherService teacherService;

    @Resource
    private UserRoleMapper userRoleMapper;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Result add(@NotNull String username, @NotNull String password) {
        Result result = new Result();
        userService.addUser(username, password);
        return result;
    }

    @Anon
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public Result login(@NotNull String username, @NotNull String password) {
        InfoResult<String> result = new InfoResult<>();

        try {
            User user = userService.selectByUsername(username);
            if (null == user) {
                result.setInvalidMsg("当前用户不存在");
                return result;
            }

            if (0 == user.getStatus()) {
                result.setInvalidMsg("账号已经停用");
                return result;
            }

            Teacher teacher = teacherService.findByUserId(user.getId());

            JysSubject jysSubject = new JysSubject(user.getId(), username, user.getIsAdmin(),teacher.getId());
            String json = jysSubject.toJson();
            log.info("admin login in info is[" + json + "]");

            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(json, password);
            subject.login(usernamePasswordToken);
            String token = jysSubject.toToken();
            result.setInfo(token);
        }catch (AuthenticationException e) {
            e.printStackTrace();
            result.setInvalidMsg("用户名或密码不正确");
        } catch (Exception e) {
            e.printStackTrace();
            result.setExceptionMsg("登陆失败");
        }
        return result;
    }

    @RequestMapping("baseInfo")
    public Result baseInfo() {
        MapResult mapResult = new MapResult();
        User user = userService.selectByPrimaryKey(getUserId());
        Teacher query = new Teacher();
        query.setUserId(getUserId());
        List<Teacher> select = teacherService.select(query);
        Teacher teacher = select.get(0);
        UserRole userRoleQuery = new UserRole();
        userRoleQuery.setUserId(getUserId());
        UserRole userRole = userRoleMapper.selectOne(userRoleQuery);
        mapResult.getInfo().put("name", teacher.getName());
        mapResult.getInfo().put("role", userRole.getRoleId());
        mapResult.getInfo().put("avatar", user.getAvatar());
        mapResult.getInfo().put("userId", getUserId());
        return mapResult;
    }

    @RequestMapping("detailInfo")
    public Result detailInfo() {
        MapResult result = new MapResult();
        result.getInfo().put("user", userService.selectByPrimaryKey(getUserId()));
        result.getInfo().put("teacher", teacherService.selectByPrimaryKey(getTeacherId()));
        return result;
    }

    @RequestMapping("test")
    public Result test(){
        User user = userService.selectByUsername("15603017553");
        InfoResult result = new InfoResult();
        result.setInfo(user);
        return result;
    }

    @RequestMapping("getResource")
    public Result getResource() {
        InfoResult<List<UserResourceVo>> result = new InfoResult<>();
        List<UserResourceVo> userResourceVos = resourceService.getByUserId(getUserId());
        result.setInfo(userResourceVos);
        return result;
    }
}
