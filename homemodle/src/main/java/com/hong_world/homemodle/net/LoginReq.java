package com.hong_world.homemodle.net;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Date: 2018/5/18. 14:21
 * Author: hong_world
 * Description:
 * Version:
 */

public class LoginReq  implements Parcelable {
    private String userName;
    private String password;

    public LoginReq() {
    }

    public LoginReq(String name, String pwd) {
        this.password = pwd;
        this.userName = name;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.password);
    }

    protected LoginReq(Parcel in) {
        this.userName = in.readString();
        this.password = in.readString();
    }

    public static final Creator<LoginReq> CREATOR = new Creator<LoginReq>() {
        @Override
        public LoginReq createFromParcel(Parcel source) {
            return new LoginReq(source);
        }

        @Override
        public LoginReq[] newArray(int size) {
            return new LoginReq[size];
        }
    };
}
