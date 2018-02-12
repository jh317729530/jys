package com.gunn.jys.interceptor;

import com.github.pagehelper.Page;
import com.gunn.jys.annotation.Pagination;
import com.gunn.jys.base.BaseInterceptor;
import com.gunn.jys.combiz.context.PageContext;
import com.gunn.jys.constant.common.PageConst;
import com.gunn.jys.util.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

@Component
public class PageInterceptor extends BaseInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isNeedToPage(handler)) {
            PageContext.setPageNumber(getPageNo(request));
            PageContext.setPageSize(getPageSize(request));
            PageContext.setCancel(false);
        } else {
            PageContext.setCancel(true);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (!PageContext.isCancel()) {
            PageContext.removePageNumber();
            PageContext.removePageSize();

            if (null != modelAndView) {
                ModelMap modelMap = modelAndView.getModelMap();
                Set<Map.Entry<String, Object>> set = modelMap.entrySet();
                for (Map.Entry<String, Object> entry : set) {
                    Object value = entry.getValue();
                    if (value instanceof Page) {
                        String key = entry.getKey();
                        Page page = (Page)value;
                        modelMap.put(key, PageUtil.page2page(page));
                    }
                }
            } else {

            }
        }
        PageContext.removeCancel();
    }

    private boolean isNeedToPage(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Pagination pagination = handlerMethod.getMethodAnnotation(Pagination.class);
            if (null != pagination) {
                return pagination.value();
            }
        }
        return false;
    }

    /**
     * 获得pageNo参数的值
     * @param request
     * @return
     */
    protected Integer getPageNo(HttpServletRequest request) {
        Integer pageNo = PageContext.PAGE_NO;
        try {
            String pg = request.getParameter(PageConst.PAGE_NO);
            if (StringUtils.isNotBlank(pg)) {
                pageNo =Integer.parseInt(pg);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return pageNo;
    }

    /**
     * 设置默认每页大小
     * @return
     */
    protected Integer getPageSize(HttpServletRequest request) {
        Integer pageSize = PageContext.PAGE_SIZE;
        try {
            String ps = request.getParameter(PageConst.PAGE_SIZE);
            if (StringUtils.isNotBlank(ps)) {
                pageSize =Integer.parseInt(ps);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return pageSize;
    }
}
