package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

public class DetailRecord {

    /**
     * status : 200
     * msg : 成功！
     * data : {"total":24,"per_page":"1","current_page":1,"last_page":24,"data":[{"id":2249,"source_type":10,"balance":"0.72000000","note":"平级奖","create_time":1564755667}]}
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
         * total : 24
         * per_page : 1
         * current_page : 1
         * last_page : 24
         * data : [{"id":2249,"source_type":10,"balance":"0.72000000","note":"平级奖","create_time":1564755667}]
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
            return per_page;
        }

        public void setPer_page(String per_page) {
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
             * id : 2249
             * source_type : 10
             * balance : 0.72000000
             * note : 平级奖
             * create_time : 1564755667
             */

            private int id;
            private int source_type;
            private String balance;
            private String note;
            private int create_time;

            public int getId() {
                return id;
            }

            public int getSource_type() {
                return source_type;
            }

            public String getBalance() {
                return balance == null ? "" : balance;
            }

            public String getNote() {
                return note == null ? "" : note;
            }

            public int getCreate_time() {
                return create_time;
            }
        }
    }
}
