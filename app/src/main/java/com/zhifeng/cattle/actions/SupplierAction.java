package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.Supplier;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.SupplierView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;

/**
 * @ClassName:
 * @Description: 申请供应商
 * @Author: Administrator
 * @Date: 2019/9/21 11:05
 */
public class SupplierAction extends BaseAction<SupplierView> {
    public SupplierAction(RxAppCompatActivity _rxAppCompatActivity, SupplierView supplierView) {
        super(_rxAppCompatActivity);
        attachView(supplierView);
    }

    public void postSupplier(String name, String mobile) {
        post(WebUrlUtil.POST_SUPPLIER, false, service -> manager.runHttp(service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()), "name", name, "moblie", mobile), WebUrlUtil.POST_SUPPLIER)));
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
                case WebUrlUtil.POST_SUPPLIER:
                    //todo 提交申请供应商
                    if (aBoolean) {
                        L.e("xx", "输出返回结果 " + action.getUserData().toString());
                        Supplier supplier = new Gson().fromJson(action.getUserData().toString(), new TypeToken<Supplier>() {
                        }.getType());
                        if (supplier.getStatus() == 200) {
                            //todo 提交申请供应商成功
                            view.postSupplierSuccess(supplier);
                            return;
                        }
                        view.onError(supplier.getMsg(), action.getErrorType());
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
