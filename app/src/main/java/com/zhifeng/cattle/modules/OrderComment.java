package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class OrderComment {
    private int order_id;
    private int goods_id;
    private int sku_id;
    private String descrie;
    private String logistics;
    private String service;
    private String content;
    private List<String> img;

    public OrderComment(int order_id, int goods_id, int sku_id, String descrie, String logistics, String service, String content, List<String> img) {
        this.order_id = order_id;
        this.goods_id = goods_id;
        this.sku_id = sku_id;
        this.descrie = descrie;
        this.logistics = logistics;
        this.service = service;
        this.content = content;
        this.img = img;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getSku_id() {
        return sku_id;
    }

    public void setSku_id(int sku_id) {
        this.sku_id = sku_id;
    }

    public String getDescribe() {
        return descrie == null ? "" : descrie;
    }

    public void setDescribe(String describe) {
        this.descrie = describe == null ? "" : describe;
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
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"order_id\":\"")
                .append(order_id).append('\"');
        sb.append(",\"goods_id\":\"")
                .append(goods_id).append('\"');
        sb.append(",\"sku_id\":\"")
                .append(sku_id).append('\"');
        sb.append(",\"descrie\":\"")
                .append(descrie).append('\"');
        sb.append(",\"logistics\":\"")
                .append(logistics).append('\"');
        sb.append(",\"service\":\"")
                .append(service).append('\"');
        sb.append(",\"content\":\"")
                .append(content).append('\"');
        sb.append(",\"img\":")
                .append(img);
        sb.append('}');
        return sb.toString();
    }
}
