package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

/**
  *
  * @ClassName:     订单列表实体类
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/11 14:55
  * @Version:        1.0
 */

public class OrderListDto {


    /**
     * status : 200
     * msg : 获取成功
     * data : [{"order_id":3163,"order_sn":"20190814165707582044","comment":0,"order_status":1,"pay_status":0,"shipping_status":0,"pay_type":1,"add_time":"2019-08-14 16:57:07","goods_price":"40000.00","total_amount":"40000.00","order_amount":"40000.00","shipping_price":"0.00","is_refund":1,"goods":[{"goods_name":"企业版 矿机","goods_id":75,"sku_id":75,"img":"http://zf8020.com/upload/images/goods/20190803156482339365585.png","spec_key_name":"规格:西数企业1T","goods_price":"10000.00","goods_num":4}],"goods_num":4,"status":1},{"order_id":1918,"order_sn":"20190716092024582047","comment":0,"order_status":1,"pay_status":0,"shipping_status":0,"pay_type":1,"add_time":"2019-07-16 09:20:24","goods_price":"500.00","total_amount":"500.00","order_amount":"500.00","shipping_price":"0.00","is_refund":1,"goods":[{"goods_name":"礼品商品","goods_id":40,"sku_id":23,"img":"http://zf8020.com/upload/images/goods/2019071015627391512588.png","spec_key_name":"规格:默认","goods_price":"500.00","goods_num":1}],"goods_num":1,"status":1}]
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
         * order_id : 3163
         * order_sn : 20190814165707582044
         * comment : 0
         * order_status : 1
         * pay_status : 0
         * shipping_status : 0
         * pay_type : 1
         * add_time : 2019-08-14 16:57:07
         * goods_price : 40000.00
         * total_amount : 40000.00
         * order_amount : 40000.00
         * shipping_price : 0.00
         * is_refund : 1
         * goods : [{"goods_name":"企业版 矿机","goods_id":75,"sku_id":75,"img":"http://zf8020.com/upload/images/goods/20190803156482339365585.png","spec_key_name":"规格:西数企业1T","goods_price":"10000.00","goods_num":4}]
         * goods_num : 4
         * status : 1
         */

        private int order_id;
        private String order_sn;
        private int comment;
        private int order_status;
        private int pay_status;
        private int shipping_status;
        private int pay_type;
        private String add_time;
        private String goods_price;
        private String total_amount;
        private String order_amount;
        private String shipping_price;
        private int is_refund;
        private int goods_num;
        private int status;
        private List<GoodsBean> goods;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn == null ? "" : order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn == null ? "" : order_sn;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }

        public int getShipping_status() {
            return shipping_status;
        }

        public void setShipping_status(int shipping_status) {
            this.shipping_status = shipping_status;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public String getAdd_time() {
            return add_time == null ? "" : add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time == null ? "" : add_time;
        }

        public String getGoods_price() {
            return goods_price == null ? "" : goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price == null ? "" : goods_price;
        }

        public String getTotal_amount() {
            return total_amount == null ? "0" : total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount == null ? "" : total_amount;
        }

        public String getOrder_amount() {
            return order_amount == null ? "" : order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount == null ? "" : order_amount;
        }

        public String getShipping_price() {
            return shipping_price == null ? "" : shipping_price;
        }

        public void setShipping_price(String shipping_price) {
            this.shipping_price = shipping_price == null ? "" : shipping_price;
        }

        public int getIs_refund() {
            return is_refund;
        }

        public void setIs_refund(int is_refund) {
            this.is_refund = is_refund;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<GoodsBean> getGoods() {
            if (goods == null) {
                return new ArrayList<>();
            }
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * goods_name : 企业版 矿机
             * goods_id : 75
             * sku_id : 75
             * img : http://zf8020.com/upload/images/goods/20190803156482339365585.png
             * spec_key_name : 规格:西数企业1T
             * goods_price : 10000.00
             * goods_num : 4
             */

            private String goods_name;
            private int goods_id;
            private int sku_id;
            private String img;
            private String spec_key_name;
            private String goods_price;
            private int goods_num;

            public String getGoods_name() {
                return goods_name == null ? "" : goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name == null ? "" : goods_name;
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

            public String getImg() {
                return img == null ? "" : img;
            }

            public void setImg(String img) {
                this.img = img == null ? "" : img;
            }

            public String getSpec_key_name() {
                return spec_key_name == null ? "" : spec_key_name;
            }

            public void setSpec_key_name(String spec_key_name) {
                this.spec_key_name = spec_key_name == null ? "" : spec_key_name;
            }

            public String getGoods_price() {
                return goods_price == null ? "" : goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price == null ? "" : goods_price;
            }

            public int getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }
        }
    }
}
