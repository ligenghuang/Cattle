package com.zhifeng.cattle.modules;
/**
  *
  * @ClassName:     订单支付  支付宝
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/10/22 16:32
  * @Version:        1.0
 */

public class AlipayOrderDto {

    /**
     * status : 200
     * msg : 支付宝扫码地址
     * data : {"requestParams":"_input_charset=\"utf-8\"&body=\"M SQUARE TEST\"&currency=\"AUD\"&forex_biz=\"FP\"&notify_url=\"http://online.paylinx.cn/payment/paylinx_alipay_gateway/notify\"&out_trade_no=\"20191023101847105989605422\"&partner=\"2088031536995173\"&payment_type=\"1\"&product_code=\"NEW_WAP_OVERSEAS_SELLER\"&refer_url=\"EN/CN\"&secondary_merchant_id=\"201900002774\"&secondary_merchant_industry=\"7372\"&secondary_merchant_name=\"MONEY SQUARE GROUP PTY LTD\"&seller_id=\"2088031536995173\"&service=\"mobile.securitypay.pay\"&sign=\"XeRwKXBupfZygvnyUtX4%2FkPIrIDUXUf0lspa%2FBMN7CYlY7bLcE7vBjnrnQDThhaaqN81YK42SYchrvkmcgVU7OsbdD%2F5A2ksN9nYsXkoK7ojhcuPmcjf%2BZUZ6CgZ8D9kh0EE4oamGC6a5XWdmFsqjo0wR0YrKCOV3QBm7RtIiso%3D\"&sign_type=\"RSA\"&subject=\"M SQUARE TEST\"&total_fee=\"0\"&trade_information=\"{\"business_type\":4,\"total_quantity\":\"1\",\"goods_info\":\"01\"}\"","respCode":"SUCCESS","errorCode":null,"orderNo":"19102356591001","outTradeNo":"2019102355260","errorMsg":null,"sign":"C6970A63399C299D539B8E4CC4049F08"}
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
         * requestParams : _input_charset="utf-8"&body="M SQUARE TEST"&currency="AUD"&forex_biz="FP"&notify_url="http://online.paylinx.cn/payment/paylinx_alipay_gateway/notify"&out_trade_no="20191023101847105989605422"&partner="2088031536995173"&payment_type="1"&product_code="NEW_WAP_OVERSEAS_SELLER"&refer_url="EN/CN"&secondary_merchant_id="201900002774"&secondary_merchant_industry="7372"&secondary_merchant_name="MONEY SQUARE GROUP PTY LTD"&seller_id="2088031536995173"&service="mobile.securitypay.pay"&sign="XeRwKXBupfZygvnyUtX4%2FkPIrIDUXUf0lspa%2FBMN7CYlY7bLcE7vBjnrnQDThhaaqN81YK42SYchrvkmcgVU7OsbdD%2F5A2ksN9nYsXkoK7ojhcuPmcjf%2BZUZ6CgZ8D9kh0EE4oamGC6a5XWdmFsqjo0wR0YrKCOV3QBm7RtIiso%3D"&sign_type="RSA"&subject="M SQUARE TEST"&total_fee="0"&trade_information="{"business_type":4,"total_quantity":"1","goods_info":"01"}"
         * respCode : SUCCESS
         * errorCode : null
         * orderNo : 19102356591001
         * outTradeNo : 2019102355260
         * errorMsg : null
         * sign : C6970A63399C299D539B8E4CC4049F08
         */

        private String requestParams;
        private String respCode;
        private Object errorCode;
        private String orderNo;
        private String outTradeNo;
        private Object errorMsg;
        private String sign;

        public String getRequestParams() {
            return requestParams == null ? "" : requestParams;
        }

        public void setRequestParams(String requestParams) {
            this.requestParams = requestParams == null ? "" : requestParams;
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

        public String getSign() {
            return sign == null ? "" : sign;
        }

        public void setSign(String sign) {
            this.sign = sign == null ? "" : sign;
        }
    }
}
