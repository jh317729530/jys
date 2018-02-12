package com.gunn.jys.util;

import com.github.pagehelper.Page;

public class PageUtil {

    public static <T> com.gunn.jys.bo.Page<T> page2page(Page<T> page) {
        com.gunn.jys.bo.Page<T> p = new  com.gunn.jys.bo.Page<>();
        p.setPageNum(page.getPageNum());
        p.setPageSize(page.getPageSize());
        p.setStartRow(page.getStartRow());
        p.setEndRow(page.getEndRow());
        p.setTotal(page.getTotal());
        p.setPages(page.getPages());
        p.setResult(page.getResult());
        return p;
    }
}
