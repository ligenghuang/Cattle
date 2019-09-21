package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

public class SearchGoods {

    /**
     * data : [{"goods_id":75,"goods_name":"企业版 矿机","price":"19900.00","picture":"http://zf8020.com/public/upload/images/goods/20190904156759240748034.png","praise":"100%"}]
     * status : 1
     * msg : 获取数据成功
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
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? "" : msg;
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
         * goods_id : 75
         * goods_name : 企业版 矿机
         * price : 19900.00
         * picture : http://zf8020.com/public/upload/images/goods/20190904156759240748034.png
         * praise : 100%
         */

        private int goods_id;
        private String goods_name;
        private String price;
        private String picture;
        private String praise;

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

        public String getPrice() {
            return price == null ? "" : price;
        }

        public void setPrice(String price) {
            this.price = price == null ? "" : price;
        }

        public String getPicture() {
            return picture == null ? "" : picture;
        }

        public void setPicture(String picture) {
            this.picture = picture == null ? "" : picture;
        }

        public String getPraise() {
            return praise == null ? "" : praise;
        }

        public void setPraise(String praise) {
            this.praise = praise == null ? "" : praise;
        }
    }
}
