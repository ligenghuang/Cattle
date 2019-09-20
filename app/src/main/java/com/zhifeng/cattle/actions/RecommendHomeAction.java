package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.RecommendHomeDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.RecommendHomeView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;

/**
 * @ClassName: 首页推荐
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/20 17:21
 * @Version: 1.0
 */

public class RecommendHomeAction extends BaseAction<RecommendHomeView> {
    public RecommendHomeAction(RxAppCompatActivity _rxAppCompatActivity, RecommendHomeView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    public void getRecommendHome() {
        post(WebUrlUtil.POST_RECOMMENDhOME, false, service -> manager.runHttp(
                service.GetData(WebUrlUtil.POST_RECOMMENDhOME)
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
                case WebUrlUtil.POST_RECOMMENDhOME:
                    //todo 获取首页推荐
                    if (aBoolean) {
                        L.e("xx", "输出返回结果 " + action.getUserData().toString());
                        RecommendHomeDto recommendHomeDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<RecommendHomeDto>() {
                        }.getType());
                        if (recommendHomeDto.getStatus() == 200) {
                            //todo 获取首页推荐 成功
                            view.getRecommendHomeSuccess(recommendHomeDto);
                            return;
                        }
                        view.onError(recommendHomeDto.getMsg(), action.getErrorType());
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
