package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.data.ResUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.modules.OrderListDto;
import com.zhifeng.cattle.modules.PayOrderDto;
import com.zhifeng.cattle.modules.post.SubmitOrderPost;
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
 * @ClassName: 我的订单
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/11 15:00
 * @Version: 1.0
 */
public class OrderAction extends BaseAction<OrderView> {
    public OrderAction(RxAppCompatActivity _rxAppCompatActivity, OrderView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取订单列表
     *
     * @param type
     */
    public void getOrderList(int type) {
        String Type = getType(type);
        post(WebUrlUtil.POST_ORDER_LIST, false, service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()), "type", Type), WebUrlUtil.POST_ORDER_LIST)
        ));
    }

    /**
     * 类型 all：全部订单，dfk：待付款，dfh:代发货，dsh：待收货，dpj：待评价，tk：退款，yqx：已取消
     *
     * @param type
     * @return
     */
    private String getType(int type) {
        String Type = "all";
        switch (type) {
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
     * 修改订单状态
     *
     * @param id
     * @param status 1：取消订单（未付款情况下）3：确认收货（待收货情况下）4 or 5：删除订单（订单收货或者订单取消后操作）
     */
    int status;

    public void editOrderStatus(int id, int status) {
        this.status = status;
        post(WebUrlUtil.POST_EDIT_ORDER_STATUS, false, service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()), "status", status, "order_id", id),
                        WebUrlUtil.POST_EDIT_ORDER_STATUS)
        ));
    }

    /**
     * 支付
     * @param submitOrderPost
     */
    public void submitOrder(SubmitOrderPost submitOrderPost){
        post(WebUrlUtil.POST_PAY_ORDER,false,service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token",MySp.getAccessToken(MyApp.getContext()),
                        "order_id",submitOrderPost.getCart_id(),"pay_type",submitOrderPost.getPay_type()
                        ,"pwd",submitOrderPost.getPwd()),WebUrlUtil.POST_PAY_ORDER)
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
//                        //todo 获取订单列表
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            OrderListDto orderListDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<OrderListDto>() {
                            }.getType());
                            if (orderListDto.getStatus() == 200) {
                                //todo 获取订单列表成功
                                view.getOrderListSuccess(orderListDto);
                                return;
                            }
                            view.onError(orderListDto.getMsg(), action.getErrorType());
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_EDIT_ORDER_STATUS:
                        //todo 修改订单状态
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            GeneralDto generalDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            if (generalDto.getStatus() == 1 || generalDto.getStatus() == 200) {
                                //todo 修改订单状态成功
                                String text = "";
                                switch (status) {
                                    case 1:
                                        text = ResUtil.getString(R.string.order_tab_29);
                                        break;
                                    case 3:
                                        text = ResUtil.getString(R.string.order_tab_30);
                                        break;
                                    default:
                                        text = ResUtil.getString(R.string.order_tab_31);
                                        break;
                                }
                                view.editOrderStatusSuccess(text,status);
                                return;
                            }
                            view.onError(generalDto.getMsg(), action.getErrorType());
                            return;
                        }
                        view.onError(msg, action.getErrorType());
                        break;
                    case WebUrlUtil.POST_PAY_ORDER:
                        //todo 订单支付
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            try{
                                PayOrderDto payOrderDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<PayOrderDto>() {
                                }.getType());
                                if (payOrderDto.getStatus() == 200) {
                                    //todo 订单支付成功
                                    view.submitOrderSuccess(payOrderDto);
                                    return;
                                }
                                view.onError(payOrderDto.getMsg(), action.getErrorType());
                                return;
                            }catch (JsonSyntaxException e){
                                GeneralDto generalDto =  new Gson().fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                                }.getType());
                                view.onError(generalDto.getMsg(), action.getErrorType());
                            }
                        }
                        view.onError(msg, action.getErrorType());
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
