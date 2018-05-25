package com.hong_world.library.net.exception;

/**
 * Date: 2018/5/8. 18:22
 * Author: hong_world
 * Description:
 * Version:
 */

public class APIResultException extends Exception {

    private int errorCode;
    private String errorMsg = "未知错误";

    public APIResultException(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public APIResultException(int errorCode) {
        this.errorCode = errorCode;
    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "APIResultException{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}