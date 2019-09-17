package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.BalanceDto;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.WithdrawalView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
  *
  * @ClassName:     提现
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/17 16:32
  * @Version:        1.0
 */

public class WithdrawalAction extends BaseAction<WithdrawalView> {
    public WithdrawalAction(RxAppCompatActivity _rxAppCompatActivity,WithdrawalView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }


    /**
     * 获取余额
     */
    public void getBalance() {
        post(WebUrlUtil.POST_REMAINDER, false, service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext())), WebUrlUtil.POST_REMAINDER)
        ));
    }

    /**
     * 提现
     * @param money
     */
    public  void withdrawal(double money){
        post(WebUrlUtil.POST_WITHDRAWAL, false, service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()),"money",money), WebUrlUtil.POST_WITHDRAWAL)
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
                    case WebUrlUtil.POST_REMAINDER:
                        //todo 获取提现余额
                        if (aBoolean) {
                            BalanceDto balanceDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<BalanceDto>() {
                            }.getType());
                            if (balanceDto.getStatus() == 200) {
                                //todo 获取提现余额成功
                                view.getBalanceSuccess(balanceDto);
                                return;
                            }
                            view.onError(balanceDto.getMsg(), action.getErrorType());
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_WITHDRAWAL:
                        //todo 提现
                        if (aBoolean) {
                            GeneralDto generalDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            if (generalDto.getStatus() == 200) {
                                //todo 提现成功
                                view.withdrawalSuccess(generalDto);
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
