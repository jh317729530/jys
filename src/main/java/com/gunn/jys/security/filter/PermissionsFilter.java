package com.gunn.jys.security.filter;

import com.gunn.jys.bo.JysSubject;
import com.gunn.jys.security.token.JWTAuthenticationToken;
import com.gunn.jys.util.JsonUtil;
import com.gunn.jys.util.JwtUtil;
import com.gunn.jys.util.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

public class PermissionsFilter extends PermissionsAuthorizationFilter {

    @Override
    public boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws IOException {
        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        HttpServletResponse response = WebUtils.toHttp(servletResponse);
        String token = RequestUtil.getToken(request);

        if (StringUtils.isBlank(token)) {
            return false;
        }

        // token 不合法
        if (!JwtUtil.validateToken(token)) {
            return false;
        }

        // token 过期
        if (JwtUtil.isTokenExpired(token)) {
            return false;
        }

        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            try {
                String userId = JwtUtil.getFromToken(token);
                JWTAuthenticationToken jwtAuthenticationToken = new JWTAuthenticationToken(userId, token);
                subject.login(jwtAuthenticationToken);
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
        }


        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        HttpServletResponse response = WebUtils.toHttp(servletResponse);

        String token = RequestUtil.getAuthzHeader(request);
        // 如果传递的header为空
        if (StringUtils.isBlank(token)) {
            JwtUtil.responseUnauthorized4010(request,response);
            return false;
        }

        // token不合法
        if (!JwtUtil.validateToken(token)) {
            JwtUtil.responseUnauthorized4011(request, response);
            return false;
        }

        // token失效
        if (JwtUtil.isTokenExpired(token)) {
            JwtUtil.responseUnauthorized4012(request,response);
            return false;
        }

        String json = null;
        try {
            json = JwtUtil.getFromToken(token);
            JysSubject jysSubject = JsonUtil.fromJson(json, JysSubject.class);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JwtUtil.responseUnauthorized401(request, response);
        return super.onAccessDenied(request, response);
    }
}
