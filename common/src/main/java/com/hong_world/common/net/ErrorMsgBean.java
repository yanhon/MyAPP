package com.hong_world.common.net;

/**
 * Date: 2018/5/25. 11:25
 * Author: hong_world
 * Description: 网络请求失败or数据错误，返回code 和msg数据给view进行处理
 * Version:
 */

public class ErrorMsgBean {
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
