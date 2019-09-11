package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.OrderDetailDto;
import com.zhifeng.cattle.modules.OrderListDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.OrderDetailView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
  *
  * @ClassName:     订单详情
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/11 17:00
  * @Version:        1.0
 */

public class OrderDetailAction extends BaseAction<OrderDetailView> {
    public OrderDetailAction(RxAppCompatActivity _rxAppCompatActivity,OrderDetailView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取订单详情
     * @param id
     */
    public void getOrderDetail(int id){
        post(WebUrlUtil.POST_ORDER_DETAIL,false,service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()),"order_id",id),WebUrlUtil.POST_ORDER_DETAIL)
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
                    case WebUrlUtil.POST_ORDER_DETAIL:
//                        //todo 获取订单详情
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            OrderDetailDto orderDetailDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<OrderDetailDto>() {
                            }.getType());
                            if (orderDetailDto.getStatus() == 200){
                                //todo 获取订单详情成功
                                view.getOrderDetailSuccess(orderDetailDto);
                                return;
                            }
                            view.onError(orderDetailDto.getMsg(),action.getErrorType());
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
