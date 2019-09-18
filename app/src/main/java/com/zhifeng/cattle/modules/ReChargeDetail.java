package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

public class ReChargeDetail {

    /**
     * status : 200
     * msg : success
     * data : {"list":{"total":3,"per_page":20,"current_page":1,"last_page":1,"data":[{"create_time":"2019.07.17","amount":"0.02","order_status":1},{"create_time":"2019.07.17","amount":"50.00","order_status":0},{"create_time":"2019.07.17","amount":"50.00","order_status":0}]}}
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
         * list : {"total":3,"per_page":20,"current_page":1,"last_page":1,"data":[{"create_time":"2019.07.17","amount":"0.02","order_status":1},{"create_time":"2019.07.17","amount":"50.00","order_status":0},{"create_time":"2019.07.17","amount":"50.00","order_status":0}]}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * total : 3
             * per_page : 20
             * current_page : 1
             * last_page : 1
             * data : [{"create_time":"2019.07.17","amount":"0.02","order_status":1},{"create_time":"2019.07.17","amount":"50.00","order_status":0},{"create_time":"2019.07.17","amount":"50.00","order_status":0}]
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
                 * create_time : 2019.07.17
                 * amount : 0.02
                 * order_status : 1
                 */

                private String create_time;
                private String amount;
                private int order_status;

                public String getCreate_time() {
                    return create_time == null ? "" : create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time == null ? "" : create_time;
                }

                public String getAmount() {
                    return amount == null ? "" : amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount == null ? "" : amount;
                }

                public int getOrder_status() {
                    return order_status;
                }

                public void setOrder_status(int order_status) {
                    this.order_status = order_status;
                }
            }
        }
    }
}
