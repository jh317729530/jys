package com.gunn.jys.base;

import com.gunn.jys.bo.JysSubject;
import com.gunn.jys.constant.RequestConst;
import com.gunn.jys.util.JsonUtil;
import com.gunn.jys.util.JwtUtil;
import com.gunn.jys.util.JysSubjectUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class BaseController {

    /**
     * 日志
     */
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    protected Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected JysSubject getJysSubject(HttpServletRequest request ){
        Optional<String> optional = Optional.ofNullable(getToken(request));
        return optional.filter(JwtUtil::validateToken).map((str) -> JsonUtil.fromJson(JwtUtil.getFromTokenInfo(str), JysSubject.class)).orElse(null);
    }

    protected String getToken(HttpServletRequest request) {
        String token = request.getHeader(RequestConst.Header.TOKEN);
        return token;
    }

    protected Integer getUserId(HttpServletRequest request){
        Optional<JysSubject> optional = Optional.ofNullable(getJysSubject(request));
        return optional.map(JysSubject::getId).orElse(null);

    }

    protected Integer getTeacherId(HttpServletRequest request) {
        Optional<JysSubject> optional = Optional.ofNullable(getJysSubject(request));
        return optional.map(JysSubject::getTeacherId).orElse(null);
    }

    /**
     * 获取前端用户的userId
     *
     * @return
     */
    public Integer getUserId() {
        return JysSubjectUtil.getUserId();
    }
}
