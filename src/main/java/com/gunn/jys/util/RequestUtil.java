package com.gunn.jys.util;

import com.gunn.jys.constant.HeaderConst;
import com.gunn.jys.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mobile.device.DeviceUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class RequestUtil {

    /**
     * 获取请求的后缀
     * @param request
     * @return
     */
    public static String getSuffix(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String suffix = StringUtils.substringAfterLast(requestUri, ".");
        return suffix;
    }

    public static final String UNKNOWN = "unknown";

    /**
     * 获取IP地址
     *
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getAccessIp(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            throw new ServiceException("5000");
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }

    /**
     * 当前访问是否是移动端访问的。
     * @param request
     * @return
     */
    public static boolean isMobile(HttpServletRequest request) {
        return DeviceUtils.getCurrentDevice(request).isMobile();
    }

    /**
     * 判断ajax请求
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request){
        return  (request.getHeader("X-Requested-With") != null  && "XMLHttpRequest".equals( request.getHeader("X-Requested-With").toString())   ) ;
    }

    /**
     *
     * @param request
     * @return
     */
    public static String getRequestUrl(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder(request.getRequestURL().toString() + "?time=" + System.currentTimeMillis());
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name= names.nextElement();
//            if (WeiXinConst.WEIXIN_REQ_KEY.contains(name)) {
//                continue;
//            }
            String[] values = request.getParameterValues(name);
            if (values != null){
                for (String value : values) {
                    sb.append("&" + name + "=" + value);
                }
            }
        }

        return sb.toString();
    }

    public static String getToken(ServletRequest request) {
        String token = getAuthzHeader(request);
        if (StringUtils.isBlank(token)) {
            token = getAuthzBody(request);
        }
        return token;
    }

    /**
     * 获取授权的请求头
     * @param request
     * @return
     */
    public static String getAuthzHeader(HttpServletRequest request) {
        return request.getHeader(HeaderConst.param.TOKEN);
    }

    public static String getAuthzHeader(ServletRequest request) {
        return getAuthzHeader((HttpServletRequest)request);
    }

    /**
     * 获取授权的请求体
     * @param request
     * @return
     */
    public static String getAuthzBody(HttpServletRequest request) {
        return request.getParameter(HeaderConst.param.TOKEN);
    }


    public static String getAuthzBody(ServletRequest request) {
        return getAuthzBody((HttpServletRequest)request);
    }

    public static HttpServletRequest getRequest(ServletRequest request) {
        return (HttpServletRequest)request;
    }

}
