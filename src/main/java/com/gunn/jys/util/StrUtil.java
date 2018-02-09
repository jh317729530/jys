package com.gunn.jys.util;

import org.apache.commons.lang3.StringUtils;

/**
 *  字符串工具
 */
public class StrUtil {

    /**
     * 处理访问的接口
     * @param string
     * @return
     */
    public static String dealSlash(String string) {
        String newString = string;
        if (StringUtils.isBlank(string)) {
            return "";
        }
        if (!string.startsWith("/")) {
            newString = "/" + newString;
        }

        if (string.endsWith("/")) {
            newString = newString.substring(0, newString.length() - 1);
        }

        return newString;

    }
}
