package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

public class CheckDetail {

    /**
     * data : {"total":3,"per_page":"20","current_page":1,"last_page":1,"data":[{"note":"下单消费","balance":0.72,"source_id":"","create_time":"2019-08-02 22:21:07","log_type":0},{"note":"下单消费","balance":0.72,"source_id":"","create_time":"2019-08-02 22:21:07","log_type":0},{"note":"下单消费","balance":0.72,"source_id":"","create_time":"2019-08-02 22:21:07","log_type":0}]}
     * msg : success
     * status : 200
     */

    private DataBeanX data;
    private String msg;
    private int status;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? "" : msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBeanX {
        /**
         * total : 3
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"note":"下单消费","balance":0.72,"source_id":"","create_time":"2019-08-02 22:21:07","log_type":0},{"note":"下单消费","balance":0.72,"source_id":"","create_time":"2019-08-02 22:21:07","log_type":0},{"note":"下单消费","balance":0.72,"source_id":"","create_time":"2019-08-02 22:21:07","log_type":0}]
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
             * note : 下单消费
             * balance : 0.72
             * source_id :
             * create_time : 2019-08-02 22:21:07
             * log_type : 0
             */

            private String note;
            private double balance;
            private String source_id;
            private String create_time;
            private int log_type;

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

            public String getCreate_time() {
                return create_time == null ? "" : create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time == null ? "" : create_time;
            }

            public int getLog_type() {
                return log_type;
            }

            public void setLog_type(int log_type) {
                this.log_type = log_type;
            }
        }
    }
}
