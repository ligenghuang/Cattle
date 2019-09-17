package com.zhifeng.cattle.modules;
/**
  *
  * @ClassName:     填写支付宝返回实体类
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/17 17:29
  * @Version:        1.0
 */

public class AliPayDto {


    /**
     * status : 200
     * msg : 编辑成功
     * data : {"alipay":"123456","alipay_name":"李"}
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
         * alipay : 123456
         * alipay_name : 李
         */

        private String alipay;
        private String alipay_name;

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
    }
}
