package com.hong_world.myapp.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hong_world.myapp.BR;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Date: 2017/11/1.17:15
 * Author: hong_world
 * Description:
 * Version:
 */
@Entity
public class Task extends BaseObservable {
    @Id
    private String phone;
    @Property
    private String pwd;

    public Task() {
    }

    @Generated(hash = 1756216785)
    public Task(String phone, String pwd) {
        this.phone = phone;
        this.pwd = pwd;
    }


    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
        notifyPropertyChanged(BR.pwd);
    }
}
