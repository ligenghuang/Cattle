package com.zhifeng.cattle.wxapi;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.lgh.huanglib.util.L;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhifeng.cattle.actions.BaseAction;
import com.zhifeng.cattle.modules.WXBaseRespEntity;
import com.zhifeng.cattle.utils.LoginUtil;
import com.zhifeng.cattle.utils.base.UserBaseActivity;
import com.zhifeng.cattle.utils.config.Content;

/**
 * description:微信
 * autour: lgh
 * date: 2018/11/6 11:47
 * update: 2018/11/6
 * version:
 */

public class WXEntryActivity extends UserBaseActivity implements IWXAPIEventHandler {

    /**
     * 微信登录相关
     */
    private IWXAPI api;


    @Override
    public int intiLayout() {
        return 0;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通过WXAPIFactory工厂获取IWXApI的示例
        api = WXAPIFactory.createWXAPI(this, Content.APP_ID, true);
        //将应用的appid注册到微信
        api.registerApp(Content.APP_ID);
        L.e("lgh", "onCreate");

        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            boolean result = api.handleIntent(getIntent(), this);
            if (!result) {

                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        L.e("lshs", "onActivityResult");
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
//        L.e("baseReq:" + baseReq.toString());
//        L.e("lshs", "onReq");
    }

    @Override
    public void onResp(BaseResp baseResp) {
        L.e("lgh", "onResp");
//        L.d("baseResp:--A" + JSON.toJSONString(baseResp));
//        L.e("baseResp--B:" + baseResp.errStr + "," + baseResp.openId + "," + baseResp.transaction + "," + baseResp.errCode + " getType " + baseResp.getType());
        WXBaseRespEntity entity = JSON.parseObject(JSON.toJSONString(baseResp), WXBaseRespEntity.class);
        String result = "";
        Intent intent = null;
//        L.e("baseResp:--C   " + baseResp.getType() + " getCode " + entity.getCode());

        switch (baseResp.getType()) {
            case ConstantsAPI.COMMAND_SENDAUTH://登录
                L.e("lgh", "登录.......");
                intent = new Intent(LoginUtil.ACTION_SHARE_RESPONSE);
                intent.putExtra(LoginUtil.EXTRA_RESULT, new LoginUtil.Response(baseResp, entity.getCode()));
                intent.putExtra("sendType",1);
                sendBroadcast(intent);
                finish();
                break;

//            case ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX://分享
//                L.e("xx", "分享.......");
//                intent = new Intent(LoginUtil.ACTION_SHARE_RESPONSE);
//                intent.putExtra(LoginUtil.EXTRA_RESULT, new LoginUtil.Response(baseResp, entity.getCode()));
//                intent.putExtra("sendType",0);
//                sendBroadcast(intent);
//                finish();
//                break;
        }

    }


    @Override
    protected BaseAction initAction() {
        return null;
    }

    @Override
    public void finish() {
        super.finish();
        hideInput();
    }
}
