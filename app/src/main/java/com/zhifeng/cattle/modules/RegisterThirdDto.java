package com.zhifeng.cattle.modules;

import java.io.Serializable;

/**
 * <pre>
 *     author : lgh
 *     e-mail : 1045105946@qq.com
 *     time   : 2017/11/24 19:11
 *     desc   :    第三方注册资料
 *     version: 1.0
 * </pre>
 */

public class RegisterThirdDto implements Serializable {
    private String headerImg;
    private String username;
    private String nickname;
    private int source;
    private String mobile;
    private String mobileCode;
    private String invitationCode;

    private String province;
    private String city;
    private String district;

    public RegisterThirdDto() {

    }

    public String getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }

    public RegisterThirdDto(String username, String nickname, int source, String mobile, String invitationCode, String province, String city, String district,String headerImg) {
        this.username = username;
        this.nickname = nickname;
        this.source = source;
        this.mobile = mobile;
        this.invitationCode = invitationCode;
        this.province = province;
        this.city = city;
        this.district = district;
        this.headerImg=headerImg;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "RegisterThirdDto{" +
                "headerImg='" + headerImg + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", source=" + source +
                ", mobile='" + mobile + '\'' +
                ", mobileCode='" + mobileCode + '\'' +
                ", invitationCode='" + invitationCode + '\'' +
                ", province='" + province + '\'' +
                ", city.json='" + city + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
