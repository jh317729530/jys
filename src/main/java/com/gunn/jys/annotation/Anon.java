package com.gunn.jys.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *  匿名即可访问的接口
 */
@Target({ElementType.METHOD})
@Retention(RUNTIME)
public @interface Anon {
}
