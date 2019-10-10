package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class OrderComment {
    private String order_id;
    private String goods_id;
    private String sku_id;
    private String describe;
    private String logistics;
    private String service;
    private String content;
    private List<String> img;

    public OrderComment(String order_id, String goods_id, String sku_id, String describe, String logistics, String service, String content, List<String> img) {
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

    public String getDescribe() {
        return describe == null ? "" : describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? "" : describe;
    }

    public String getLogistics() {
        return logistics == null ? "" : logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics == null ? "" : logistics;
    }

    public String getService() {
        return service == null ? "" : service;
    }

    public void setService(String service) {
        this.service = service == null ? "" : service;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content == null ? "" : content;
    }

    public List<String> getImg() {
        if (img == null) {
            return new ArrayList<>();
        }
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "OrderComment{" +
                "order_id='" + order_id + '\'' +
                ", goods_id='" + goods_id + '\'' +
                ", sku_id='" + sku_id + '\'' +
                ", describe=" + describe +
                ", logistics=" + logistics +
                ", service=" + service +
                ", content='" + content + '\'' +
                ", img=" + img +
                '}';
    }
}
