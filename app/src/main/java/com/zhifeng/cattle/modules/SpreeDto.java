package com.zhifeng.cattle.modules;
/**
  *
  * @ClassName:     首页优选单品
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/21 10:43
  * @Version:        1.0
 */

public class SpreeDto {
    /**
     * id : 1
     * title : 苹果
     * image :
     * status : 1
     * create_time : 1569664
     * url : https://www.baidu.com/
     * location : 1
     */

    private int id;
    private String title;
    private String image;
    private int status;
    private int create_time;
    private String url;
    private int location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public String getUrl() {
        return url == null ? "" : url;
    }

    public void setUrl(String url) {
        this.url = url == null ? "" : url;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
