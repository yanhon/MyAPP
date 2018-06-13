package com.hong_world.homemodle.net;


/**
 * Date: 2018/5/18. 14:21
 * Author: hong_world
 * Description:
 * Version:
 */

public class RegisterResp  {
    private String id;
    private String code;
    private String nickname;
    private String headimg;
    private String realname;
    private String userId;
    private String userName;
    private String phone;
    private String gender;
    private String birthday;
    private String privince;
    private String city;
    private String address;
    private String workId;
    private String workYear;
    private String workType;
    private String workNum;
    private String token;
    private String idcard;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPrivince() {
        return privince;
    }

    public void setPrivince(String privince) {
        this.privince = privince;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @Override
    public String toString() {
        return "RegisterResp{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headimg='" + headimg + '\'' +
                ", realname='" + realname + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", privince='" + privince + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", workId='" + workId + '\'' +
                ", workYear='" + workYear + '\'' +
                ", workType='" + workType + '\'' +
                ", workNum='" + workNum + '\'' +
                ", token='" + token + '\'' +
                ", idcard='" + idcard + '\'' +
                '}';
    }
}
