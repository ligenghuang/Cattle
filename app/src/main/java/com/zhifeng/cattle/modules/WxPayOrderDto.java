package com.zhifeng.cattle.modules;

public class WxPayOrderDto {


    /**
     * status : 200
     * msg : 微信扫码地址
     * data : {"respCode":"SUCCESS","errorCode":null,"orderNo":"19102457601022","outTradeNo":"2019102420069","errorMsg":null,"appid":"wx19ac5b21ebe2b173","partnerid":"326609695","prepayid":"wx24155848083320fee002c7b11496629000","pack":"Sign=WXPay","noncestr":"gmfunxbc39zk6yv2d12zq4gim7birg36","timestamp":"1571903928","sign":"633096809260902B4EAF7C08FE55AD90"}
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
         * respCode : SUCCESS
         * errorCode : null
         * orderNo : 19102457601022
         * outTradeNo : 2019102420069
         * errorMsg : null
         * appid : wx19ac5b21ebe2b173
         * partnerid : 326609695
         * prepayid : wx24155848083320fee002c7b11496629000
         * pack : Sign=WXPay
         * noncestr : gmfunxbc39zk6yv2d12zq4gim7birg36
         * timestamp : 1571903928
         * sign : 633096809260902B4EAF7C08FE55AD90
         */

        private String respCode;
        private Object errorCode;
        private String orderNo;
        private String outTradeNo;
        private Object errorMsg;
        private String appid;
        private String partnerid;
        private String prepayid;
        private String pack;
        private String noncestr;
        private String timestamp;
        private String sign;

        @Override
        public String toString() {
            return "DataBean{" +
                    "respCode='" + respCode + '\'' +
                    ", errorCode=" + errorCode +
                    ", orderNo='" + orderNo + '\'' +
                    ", outTradeNo='" + outTradeNo + '\'' +
                    ", errorMsg=" + errorMsg +
                    ", appid='" + appid + '\'' +
                    ", partnerid='" + partnerid + '\'' +
                    ", prepayid='" + prepayid + '\'' +
                    ", pack='" + pack + '\'' +
                    ", noncestr='" + noncestr + '\'' +
                    ", timestamp='" + timestamp + '\'' +
                    ", sign='" + sign + '\'' +
                    '}';
        }

        public String getRespCode() {
            return respCode == null ? "" : respCode;
        }

        public void setRespCode(String respCode) {
            this.respCode = respCode == null ? "" : respCode;
        }

        public Object getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Object errorCode) {
            this.errorCode = errorCode;
        }

        public String getOrderNo() {
            return orderNo == null ? "" : orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo == null ? "" : orderNo;
        }

        public String getOutTradeNo() {
            return outTradeNo == null ? "" : outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo == null ? "" : outTradeNo;
        }

        public Object getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(Object errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getAppid() {
            return appid == null ? "" : appid;
        }

        public void setAppid(String appid) {
            this.appid = appid == null ? "" : appid;
        }

        public String getPartnerid() {
            return partnerid == null ? "" : partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid == null ? "" : partnerid;
        }

        public String getPrepayid() {
            return prepayid == null ? "" : prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid == null ? "" : prepayid;
        }

        public String getPack() {
            return pack == null ? "" : pack;
        }

        public void setPack(String pack) {
            this.pack = pack == null ? "" : pack;
        }

        public String getNoncestr() {
            return noncestr == null ? "" : noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr == null ? "" : noncestr;
        }

        public String getTimestamp() {
            return timestamp == null ? "" : timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp == null ? "" : timestamp;
        }

        public String getSign() {
            return sign == null ? "" : sign;
        }

        public void setSign(String sign) {
            this.sign = sign == null ? "" : sign;
        }
    }
}
