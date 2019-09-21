package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

public class ListPage {

    /**
     * status : 200
     * msg : 获取成功
     * data : {"goods":{"total":3,"per_page":20,"current_page":1,"last_page":1,"data":[{"goods_id":72,"goods_name":"商务保温杯","img":"http://zf8020.com/upload/images/goods/20190716156324016790465.png","price":"500.00","original_price":"0.00"},{"goods_id":45,"goods_name":"礼品专区商品1","img":"http://zf8020.com/upload/images/goods/20190710156274057499262.png","price":"500.00","original_price":"0.00"},{"goods_id":40,"goods_name":"礼品商品","img":"http://zf8020.com/upload/images/goods/2019071015627391512588.png","price":"500.00","original_price":"500.00"}]}}
     */

    private int status;
    private String msg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * goods : {"total":3,"per_page":20,"current_page":1,"last_page":1,"data":[{"goods_id":72,"goods_name":"商务保温杯","img":"http://zf8020.com/upload/images/goods/20190716156324016790465.png","price":"500.00","original_price":"0.00"},{"goods_id":45,"goods_name":"礼品专区商品1","img":"http://zf8020.com/upload/images/goods/20190710156274057499262.png","price":"500.00","original_price":"0.00"},{"goods_id":40,"goods_name":"礼品商品","img":"http://zf8020.com/upload/images/goods/2019071015627391512588.png","price":"500.00","original_price":"500.00"}]}
         */

        private GoodsBean goods;

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * total : 3
             * per_page : 20
             * current_page : 1
             * last_page : 1
             * data : [{"goods_id":72,"goods_name":"商务保温杯","img":"http://zf8020.com/upload/images/goods/20190716156324016790465.png","price":"500.00","original_price":"0.00"},{"goods_id":45,"goods_name":"礼品专区商品1","img":"http://zf8020.com/upload/images/goods/20190710156274057499262.png","price":"500.00","original_price":"0.00"},{"goods_id":40,"goods_name":"礼品商品","img":"http://zf8020.com/upload/images/goods/2019071015627391512588.png","price":"500.00","original_price":"500.00"}]
             */

            private int total;
            private int per_page;
            private int current_page;
            private int last_page;
            private List<DataBean> data;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
                this.per_page = per_page;
            }

            public int getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(int current_page) {
                this.current_page = current_page;
            }

            public int getLast_page() {
                return last_page;
            }

            public void setLast_page(int last_page) {
                this.last_page = last_page;
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
                 * goods_id : 72
                 * goods_name : 商务保温杯
                 * img : http://zf8020.com/upload/images/goods/20190716156324016790465.png
                 * price : 500.00
                 * original_price : 0.00
                 */

                private int goods_id;
                private String goods_name;
                private String img;
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
        }
    }
}
