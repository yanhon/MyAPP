package com.hong_world.library.net.exception;

/**
 * Date: 2018/5/24. 11:45
 * Author: hong_world
 * Description:
 * Version:
 */

public class DefaultErrorBundle {
    private final static String DEFAULT_ERROR_MSG = "未知错误";
    private final Exception mException;

    public DefaultErrorBundle(Exception exception) {
        mException = exception;
    }

    public Exception getException() {
        return mException;
    }

    public String getErrorMessage() {
        return (mException != null) ? mException.getMessage() : DEFAULT_ERROR_MSG;
    }
}
