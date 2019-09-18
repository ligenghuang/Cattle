package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

public class TeamOrder {

    /**
     * status : 200
     * msg : 成功！
     * data : {"total":1,"per_page":"6","current_page":1,"last_page":1,"data":[{"order_id":1895,"order_sn":"20190715205357953549","consignee":"刘群","add_time":1563195237}]}
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
         * total : 1
         * per_page : 6
         * current_page : 1
         * last_page : 1
         * data : [{"order_id":1895,"order_sn":"20190715205357953549","consignee":"刘群","add_time":1563195237}]
         */

        private int total;
        private String per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getPer_page() {
            return per_page == null ? "" : per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page == null ? "" : per_page;
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
             * order_id : 1895
             * order_sn : 20190715205357953549
             * consignee : 刘群
             * add_time : 1563195237
             */

            private int order_id;
            private String order_sn;
            private String consignee;
            private int add_time;

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

            public String getConsignee() {
                return consignee == null ? "" : consignee;
            }

            public void setConsignee(String consignee) {
                this.consignee = consignee == null ? "" : consignee;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }
        }
    }
}
