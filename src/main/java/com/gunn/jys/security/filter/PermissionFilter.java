package com.gunn.jys.security.filter;

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

public class PermissionFilter extends PermissionsAuthorizationFilter {

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

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        return super.onAccessDenied(request, response);
    }
}
