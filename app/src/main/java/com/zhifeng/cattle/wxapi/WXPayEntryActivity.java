package com.zhifeng.cattle.wxapi;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.lgh.huanglib.util.L;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhifeng.cattle.modules.WXBaseRespEntity;
import com.zhifeng.cattle.utils.pay.wechatpay.Constants;
import com.zhifeng.cattle.utils.pay.wechatpay.PayUtil;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private IWXAPI api;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通过WXAPIFactory工厂获取IWXApI的示例
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, true);
        //将应用的appid注册到微信
        api.registerApp(Constants.APP_ID);
        L.e("------------WXPayEntryActivity------------------------");
        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            boolean result = api.handleIntent(getIntent(), this);
            if (!result) {
                L.e("参数不合法，未被SDK处理，退出");
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        L.e("onActivityResult.......");
        api.handleIntent(data, this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }

    @Override
    public void onReq(BaseReq baseReq) {
        L.e("baseReq:" + baseReq.toString());
    }

    @Override
    public void onResp(BaseResp baseResp) {
        L.e("baseResp:--A" + JSON.toJSONString(baseResp));
        L.e("baseResp--B:" + baseResp.errStr + "," + baseResp.openId + "," + baseResp.transaction + "," + baseResp.errCode + " getType " + baseResp.getType());
        WXBaseRespEntity entity = JSON.parseObject(JSON.toJSONString(baseResp), WXBaseRespEntity.class);
        String result = "";
        Intent intent = null;
        L.e("baseResp:--C   " + baseResp.getType() + " getCode " + entity.getCode());
        switch (baseResp.getType()) {
            case ConstantsAPI.COMMAND_PAY_BY_WX://微信支付
                L.e("xx", "微信支付.......");
                intent = new Intent(PayUtil.ACTION_PAY_RESPONSE);
                intent.putExtra(PayUtil.EXTRA_RESULT, new PayUtil.Response(baseResp, entity.getCode()));
                sendBroadcast(intent);
                finish();
                break;
        }

    }

}