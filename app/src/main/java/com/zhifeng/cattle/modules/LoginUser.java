package com.zhifeng.cattle.modules;

public class LoginUser {
    private String token;
    private String realname;
    private String mobile;
    private String avatar;

    public LoginUser(String token, String realname, String mobile, String avatar) {
        this.token = token;
        this.realname = realname;
        this.mobile = mobile;
        this.avatar = avatar;
    }

    public String getToken() {
        return token == null ? "" : token;
    }

    public void setToken(String token) {
        this.token = token == null ? "" : token;
    }

    public String getRealname() {
        return realname == null ? "" : realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? "" : realname;
    }

    public String getMobile() {
        return mobile == null ? "" : mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? "" : mobile;
    }

    public String getAvatar() {
        return avatar == null ? "" : avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? "" : avatar;
    }
}
