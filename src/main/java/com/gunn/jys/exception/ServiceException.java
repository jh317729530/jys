package com.gunn.jys.exception;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String[] errParam;


    /**
     * 抛出异常, 并携带错误key以及需携带参数 {errKey, param1, param2....}
     * @param errParam
     */
    public ServiceException(String... errParam) {
        super();
        this.errParam = errParam;
    }


    public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errParam = new String[]{message};
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.errParam = new String[]{message};
    }

    public ServiceException(String message) {
        super(message);
        this.errParam = new String[]{message};
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public String[] getErrParam() {
        return errParam;
    }



}
