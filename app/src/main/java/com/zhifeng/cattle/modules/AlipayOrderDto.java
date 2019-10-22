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
     * requestParams : _input_charset="utf-8"&body="??"&currency="AUD"&forex_biz="FP"&notify_url="http://online.paylinx.cn/payment/paylinx_alipay_gateway/notify"&out_trade_no="20191022161804578476468791"&partner="2088031536995173"&payment_type="1"&product_code="NEW_WAP_OVERSEAS_SELLER"&refer_url="EN/CN"&secondary_merchant_id="201900002774"&secondary_merchant_industry="7372"&secondary_merchant_name="MONEY SQUARE GROUP PTY LTD"&seller_id="2088031536995173"&service="mobile.securitypay.pay"&sign="btZpTbFtsklsrWXx7zfE1QrP4gIQvp6P%2FwXJ05yMWhbrAimBi9ns9XVpkSIREpfL%2F1WPZtdR4E%2FPxawNZNgaNnboFNB1GOKzPYvlYhcejerM7D3hpkQWTLwNiBVyHODN4Q73niA46zn2YVRUnOjkftZe8KEeg6iwtnpDl0IOZwQ%3D"&sign_type="RSA"&subject="M SQUARE TEST"&total_fee="1.52"&trade_information="{"business_type":4,"total_quantity":"1","goods_info":"01"}"
     * respCode : SUCCESS
     * errorCode : null
     * orderNo : 19102256591012
     * outTradeNo : 2019102258950
     * errorMsg : null
     * sign : 311EC3992B0B0258CE6671CB9CF99712
     */

    private String requestParams;
    private String respCode;
    private Object errorCode;
    private String orderNo;
    private String outTradeNo;
    private Object errorMsg;
    private String sign;

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public Object getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Object errorCode) {
        this.errorCode = errorCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Object getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Object errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
