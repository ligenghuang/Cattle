package com.zhifeng.cattle.modules;

public class BannersBean {

    /**
     * picture :
     * title : 进口测试
     * url : www.baidu.com
     */

    private String picture;
    private String title;
    private String url;

    public String getPicture() {
        return picture == null ? "" : picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? "" : picture;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title == null ? "" : title;
    }

    public String getUrl() {
        return url == null ? "" : url;
    }

    public void setUrl(String url) {
        this.url = url == null ? "" : url;
    }
}
