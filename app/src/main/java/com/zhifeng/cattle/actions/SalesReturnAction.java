package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.actions.BaseAction;
import com.zhifeng.cattle.modules.OrderListDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.SalesReturnView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
  *
  * @ClassName:     退货
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/11 18:12
  * @Version:        1.0
 */

public class SalesReturnAction extends BaseAction<SalesReturnView> {
    public SalesReturnAction(RxAppCompatActivity _rxAppCompatActivity,SalesReturnView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取订单列表
     */
    public void getOrderList(){
        post(WebUrlUtil.POST_ORDER_LIST,false,service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()),"type","tk"),WebUrlUtil.POST_ORDER_LIST)
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
                    case WebUrlUtil.POST_ORDER_LIST:
//                        //todo 获取退货列表
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            OrderListDto orderListDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<OrderListDto>() {
                            }.getType());
                            if (orderListDto.getStatus() == 200){
                                //todo 获取订单列表成功
                                view.getSalesReturnListSucces(orderListDto);
                                return;
                            }
                            view.onError(orderListDto.getMsg(),action.getErrorType());
                            return;
                        }
                        view.onError(msg,action.getErrorType());
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
