package com.zhifeng.cattle.utils.sku;

/**
 * description: Sku基本模型数据
 * autour: huang
 * date: 2019/9/19 21:27
 * update: 2019/9/19
 * version:
 */
public class BaseSkuModel {

    //base 属性
    private double price;//价格  我这没用到
    private long stock;//库存
    private int sku_id;
    private String name;

    public BaseSkuModel(double price, long stock, int sku_id, String name) {
        this.price = price;
        this.stock = stock;
        this.sku_id = sku_id;
        this.name = name;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public int getSku_id() {
        return sku_id;
    }

    public void setSku_id(int sku_id) {
        this.sku_id = sku_id;
    }
}
