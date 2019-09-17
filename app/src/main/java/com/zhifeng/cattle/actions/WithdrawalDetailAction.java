package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.WithdrawalListDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.WithdrawalDetailView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * @ClassName: 提现明细
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/17 14:06
 * @Version: 1.0
 */

public class WithdrawalDetailAction extends BaseAction<WithdrawalDetailView> {
    public WithdrawalDetailAction(RxAppCompatActivity _rxAppCompatActivity, WithdrawalDetailView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取提现明细
     */
    public void getWithdrawalList(int page){
        post(WebUrlUtil.POST_WITHDRAWAL_LIST,false,service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()),"page",page),WebUrlUtil.POST_WITHDRAWAL_LIST)
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
                    case WebUrlUtil.POST_WITHDRAWAL_LIST:
                        //todo 获取提现明细
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            WithdrawalListDto withdrawalListDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<WithdrawalListDto>() {
                            }.getType());
                            if (withdrawalListDto.getStatus() == 200){
                                //todo 获取提现明细 成功
                                view.getWithdrawalListSuccess(withdrawalListDto);
                                return;
                            }
                            view.onError(withdrawalListDto.getMsg(),action.getErrorType());
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
