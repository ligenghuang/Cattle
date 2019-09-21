package com.zhifeng.cattle.modules;

public class HomeOtherSelfnavBean {
    /**
     * title : 肉类
     * image :
     * url : https://www.baidu.com/
     */

    private String title;
    private String image;
    private String url;

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title == null ? "" : title;
    }

    public String getImage() {
        return image == null ? "" : image;
    }

    public void setImage(String image) {
        this.image = image == null ? "" : image;
    }

    public String getUrl() {
        return url == null ? "" : url;
    }

    public void setUrl(String url) {
        this.url = url == null ? "" : url;
    }
}
