package com.zhifeng.cattle.utils.pay.wechatpay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.lgh.huanglib.util.L;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.zhifeng.cattle.utils.config.MyApp;

/**
 * <pre>
 *     author : lgh
 *     e-mail : 1045105946@qq.com
 *     time   : 2017/12/26 18:57
 *     desc   :   支付
 *     version: 1.0
 * </pre>
 */
public class PayUtil {


    public static final String ACTION_PAY_RESPONSE = "action_wx_pay_response";
    public static final String EXTRA_RESULT = "result";

    private final Context context;
    private OnResponseListener listener;
    private ResponseReceiver receiver;

    public PayUtil(Context context) {
        this.context = context;
    }

    public PayUtil register() {
        // 微信支付
        L.e("lgh-wechat","register ");
        receiver = new ResponseReceiver();
        IntentFilter filter = new IntentFilter(ACTION_PAY_RESPONSE);
        context.registerReceiver(receiver, filter);
        return this;
    }

    public void unregister() {
        try {
            if (receiver != null) {
                L.e("lgh-wechat","unregister ");
                context.unregisterReceiver(receiver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 微信支付
     *
     * @param partnerId 商户号
     * @param appId
     * @param nonceStr
     * @param timestamp
     * @param prepayId
     * @param sign
     */
    public PayUtil pay(String partnerId, String appId, String nonceStr,
                       String timestamp, String prepayId, String sign) {
        PayReq req = new PayReq();
        req.appId = appId;
        req.nonceStr = nonceStr;
        req.packageValue = "Sign=WXPay";
        req.partnerId = partnerId;
        req.prepayId = prepayId;
        req.timeStamp = timestamp;
        req.sign = sign;
        MyApp.getWxApi().sendReq(req);
        return this;
    }


    public void setListener(OnResponseListener listener) {
        this.listener = listener;
    }

    private class ResponseReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("lgh-wechat","Response  ");
            Response response = intent.getParcelableExtra(EXTRA_RESULT);
            Log.e("lgh-wechat","Response  = "+ response.toString());
            Log.e("lgh-wechat","type: " + response.getType());
            Log.e("lgh-wechat","errCode: " + response.errCode);
            Log.e("lgh-wechat","errCode: " + response.type);
            Log.e("lgh-wechat","errCode: " + response.respType);
            String result;
            if (listener != null) {
                if (response.errCode == BaseResp.ErrCode.ERR_OK) {

                    listener.onSuccess();
                } else if (response.errCode == BaseResp.ErrCode.ERR_USER_CANCEL) {

                    listener.onCancel();
                } else {
                    switch (response.errCode) {
                        case BaseResp.ErrCode.ERR_AUTH_DENIED:
                            result = "发送被拒绝";
                            break;
                        case BaseResp.ErrCode.ERR_UNSUPPORT:
                            result = "不支持错误";
                            break;
                        default:
                            result = "调起微信支付失败";
                            break;
                    }

                    listener.onFail(result);
                }
            }
        }
    }

    public static class Response extends BaseResp implements Parcelable {

        public int errCode;
        public String errStr;
        public String transaction;
        public String openId;
        public String code;
        private String respType;

        private int type;
        private boolean checkResult;


        public Response(BaseResp baseResp, String _code) {

            errCode = baseResp.errCode;
            errStr = baseResp.errStr;
            transaction = baseResp.transaction;
            openId = baseResp.openId;
            type = baseResp.getType();
            code = _code;
            checkResult = baseResp.checkArgs();
        }

        public Response(BaseResp baseResp, String _code, String _respType) {

            errCode = baseResp.errCode;
            errStr = baseResp.errStr;
            transaction = baseResp.transaction;
            openId = baseResp.openId;
            type = baseResp.getType();
            respType = _respType;
            code = _code;
            checkResult = baseResp.checkArgs();
        }

        @Override
        public int getType() {
            return type;
        }

        @Override
        public boolean checkArgs() {
            return checkResult;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.errCode);
            dest.writeString(this.errStr);
            dest.writeString(this.transaction);
            dest.writeString(this.openId);
            dest.writeString(this.code);
            dest.writeString(this.respType);
            dest.writeInt(this.type);
            dest.writeByte(this.checkResult ? (byte) 1 : (byte) 0);
        }

        protected Response(Parcel in) {
            this.errCode = in.readInt();
            this.errStr = in.readString();
            this.transaction = in.readString();
            this.openId = in.readString();
            this.code = in.readString();
            this.respType = in.readString();
            this.type = in.readInt();
            this.checkResult = in.readByte() != 0;
        }

        public static final Creator<Response> CREATOR = new Creator<Response>() {
            @Override
            public Response createFromParcel(Parcel source) {
                return new Response(source);
            }

            @Override
            public Response[] newArray(int size) {
                return new Response[size];
            }
        };
    }

    public interface OnResponseListener {
        void onSuccess();

        void onCancel();

        void onFail(String message);
    }
}
