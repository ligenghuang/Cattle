package com.zhifeng.cattle.modules.post;
/**
  *
  * @ClassName:     认证请求体
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/20 14:43
  * @Version:        1.0
 */

public class CertificationPost {

    private String pic_front;
    private String pic_back;
    private String name;
    private String idcard;

    public CertificationPost(String pic_front, String pic_back, String name, String idcard) {
        this.pic_front = pic_front;
        this.pic_back = pic_back;
        this.name = name;
        this.idcard = idcard;
    }

    public String getPic_front() {
        return pic_front == null ? "" : pic_front;
    }

    public void setPic_front(String pic_front) {
        this.pic_front = pic_front == null ? "" : pic_front;
    }

    public String getPic_back() {
        return pic_back == null ? "" : pic_back;
    }

    public void setPic_back(String pic_back) {
        this.pic_back = pic_back == null ? "" : pic_back;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getIdcard() {
        return idcard == null ? "" : idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? "" : idcard;
    }

    @Override
    public String toString() {
        return "CertificationPost{" +
                "pic_front='" + pic_front + '\'' +
                ", pic_back='" + pic_back + '\'' +
                ", name='" + name + '\'' +
                ", idcard='" + idcard + '\'' +
                '}';
    }
}
