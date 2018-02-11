package com.gunn.jys.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {

    /**
     * 响应流写出json
     * @param response
     * @param json
     */
    public static void writeJson(HttpServletResponse response, String json) {
        PrintWriter printWriter = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            printWriter = response.getWriter();
            printWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != printWriter) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }

    /**
     * 把对象作为json写出
     * @param response
     * @param object
     */
    public static void writeJson(HttpServletResponse response, Object object) {
        writeJson(response, JsonUtil.toJson(object));
    }



}
