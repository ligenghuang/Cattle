package com.zhifeng.cattle.modules;
/**
  *
  * @ClassName:     我的余额返回实体类
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/11 9:46
  * @Version:        1.0
 */

public class BalanceDto {

    /**
     * status : 200
     * msg : success
     * data : {"remainder_money":"0.00000000","alipay":null,"alipay_name":"","rate":0.006}
     */

    private int status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * remainder_money : 0.00000000
         * alipay : null
         * alipay_name :
         * rate : 0.006
         */

        private String remainder_money;
        private String alipay;
        private String alipay_name;
        private double rate;

        public String getRemainder_money() {
            return remainder_money == null ? "" : remainder_money;
        }

        public void setRemainder_money(String remainder_money) {
            this.remainder_money = remainder_money == null ? "" : remainder_money;
        }

        public String getAlipay() {
            return alipay == null ? "" : alipay;
        }

        public void setAlipay(String alipay) {
            this.alipay = alipay == null ? "" : alipay;
        }

        public String getAlipay_name() {
            return alipay_name == null ? "" : alipay_name;
        }

        public void setAlipay_name(String alipay_name) {
            this.alipay_name = alipay_name == null ? "" : alipay_name;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }
    }
}
