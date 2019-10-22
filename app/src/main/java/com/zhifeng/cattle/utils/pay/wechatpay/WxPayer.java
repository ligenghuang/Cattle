package com.zhifeng.cattle.utils.pay.wechatpay;

import android.content.Context;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * <pre>
 *     author : lgh
 *     e-mail : 1045105946@qq.com
 *     time   : 2017/12/11 19:46
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class WxPayer {

    public static final String ERROR_TIPS_INIT = " WxPayer must call init method first ";

    private String mPrepayId;

    public IWXAPI mMsgApi;

    /**
     * 初始化
     * @param context
     */
    public void init(Context context) {
        mMsgApi = WXAPIFactory.createWXAPI(context, null);
        mMsgApi.registerApp(Constants.APP_ID);
    }

    /**
     * 支付
     * @param partnerId
     * @param appId
     * @param nonceStr
     * @param timestamp
     * @param prepayId
     * @param sign
     */
    public void pay(String partnerId, String appId, String nonceStr,
                    String timestamp, String prepayId, String sign) {
        if (mMsgApi == null) {
            throw new RuntimeException(ERROR_TIPS_INIT);
        }
        PayReq req = new PayReq();
        req.appId = appId;
        req.nonceStr = nonceStr;
        req.packageValue = "Sign=WXPay";
        req.partnerId = partnerId;
        req.prepayId = prepayId;
        req.timeStamp = timestamp;
        req.sign = sign;

//        ContentValues signParams = new ContentValues();
//        signParams.put("appId", req.appId);
//        signParams.put("nonceStr", req.nonceStr);
//        signParams.put("package", req.packageValue);
//        signParams.put("partnerId", req.partnerId);
//        signParams.put("prepayId", req.prepayId);
//        signParams.put("timestamp", req.timeStamp);
//        signParams.put("sign", req.sign);
//
//        Log.e("orion", signParams.toString());
//        mMsgApi.registerApp(appId);
        mMsgApi.sendReq(req);
    }

    /**
     * 是否已安装微信客户端
     *
     * @return
     */
    public boolean isWXAppInstalled() {
        if (mMsgApi == null) {
            throw new RuntimeException(ERROR_TIPS_INIT);
        }
        return mMsgApi.isWXAppInstalled();
    }

    public String getPrepayId() {
        return mPrepayId;
    }

    public void setPrepayId(String prepayId) {
        this.mPrepayId = prepayId;
    }
}
