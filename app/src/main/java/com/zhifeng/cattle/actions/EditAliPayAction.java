package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.AliPayDto;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.EditAliPayView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
  *
  * @ClassName:     填写支付宝账号
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/17 17:16
  * @Version:        1.0
 */

public class EditAliPayAction extends BaseAction<EditAliPayView> {

    public EditAliPayAction(RxAppCompatActivity _rxAppCompatActivity,EditAliPayView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    public void editAlipay(String alipay_name,String alipay){
        post(WebUrlUtil.POST_EDIT_ALIPAY,false,service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()),"alipay_name",alipay_name
                ,"alipay",alipay),WebUrlUtil.POST_EDIT_ALIPAY)
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
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return (integer == 200);
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                // 输出返回结果
                L.e("xx", "输出返回结果 " + aBoolean);

                switch (action.getIdentifying()) {
                    case WebUrlUtil.POST_EDIT_ALIPAY:
                        //todo 填写支付宝账号
                        if (aBoolean) {
                            AliPayDto generalDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<AliPayDto>() {
                            }.getType());
                            if (generalDto.getStatus() == 200) {
                                //todo 填写支付宝账号成功
                                view.editAlipaySuccess(generalDto);
                                return;
                            }
                            view.onError(generalDto.getMsg(), action.getErrorType());
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                }

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
