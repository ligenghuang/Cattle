package com.zhifeng.cattle.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;
import com.lgh.huanglib.util.L;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.zhifeng.cattle.modules.RegisterThirdDto;
import com.zhifeng.cattle.modules.WXAccessTokenEntity;
import com.zhifeng.cattle.modules.WXUserInfo;
import com.zhifeng.cattle.utils.config.Content;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * description: 微信
 * autour: lgh
 * date: 2018/6/28 11:02
 * update: 2018/6/28
 * version:
 */
public class LoginUtil {


    public static final String ACTION_SHARE_RESPONSE = "action_wx_share_response";
    public static final String EXTRA_RESULT = "result";

    private final Context context;
    private OnResponseListener listener;
    private OnLoginResponseListener loginlistener;
    private ResponseReceiver receiver;

    public LoginUtil(Context context) {
        this.context = context;
    }

    public LoginUtil register() {
        // 微信
        L.e("lgh","register");
        receiver = new ResponseReceiver();
        IntentFilter filter = new IntentFilter(ACTION_SHARE_RESPONSE);
        context.registerReceiver(receiver, filter);
        return this;
    }

    public void unregister() {
        try {
            if (receiver != null) {
                L.e("lgh","unregister");
                context.unregisterReceiver(receiver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setListener(OnResponseListener listener) {
        this.listener = listener;
    }

    //
    public void setLoginListener(OnLoginResponseListener listener) {
        this.loginlistener = listener;
    }



    public class ResponseReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            int sendType = intent.getIntExtra("sendType", 0);

            L.e("lgh_weixin", "登录  到這裏了  " + sendType);
            Response response = intent.getParcelableExtra(EXTRA_RESULT);
            L.e("lgh_weixin","type: " + response.getType());
            L.e("lgh_weixin","errCode: " + response.errCode);
            L.e("lgh_weixin","errCode: " + response.type);
            L.e("lgh_weixin","errCode: " + response.respType);
            String result;
            if (listener != null || loginlistener != null) {
                if (response.errCode == BaseResp.ErrCode.ERR_OK) {

                    if (response.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
                        login(response.code);
                    } else {
                        listener.onSuccess();
                    }
                } else if (response.errCode == BaseResp.ErrCode.ERR_USER_CANCEL) {
                    if (response.getType() == ConstantsAPI.COMMAND_SENDAUTH) {

                        loginlistener.onCancel();
                    } else {

                        listener.onCancel();
                    }
                } else {
                    switch (response.errCode) {
                        case BaseResp.ErrCode.ERR_AUTH_DENIED:
                            result = "发送被拒绝";
                            break;
                        case BaseResp.ErrCode.ERR_UNSUPPORT:
                            result = "不支持错误";
                            break;
                        default:
                            result = "发送返回";
                            break;
                    }
                    if (response.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
                        loginlistener.onFail(result);

                    } else {

                        listener.onFail(result);
                    }
                }
            }
        }
    }

    /**
     * 获取 access_token
     * @param code
     */
    public void login(String code) {
        OkHttpUtils.get().url("https://api.weixin.qq.com/sns/oauth2/access_token")
                .addParams("appid", Content.APP_ID)
                .addParams("secret", Content.APP_Secret)
                .addParams("code", code)
                .addParams("grant_type", "authorization_code")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        L.d("lgh_weixin","请求错误..");
                        loginlistener.onFail("请求错误");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        L.d("lgh_weixin","response:" + response);
                        WXAccessTokenEntity accessTokenEntity = JSON.parseObject(response, WXAccessTokenEntity.class);
                        if (accessTokenEntity.getAccess_token() != null ) {
//                            loginlistener.onSuccess(accessTokenEntity);
                            getUserInfo(accessTokenEntity);
                        } else {
                            L.d("lgh_weixin","获取失败");
                            loginlistener.onFail("获取失败");
                        }
                    }
                });
    }

    /**
     * 获取个人信息
     *
     * @param accessTokenEntity
     */
    public void getUserInfo(WXAccessTokenEntity accessTokenEntity) {
        //https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
        OkHttpUtils.get()
                .url("https://api.weixin.qq.com/sns/userinfo")
                .addParams("access_token", accessTokenEntity.getAccess_token())
                .addParams("openid", accessTokenEntity.getOpenid())//openid:授权用户唯一标识
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        L.d("lgh_weixin","获取错误..");
                        loginlistener.onFail("获取失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        L.json("userInfo:", response);
                        WXUserInfo wxResponse = JSON.parseObject(response, WXUserInfo.class);

                        RegisterThirdDto dto = new RegisterThirdDto(wxResponse.getOpenid(), wxResponse.getNickname(), 2, "", "", wxResponse.getProvince(), wxResponse.getCity(), "", wxResponse.getHeadimgurl());

                        if (loginlistener != null) {

                            loginlistener.onSuccess(dto);
                        }
                    }
                });
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

    public interface OnLoginResponseListener {
        void onSuccess(RegisterThirdDto dto);

        void onCancel();

        void onFail(String message);
    }
}
