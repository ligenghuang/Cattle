package com.zhifeng.cattle.modules;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *     author : lgh
 *     e-mail : 1045105946@qq.com
 *     time   : 2017/12/26 14:40
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class WXUserInfo implements Serializable {
    /**
     * openid : olmt4wfxS21G4VeeVX16_zUhZezY
     * nickname : 李文星
     * sex : 1
     * language : zh_CN
     * city.json : Shenzhen
     * province : Guangdong
     * country : CN
     * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/GLfaKQqialvlTfeqcicYjjTZicf8trpicFRvVqiahW3HZLcMFaNZeibuyL1wIbfSyxJMsfGnqshkdH5TZBlZx7RnaIbQ/132
     * privilege : []
     * unionid : o5aWQwAa7niCIXhAIRBOwglIJ7UQ
     */

    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;
    private List<?> privilege;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public List<?> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<?> privilege) {
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return "WXUserInfo{" +
                "openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", language='" + language + '\'' +
                ", city.json='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", unionid='" + unionid + '\'' +
                ", privilege=" + privilege +
                '}';
    }
}
