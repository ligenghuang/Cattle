package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.AgreementDto;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.AgreementView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
  *
  * @ClassName:     用户协议
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/9 16:21
  * @Version:        1.0
 */

public class AgreementAction extends BaseAction<AgreementView> {
    public AgreementAction(RxAppCompatActivity _rxAppCompatActivity,AgreementView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取用户协议
     */
    public void getAgreement(){
        post(WebUrlUtil.POST_USER_CONSULT,false,service -> manager.runHttp(
                service.GetData( WebUrlUtil.POST_USER_CONSULT)));
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
                    case WebUrlUtil.POST_USER_CONSULT:
//                        //todo 获取用户协议
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            AgreementDto generalDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<AgreementDto>() {
                            }.getType());
                            if (generalDto.getStatus() == 200){
                                //todo 获取用户协议成功
                                view.getAgreementSuccess(generalDto.getData().getConsult());
                                return;
                            }
                            view.onError(generalDto.getMsg(),action.getErrorType());
                            return;
                        }
                        view.onError(msg,action.getErrorType());
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
