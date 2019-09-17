package com.zhifeng.cattle.modules;

import java.util.ArrayList;
import java.util.List;

/**
  *
  * @ClassName:     提现明细列表实体类
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/17 14:04
  * @Version:        1.0
 */

public class WithdrawalListDto {

    /**
     * status : 200
     * msg : success
     * data : {"list":{"total":2,"per_page":20,"current_page":1,"last_page":1,"data":[{"createtime":"2019.08.14","money":"3.00","taxfee":"0.02","status":1},{"createtime":"2019.08.14","money":"3.00","taxfee":"0.02","status":1}]}}
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
         * list : {"total":2,"per_page":20,"current_page":1,"last_page":1,"data":[{"createtime":"2019.08.14","money":"3.00","taxfee":"0.02","status":1},{"createtime":"2019.08.14","money":"3.00","taxfee":"0.02","status":1}]}
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
             * total : 2
             * per_page : 20
             * current_page : 1
             * last_page : 1
             * data : [{"createtime":"2019.08.14","money":"3.00","taxfee":"0.02","status":1},{"createtime":"2019.08.14","money":"3.00","taxfee":"0.02","status":1}]
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
                 * createtime : 2019.08.14
                 * money : 3.00
                 * taxfee : 0.02
                 * status : 1
                 */

                private String createtime;
                private String money;
                private String taxfee;
                private int status;

                public String getCreatetime() {
                    return createtime == null ? "" : createtime;
                }

                public void setCreatetime(String createtime) {
                    this.createtime = createtime == null ? "" : createtime;
                }

                public String getMoney() {
                    return money == null ? "" : money;
                }

                public void setMoney(String money) {
                    this.money = money == null ? "" : money;
                }

                public String getTaxfee() {
                    return taxfee == null ? "" : taxfee;
                }

                public void setTaxfee(String taxfee) {
                    this.taxfee = taxfee == null ? "" : taxfee;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }
        }
    }
}
