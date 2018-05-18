package com.hong_world.library.net.exception;

import java.io.IOException;

/**
 * Date: 2018/5/8. 18:16
 * Author: hong_world
 * Description:
 * Version:
 */

public class HttpStatusException extends IOException {
    private int errorCode;
    private String errorMsg;
    private String retry_after;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public HttpStatusException(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getRetry_after() {
        return retry_after;
    }

    public void setRetry_after(String retry_after) {
        this.retry_after = retry_after;
    }
}
