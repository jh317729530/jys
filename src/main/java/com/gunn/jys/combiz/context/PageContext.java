package com.gunn.jys.combiz.context;

import java.util.Optional;

public class PageContext {

    //定义两个threadLocal变量：pageNo和pageSize
    private static final ThreadLocal<Integer> PAGE_NO_THREAD_LOCAL = new ThreadLocal<>();

    private static final ThreadLocal<Integer> PAGE_SIZE_THREAD_LOCAL = new ThreadLocal<>();

    private static final ThreadLocal<Boolean> CANCEL_LOCAL = new ThreadLocal<>();

    public static Integer PAGE_NUM = 1;

    public static Integer PAGE_SIZE = 10;

    /**
     * 获取是否取消分页处理
     * @return
     */
    public static Boolean isCancel() {
        Boolean cancel = CANCEL_LOCAL.get();
        return Optional.ofNullable(cancel).orElse(false);
    }

    public static void setCancel(boolean cancel) {
        CANCEL_LOCAL.set(cancel);
    }

    /*
   * 获取当前是第几页
   */
    public static Integer getPageNumber() {
        Integer pageNumber = PAGE_NO_THREAD_LOCAL.get();
        return Optional.ofNullable(pageNumber).orElse(PAGE_NUM);
    }

    /**
     * 方法名: setPageNumber</br>
     * 详述: 设置当前是第几页</br>
     */
    public static void setPageNumber(int pageNumber) {
        PAGE_NO_THREAD_LOCAL.set(pageNumber);
    }
    public static void removePageNumber(){
        PAGE_NO_THREAD_LOCAL.remove();
    }

    public static int getPageSize() {
        Integer ps = PAGE_SIZE_THREAD_LOCAL.get();
        return Optional.ofNullable(ps).orElse(PAGE_SIZE);
    }
    public static void setPageSize(int pageSizeValue) {
        PAGE_SIZE_THREAD_LOCAL.set(pageSizeValue);
    }
    public static void removePageSize(){
        PAGE_SIZE_THREAD_LOCAL.remove();
    }

    public static void removeCancel() {
        CANCEL_LOCAL.remove();
    }
}
