package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.OrderListDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.OrderView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
  *
  * @ClassName:     我的订单
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/11 15:00
  * @Version:        1.0
 */
public class OrderAction extends BaseAction<OrderView> {
    public OrderAction(RxAppCompatActivity _rxAppCompatActivity,OrderView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取订单列表
     * @param type
     */
    public void getOrderList(int type){
        String Type = getType(type);
        post(WebUrlUtil.POST_ORDER_LIST,false,service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()),"type",Type),WebUrlUtil.POST_ORDER_LIST)
        ));
    }

    /**
     * 类型 all：全部订单，dfk：待付款，dfh:代发货，dsh：待收货，dpj：待评价，tk：退款，yqx：已取消
     * @param type
     * @return
     */
    private String getType(int type) {
        String Type = "all";
        switch (type){
            case 0:
                Type = "all";
                break;
            case 1:
                Type = "dfk";
                break;
            case 2:
                Type = "dfh";
                break;
            case 3:
                Type = "dsh";
                break;
            case 4:
                Type = "dpj";
                break;
        }
        return Type;
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
                    case WebUrlUtil.POST_SEND_CODE:
//                        //todo 获取订单列表
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            OrderListDto orderListDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<OrderListDto>() {
                            }.getType());
                            if (orderListDto.getStatus() == 200){
                                //todo 获取订单列表成功
                                view.getOrderListSuccess(orderListDto);
                                return;
                            }
                            view.onError(orderListDto.getMsg(),action.getErrorType());
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
