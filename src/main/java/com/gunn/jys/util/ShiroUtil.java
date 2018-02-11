package com.gunn.jys.util;

public class ShiroUtil {

    /**
     * 转换成shiro的权限
     * @param url
     * @return
     */
    public static String getStringPermission(String url) {
        if (!url.startsWith("/")) {
            url = "/" + url;
        }
        String perms = url.substring(1).replaceAll("/", ":");
        return perms;
    }
}
