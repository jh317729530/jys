package com.gunn.jys.controller;

import com.gunn.jys.annotation.Anon;
import com.gunn.jys.base.BaseController;
import com.gunn.jys.bo.InfoResult;
import com.gunn.jys.bo.JysSubject;
import com.gunn.jys.bo.Result;
import com.gunn.jys.entity.User;
import com.gunn.jys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

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

            JysSubject jysSubject = new JysSubject(user.getId(), username, user.getIsAdmin());
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

    @RequestMapping("info")
    public Result info() {
        InfoResult result = new InfoResult();
        result.setInfo(userService.selectUserInfoById(getUserId()));
        return result;
    }

    @RequestMapping("test")
    public Result test(){
        User user = userService.selectByUsername("15603017553");
        InfoResult result = new InfoResult();
        result.setInfo(user);
        return result;
    }

}
