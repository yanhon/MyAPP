package com.hong_world.myapp.bean;

/**
 * Date: 2017/11/1.17:15
 * Author: hong_world
 * Description:
 * Version:
 */

public class Task {
    private String phone;
    private String pwd;

    public Task() {
    }

    public Task(String phone, String pwd) {
        this.phone = phone;
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
