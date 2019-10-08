package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.CheckDetail;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.CheckDetailView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;

public class CheckDetailAction extends BaseAction<CheckDetailView> {
    public CheckDetailAction(RxAppCompatActivity _rxAppCompatActivity, CheckDetailView checkDetailView) {
        super(_rxAppCompatActivity);
        attachView(checkDetailView);
    }

    public void getCheckDetail(int log_type, int pageSize, int page) {
        post(WebUrlUtil.POST_CHECK_DETAIL, false, service -> manager.runHttp(service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()), "log_type", log_type==0?1:0, "pageSize", pageSize, "page", page), WebUrlUtil.POST_CHECK_DETAIL)));
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
                case WebUrlUtil.POST_CHECK_DETAIL:
                    //todo 获取账单明细
                    if (aBoolean) {
                        L.e("xx", "输出返回结果 " + action.getUserData().toString());
                        CheckDetail checkDetail = new Gson().fromJson(action.getUserData().toString(), new TypeToken<CheckDetail>() {
                        }.getType());
                        if (checkDetail.getStatus() == 200) {
                            //todo 获取账单明细 成功
                            view.getCheckDetailSuccess(checkDetail);
                            return;
                        }
                        view.onError(checkDetail.getMsg(), action.getErrorType());
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
