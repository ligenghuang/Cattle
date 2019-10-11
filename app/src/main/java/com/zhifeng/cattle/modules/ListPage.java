package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

public class ListPage {


    /**
     * status : 200
     * msg : 获取成功
     * data : [{"goods_id":89,"goods_name":"鲫鱼1","cat_id2":28,"img":"http://zf8020.com/upload/images/goods/20190929156974480927600.png","price":"521.00","original_price":"120.00","attr_name":[],"comment":1},{"goods_id":88,"goods_name":"小然然","cat_id2":28,"img":"http://zf8020.com/upload/images/goods/20190929156974188514326.png","price":"100.00","original_price":"120.00","attr_name":[],"comment":0},{"goods_id":20,"goods_name":"美的（Midea） 三门冰箱 2","cat_id2":28,"img":"http://zf8020.com/upload/images/goods/20190704156222275856273.png","price":"5000.00","original_price":"5500.00","attr_name":[],"comment":0}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * goods_id : 89
         * goods_name : 鲫鱼1
         * cat_id2 : 28
         * img : http://zf8020.com/upload/images/goods/20190929156974480927600.png
         * price : 521.00
         * original_price : 120.00
         * attr_name : []
         * comment : 1
         */

        private int goods_id;
        private String goods_name;
        private int cat_id2;
        private String img;
        private String price;
        private String original_price;
        private int comment;
        private List<?> attr_name;

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

        public int getCat_id2() {
            return cat_id2;
        }

        public void setCat_id2(int cat_id2) {
            this.cat_id2 = cat_id2;
        }

        public String getImg() {
            return img == null ? "" : img;
        }

        public void setImg(String img) {
            this.img = img == null ? "" : img;
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

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public List<?> getAttr_name() {
            if (attr_name == null) {
                return new ArrayList<>();
            }
            return attr_name;
        }

        public void setAttr_name(List<?> attr_name) {
            this.attr_name = attr_name;
        }
    }
}
