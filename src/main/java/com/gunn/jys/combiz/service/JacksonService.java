package com.gunn.jys.combiz.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public interface JacksonService<T>{

    ObjectMapper getObjectMapper(boolean isPage);

    /**
     * 对象转换成json
     * @param object
     * @return
     */
    String writeValueAsString(Object object);

    /**
     * 获取默认的ObjectMapper
     * @return
     */
    ObjectMapper getDefaultMapper();

    /**
     * 反序列化json
     * @param content
     * @param clazz
     * @return
     */
    T readValue(String content, Class<T> clazz);

    /**
     * 反序列json为List的集合类型
     * @param jsonStr
     * @param listClass
     * @param elementClass
     * @return
     */
    List<T> getListByType(String jsonStr, Class<?> listClass, Class<T> elementClass);
}
