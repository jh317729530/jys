package com.gunn.jys.util;

import com.gunn.jys.bo.JysSubject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.Optional;

public class JysSubjectUtil {

    /**
     * 获取登陆的主体
     *
     * @return
     */
    public static String getPrincipal() {
        return Optional.ofNullable(SecurityUtils.getSubject())
                .map(Subject::getPrincipal)
                .map(Object::toString).orElse(null);
    }

    /**
     * 获取当前登陆的用户对象
     *
     * @return
     */
    public static JysSubject getSoSubject() {
        return Optional.ofNullable(getPrincipal())
                .map(s -> JsonUtil.fromJson(s, JysSubject.class)).orElse(null);
    }

    /**
     * 获取前端登陆用户的id
     *
     * @return
     */
    public static Integer getUserId() {
        return Optional.ofNullable(getSoSubject())
                .map(JysSubject::getId).orElse(null); }

    public static Integer getTeacherId() {
        return Optional.ofNullable(getSoSubject())
                .map(JysSubject::getTeacherId).orElse(null); }
}
