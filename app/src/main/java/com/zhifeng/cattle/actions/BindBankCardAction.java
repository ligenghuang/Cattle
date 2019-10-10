package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.BindBankCardDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.BindBankCardView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
/**
 * @ClassName:
 * @Description: 绑定银行卡
 * @Author: Administrator
 * @Date: 2019/9/27 15:02
 */
public class BindBankCardAction extends BaseAction<BindBankCardView> {
    public BindBankCardAction(RxAppCompatActivity _rxAppCompatActivity, BindBankCardView bindBankCardView) {
        super(_rxAppCompatActivity);
        attachView(bindBankCardView);
    }

    public void bindBankCard(String bankName, long bankcard) {
        post(WebUrlUtil.POST_BIND_BANK, false, service -> manager.runHttp(service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()), "bankname", bankName, "bankcard", bankcard), WebUrlUtil.POST_BIND_BANK)));
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
                        case WebUrlUtil.POST_BIND_BANK:
                            //todo 绑定银行卡
                            if (aBoolean) {
                                BindBankCardDto bindBankCardDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<BindBankCardDto>() {
                                }.getType());
                                if (bindBankCardDto.getStatus() == 200) {
                                    //todo 绑定银行卡 成功
                                    view.bindBankCardSuccess(bindBankCardDto);
                                    return;
                                }
                                view.onError(bindBankCardDto.getMsg(), action.getErrorType());
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
