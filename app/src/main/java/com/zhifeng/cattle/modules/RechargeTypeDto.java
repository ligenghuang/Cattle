package com.zhifeng.cattle.modules;

public class RechargeTypeDto {


    /**
     * status : 200
     * msg : 获取成功！
     * data : {"wechat_qr_code":"http://zf8020.com/public/upload/images/site/2019102815722534718538.png","alipay_qr_code":"http://zf8020.com/public/upload/images/site/20191028157225347113699.png"}
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
         * wechat_qr_code : http://zf8020.com/public/upload/images/site/2019102815722534718538.png
         * alipay_qr_code : http://zf8020.com/public/upload/images/site/20191028157225347113699.png
         */

        private String wechat_qr_code;
        private String alipay_qr_code;

        public String getWechat_qr_code() {
            return wechat_qr_code == null ? "" : wechat_qr_code;
        }

        public void setWechat_qr_code(String wechat_qr_code) {
            this.wechat_qr_code = wechat_qr_code == null ? "" : wechat_qr_code;
        }

        public String getAlipay_qr_code() {
            return alipay_qr_code == null ? "" : alipay_qr_code;
        }

        public void setAlipay_qr_code(String alipay_qr_code) {
            this.alipay_qr_code = alipay_qr_code == null ? "" : alipay_qr_code;
        }
    }
}
