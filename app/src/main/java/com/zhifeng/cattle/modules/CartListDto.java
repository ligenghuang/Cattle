package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

/**
  *
  * @ClassName:     购物车列表实体类
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/10 12:25
  * @Version:        1.0
 */

public class CartListDto {

    /**
     * status : 200
     * msg : 成功
     * data : [{"cart_id":3695,"selected":0,"session_id":"","user_id":27869,"groupon_id":0,"goods_id":72,"goods_sn":"","goods_name":"商务保温杯","market_price":"0.00","goods_price":"500.00","member_goods_price":"500.00","subtotal_price":"1500.00","sku_id":69,"goods_num":3,"spec_key_name":"规格:黑色","img":"http://local.ldp2.com/upload/images/goods/20190716156324016790465.png","single_number":0,"inventory":991}]
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
         * cart_id : 3695
         * selected : 0
         * session_id :
         * user_id : 27869
         * groupon_id : 0
         * goods_id : 72
         * goods_sn :
         * goods_name : 商务保温杯
         * market_price : 0.00
         * goods_price : 500.00
         * member_goods_price : 500.00
         * subtotal_price : 1500.00
         * sku_id : 69
         * goods_num : 3
         * spec_key_name : 规格:黑色
         * img : http://local.ldp2.com/upload/images/goods/20190716156324016790465.png
         * single_number : 0
         * inventory : 991
         */

        private int cart_id;
        private int selected;
        private String session_id;
        private int user_id;
        private int groupon_id;
        private int goods_id;
        private String goods_sn;
        private String goods_name;
        private String market_price;
        private String goods_price;
        private String member_goods_price;
        private String subtotal_price;
        private int sku_id;
        private int goods_num;
        private String spec_key_name;
        private String img;
        private int single_number;
        private int inventory;

        public int getCart_id() {
            return cart_id;
        }

        public void setCart_id(int cart_id) {
            this.cart_id = cart_id;
        }

        public int getSelected() {
            return selected;
        }

        public void setSelected(int selected) {
            this.selected = selected;
        }

        public String getSession_id() {
            return session_id == null ? "" : session_id;
        }

        public void setSession_id(String session_id) {
            this.session_id = session_id == null ? "" : session_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getGroupon_id() {
            return groupon_id;
        }

        public void setGroupon_id(int groupon_id) {
            this.groupon_id = groupon_id;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_sn() {
            return goods_sn == null ? "" : goods_sn;
        }

        public void setGoods_sn(String goods_sn) {
            this.goods_sn = goods_sn == null ? "" : goods_sn;
        }

        public String getGoods_name() {
            return goods_name == null ? "" : goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name == null ? "" : goods_name;
        }

        public String getMarket_price() {
            return market_price == null ? "" : market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price == null ? "" : market_price;
        }

        public String getGoods_price() {
            return goods_price == null ? "0" : goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price == null ? "" : goods_price;
        }

        public String getMember_goods_price() {
            return member_goods_price == null ? "" : member_goods_price;
        }

        public void setMember_goods_price(String member_goods_price) {
            this.member_goods_price = member_goods_price == null ? "" : member_goods_price;
        }

        public String getSubtotal_price() {
            return subtotal_price == null ? "" : subtotal_price;
        }

        public void setSubtotal_price(String subtotal_price) {
            this.subtotal_price = subtotal_price == null ? "" : subtotal_price;
        }

        public int getSku_id() {
            return sku_id;
        }

        public void setSku_id(int sku_id) {
            this.sku_id = sku_id;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public String getSpec_key_name() {
            return spec_key_name == null ? "" : spec_key_name;
        }

        public void setSpec_key_name(String spec_key_name) {
            this.spec_key_name = spec_key_name == null ? "" : spec_key_name;
        }

        public String getImg() {
            return img == null ? "" : img;
        }

        public void setImg(String img) {
            this.img = img == null ? "" : img;
        }

        public int getSingle_number() {
            return single_number;
        }

        public void setSingle_number(int single_number) {
            this.single_number = single_number;
        }

        public int getInventory() {
            return inventory;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
        }
    }
}
