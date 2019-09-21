package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.PovertyReliefDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.PovertyReliefView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;

/**
  *
  * @ClassName:     兴农扶贫
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/21 10:17
  * @Version:        1.0
 */

public class PovertyReliefAction extends BaseAction<PovertyReliefView> {
    public PovertyReliefAction(RxAppCompatActivity _rxAppCompatActivity,PovertyReliefView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取兴农扶贫数据
     */
    public void getPovertyRelief(){
        post(WebUrlUtil.POST_POVEERYRELIEF,false,service -> manager.runHttp(
                service.GetData(WebUrlUtil.POST_POVEERYRELIEF)
        ));
    }

    /**
     * sticky:表明优先接收最高级  threadMode = ThreadMode.MAIN：表明在主线程
     *
     * @param action
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void MessageEvent(final Action action) {
        L.e("xx", "action   接收到数据更新....." + action.getIdentifying() + " action.getErrorType() : " + action.getErrorType());
        final String msg = action.getMsg(action);
        Observable.just(action.getErrorType())
                .all(integer -> (integer == 200)).subscribe(aBoolean -> {
            // 输出返回结果
            L.e("xx", "输出返回结果 " + aBoolean);
            switch (action.getIdentifying()) {
                case WebUrlUtil.POST_POVEERYRELIEF:
                    //todo 获取兴农扶贫数据
                    if (aBoolean) {
                        L.e("xx", "输出返回结果 " + action.getUserData().toString());
                        PovertyReliefDto povertyReliefDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<PovertyReliefDto>() {
                        }.getType());
                        if (povertyReliefDto.getStatus() == 200) {
                            //todo 获取兴农扶贫数据 成功
                            view.getPovertyReliefSuccess(povertyReliefDto);
                            return;
                        }
                        view.onError(povertyReliefDto.getMsg(), action.getErrorType());
                        return;
                    }
                    view.onError(msg, action.getErrorType());
                    break;

            }
        });
    }

    public void toRegister() {
        register(this);
    }

    public void toUnregister() {
        unregister(this);
    }
}
