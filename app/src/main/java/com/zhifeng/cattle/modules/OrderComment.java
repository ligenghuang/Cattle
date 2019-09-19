package com.zhifeng.cattle.modules;

import java.util.Arrays;
import java.util.Base64;

public class OrderComment {
    private String order_id;
    private String goods_id;
    private String sku_id;
    private int describe;
    private int logistics;
    private int service;
    private String content;
    private String[] img;

    public OrderComment(String order_id, String goods_id, String sku_id, int describe, int logistics, int service, String content, String[] img) {
        this.order_id = order_id;
        this.goods_id = goods_id;
        this.sku_id = sku_id;
        this.describe = describe;
        this.logistics = logistics;
        this.service = service;
        this.content = content;
        this.img = img;
    }

    public String getOrder_id() {
        return order_id == null ? "" : order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id == null ? "" : order_id;
    }

    public String getGoods_id() {
        return goods_id == null ? "" : goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id == null ? "" : goods_id;
    }

    public String getSku_id() {
        return sku_id == null ? "" : sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id == null ? "" : sku_id;
    }

    public int getDescribe() {
        return describe;
    }

    public void setDescribe(int describe) {
        this.describe = describe;
    }

    public int getLogistics() {
        return logistics;
    }

    public void setLogistics(int logistics) {
        this.logistics = logistics;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content == null ? "" : content;
    }

    public String[] getImg() {
        return img;
    }

    public void setImg(String[] img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "{" +
                "\"order_id\":\'" + order_id + "\'" +
                ", \"goods_id\":\'" + goods_id + "\'" +
                ", \"sku_id\":\'" + sku_id + "\'" +
                ", \"describe\":\'" + describe + "\'" +
                ", \"logistics\":\'" + logistics + "\'" +
                ", \"service\":\'" + service + "\'" +
                ", \"content\":\'" + content + "\'" +
                ", \"img\":" + Arrays.toString(img) +
                '}';
    }
}
