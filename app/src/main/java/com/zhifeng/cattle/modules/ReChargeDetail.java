package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

public class ReChargeDetail {


    /**
     * status : 200
     * msg : success
     * data : {"total":3,"per_page":20,"current_page":1,"last_page":1,"data":[{"note":"余额充值","balance":104,"source_id":"","old_balance":101109896,"log_type":1,"create_time":"2019-10-14 17:46:51"},{"note":"余额充值","balance":99999997,"source_id":"","old_balance":1109899,"log_type":1,"create_time":"2019-10-14 17:43:39"},{"note":"余额充值","balance":10000,"source_id":"","old_balance":1099899,"log_type":1,"create_time":"2019-10-14 16:53:35"}]}
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
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * total : 3
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"note":"余额充值","balance":104,"source_id":"","old_balance":101109896,"log_type":1,"create_time":"2019-10-14 17:46:51"},{"note":"余额充值","balance":99999997,"source_id":"","old_balance":1109899,"log_type":1,"create_time":"2019-10-14 17:43:39"},{"note":"余额充值","balance":10000,"source_id":"","old_balance":1099899,"log_type":1,"create_time":"2019-10-14 16:53:35"}]
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
             * note : 余额充值
             * balance : 104
             * source_id :
             * old_balance : 101109896
             * log_type : 1
             * create_time : 2019-10-14 17:46:51
             */

            private String note;
            private double balance;
            private String source_id;
            private double old_balance;
            private int log_type;
            private String create_time;
            private int status;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getNote() {
                return note == null ? "" : note;
            }

            public void setNote(String note) {
                this.note = note == null ? "" : note;
            }

            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public String getSource_id() {
                return source_id == null ? "" : source_id;
            }

            public void setSource_id(String source_id) {
                this.source_id = source_id == null ? "" : source_id;
            }

            public double getOld_balance() {
                return old_balance;
            }

            public void setOld_balance(double old_balance) {
                this.old_balance = old_balance;
            }

            public int getLog_type() {
                return log_type;
            }

            public void setLog_type(int log_type) {
                this.log_type = log_type;
            }

            public String getCreate_time() {
                return create_time == null ? "" : create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time == null ? "" : create_time;
            }
        }
    }
}
