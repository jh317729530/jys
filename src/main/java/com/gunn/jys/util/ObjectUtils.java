package com.gunn.jys.util;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

public class ObjectUtils {

    public static <T, V> void copy(T t, V v, List<String> excludeNameList) {
        List<Field> srcFields = FieldUtils.getAllFieldsList(t.getClass());

        srcFields.forEach(s -> {
            if (Modifier.isFinal(s.getModifiers()) || Modifier.isStatic(s.getModifiers())) {
                // 过滤static的与final的对象
                return;
            }
            String name = s.getName();
            if (excludeNameList != null) {
                if (excludeNameList.contains(name)) {
                    return ;
                }
            }
            Class<?> type = s.getType();
            try {
                Field targetField = FieldUtils.getDeclaredField(v.getClass(), name, true);
                // 不存在该属性
                if (targetField == null) {
                    return ;
                }
                Class<?> targetType = targetField.getType();
                if (!type.equals(targetType)) {
                    // 类型不一样
                    return ;
                }

                s.setAccessible(true);
                targetField.setAccessible(true);
                targetField.set(v, s.get(t));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("复制对象错误");
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        });
    }

    public static <T, V> void copy(T t, V v) {
        copy(t, v, null);
    }


}
