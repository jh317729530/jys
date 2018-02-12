package com.gunn.jys.combiz.aspect;

import com.github.pagehelper.PageHelper;
import com.gunn.jys.combiz.context.PageContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 分页切面
 */
@Component
@Aspect
public class PageAspect {

    @Around("execution(com.github.pagehelper.Page com.gunn.jys.mapper.*.*(..))")
    public Object arround(ProceedingJoinPoint pjp) {
        Object result = null;
        try {
            if (!PageContext.isCancel()) {
                PageHelper.startPage(PageContext.getPageNumber(), PageContext.getPageSize(), true, false, null);
            }
            result= pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }
}
