package com.zhifeng.cattle.modules;

public class HomeImportOrFoodBean {
    /**
     * goods_id : 39
     * goods_name : 本草
     * img : http://zf8020.com/public/upload/images/goods/20190704156222257233167.png
     * desc : 商品简介商品简介
     * price : 200.00
     * original_price : 250.00
     */

    private int goods_id;
    private String goods_name;
    private String img;
    private String desc;
    private String price;
    private String original_price;

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name == null ? "" : goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name == null ? "" : goods_name;
    }

    public String getImg() {
        return img == null ? "" : img;
    }

    public void setImg(String img) {
        this.img = img == null ? "" : img;
    }

    public String getDesc() {
        return desc == null ? "" : desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? "" : desc;
    }

    public String getPrice() {
        return price == null ? "" : price;
    }

    public void setPrice(String price) {
        this.price = price == null ? "" : price;
    }

    public String getOriginal_price() {
        return original_price == null ? "" : original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price == null ? "" : original_price;
    }
}
