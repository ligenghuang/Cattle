package com.zhifeng.cattle.utils.pay.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     author : lgh
 *     e-mail : 1045105946@qq.com
 *     time   : 2017/12/11 19:31
 *     desc   :    * <!--支付宝支付配置-->
 */
public class Alipayer {

    public static final int SDK_PAY_FLAG = 1;

    public static final String MSG_KEY_RESULT_STATUS = "MSG_KEY_RESULT_STATUS";
    public static final String MSG_KEY_TIPS_TEXT = "MSG_KEY_TIPS_TEXT";

    public static final String RESULT_STATUS_SUCCESS = "9000";
    public static final String RESULT_STATUS_CONFIRM = "8000";
    public static final String RESULT_STATUS_CANCEL = "6001";
    public static final String RESULT_STATUS_NETWORK_ERROR = "6002";

    private Context mContext;
    private Handler.Callback mHandlerCallback;

    public Alipayer(Context context, Handler.Callback handlerCallback) {
        this.mContext = context;
        this.mHandlerCallback = handlerCallback;
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    String tipsText = "";
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    String resultMemo = payResult.getMemo();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, RESULT_STATUS_SUCCESS)) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        tipsText = "支付成功";
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, RESULT_STATUS_CONFIRM)) {
                            tipsText = "支付宝支付确认中";
                        } else if (TextUtils.equals(resultStatus, RESULT_STATUS_CANCEL)) {
                            tipsText = "已取消支付";
                        } else if (TextUtils.equals(resultStatus, RESULT_STATUS_NETWORK_ERROR)) {
                            tipsText = "网络连接出错";
                        }else {
                            tipsText = resultMemo;
                        }
//                        else {
//                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
//                            tipsText = "支付宝支付失败";
//                        }
                    }
                    Message message = Message.obtain();
                    Bundle data = new Bundle();
                    data.putString(MSG_KEY_RESULT_STATUS, resultStatus);
                    data.putString(MSG_KEY_TIPS_TEXT, tipsText);
                    message.setData(data);
                    if (mHandlerCallback != null) {
                        mHandlerCallback.handleMessage(message);
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    /**
     * 支付宝支付业务
     *
     * @param payInfoBean
     */
    public void payV2(PayInfoBean payInfoBean) {
        if (TextUtils.isEmpty(payInfoBean.getAppId()) || (TextUtils.isEmpty(payInfoBean.getRsa2Private()) && TextUtils.isEmpty(payInfoBean.getRsaPrivate()))) {
            new AlertDialog.Builder(mContext).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            ((Activity) mContext).finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        payInfoBean.setRsa2(payInfoBean.getRsa2Private().length() > 0);
        Map<String, String> params = buildOrderParamMap(payInfoBean);
        final String orderInfo = OrderInfoUtil2_0.buildOrderParam(params);

        //sign数据应从服务端返回
//        String privateKey = payInfoBean.isRsa2() ? payInfoBean.getRsa2Private() : payInfoBean.getRsaPrivate();
//        String sign = OrderInfoUtil2_0.getSign(params, privateKey, payInfoBean.isRsa2());
//        final String orderInfo = orderParam + "&" + sign;

        payV2(orderInfo);
    }

    /**
     * 支付
     *
     * @param orderInfo 组装好的支付信息
     */
    public void payV2(final String orderInfo) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask((Activity) mContext);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 构造支付订单参数列表
     *
     * @param payInfoBean
     * @return
     */
    public static Map<String, String> buildOrderParamMap(PayInfoBean payInfoBean) {
        Map<String, String> keyValues = new HashMap<String, String>();

        keyValues.put("app_id", payInfoBean.getAppId());

        keyValues.put(
                "biz_content", "{\"timeout_express\":\"30m\"," +
                        "\"product_code\":\"QUICK_MSECURITY_PAY\"," +
                        "\"total_amount\":\"" + payInfoBean.getTotalAmount() + "\"," +
                        "\"subject\":\"1\"," +
                        "\"body\":\"" + payInfoBean.getBody() + "\"," +
                        "\"out_trade_no\":\"" + payInfoBean.getOutTradeNo() + "\"," +
                        "\"seller_id\":\"" + payInfoBean.getSellerId() + "\"}");

        keyValues.put("charset", "utf-8");

        keyValues.put("method", "alipay.trade.app.pay");

        keyValues.put("sign_type", payInfoBean.isRsa2() ? "RSA2" : "RSA");

        if (TextUtils.isEmpty(payInfoBean.getTimestamp())) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(new Date());
            payInfoBean.setTimestamp(date);
        }

        keyValues.put("timestamp", payInfoBean.getTimestamp());

        keyValues.put("version", "1.0");

        if (!TextUtils.isEmpty(payInfoBean.getNotifyUrl())) {
            keyValues.put("notify_url", payInfoBean.getNotifyUrl());
        }

        keyValues.put("sign", payInfoBean.getSign());

        return keyValues;
    }

    /**
     * PayInfoBean
     */
    public static class PayInfoBean {
        private String appId;
        private String rsa2Private;
        private String rsaPrivate;
        private boolean rsa2;

        private String outTradeNo;
        private String totalAmount;
        private String body;
        private String sellerId;
        private String sign;

        private String timestamp;
        private String notifyUrl;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public boolean isRsa2() {
            return rsa2;
        }

        public void setRsa2(boolean rsa2) {
            this.rsa2 = rsa2;
        }

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getNotifyUrl() {
            return notifyUrl;
        }

        public void setNotifyUrl(String notifyUrl) {
            this.notifyUrl = notifyUrl;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getRsa2Private() {
            return rsa2Private;
        }

        public void setRsa2Private(String rsa2Private) {
            this.rsa2Private = rsa2Private;
        }

        public String getRsaPrivate() {
            return rsaPrivate;
        }

        public void setRsaPrivate(String rsaPrivate) {
            this.rsaPrivate = rsaPrivate;
        }
    }

    /**
     * get the sdk version. 获取SDK版本号
     */
    public void getSDKVersion() {
        PayTask payTask = new PayTask((Activity) mContext);
        String version = payTask.getVersion();
        Toast.makeText(mContext, version, Toast.LENGTH_SHORT).show();
    }


}
