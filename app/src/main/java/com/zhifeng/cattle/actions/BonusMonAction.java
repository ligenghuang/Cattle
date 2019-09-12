package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.BonusDayDto;
import com.zhifeng.cattle.modules.BonusMonDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.BonusDayView;
import com.zhifeng.cattle.ui.impl.BonusMonView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
  *
  * @ClassName:     当月累计奖金
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/12 10:43
  * @Version:        1.0
 */

public class BonusMonAction extends BaseAction<BonusMonView> {
    public BonusMonAction(RxAppCompatActivity _rxAppCompatActivity, BonusMonView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取当月累计奖金
     */
    public void getBonusMon(){
        post(WebUrlUtil.POST_MON_BONUS,false,service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext())),WebUrlUtil.POST_MON_BONUS)
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
                    case WebUrlUtil.POST_MON_BONUS:
//                        //todo 获取当月累计奖金
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            BonusMonDto bonusMonDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<BonusMonDto>() {
                            }.getType());
                            if (bonusMonDto.getStatus() == 200){
                                //todo 获取当月累计奖金成功
                                view.getBonusMonSuccess(bonusMonDto);
                                return;
                            }
                            view.onError(bonusMonDto.getMsg(),action.getErrorType());
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
