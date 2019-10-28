package com.zhifeng.cattle.modules;

public class RateDto {


    /**
     * status : 200
     * msg : 获取成功
     * data : {"aus_tormb":4.8271,"rmb_toaus":0.2072}
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
         * aus_tormb : 4.8271
         * rmb_toaus : 0.2072
         */

        private double aus_tormb;
        private double rmb_toaus;

        public double getAus_tormb() {
            return aus_tormb;
        }

        public void setAus_tormb(double aus_tormb) {
            this.aus_tormb = aus_tormb;
        }

        public double getRmb_toaus() {
            return rmb_toaus;
        }

        public void setRmb_toaus(double rmb_toaus) {
            this.rmb_toaus = rmb_toaus;
        }
    }
}
