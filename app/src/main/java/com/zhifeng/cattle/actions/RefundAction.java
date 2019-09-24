package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.OrderDetailDto;
import com.zhifeng.cattle.modules.RefundDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.RefundView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;

public class RefundAction extends BaseAction<RefundView> {
    public RefundAction(RxAppCompatActivity _rxAppCompatActivity, RefundView refundView) {
        super(_rxAppCompatActivity);
        attachView(refundView);
    }

    public void getGoodsDetail(String order_id) {
        post(WebUrlUtil.POST_ORDER_DETAIL, false, service -> manager.runHttp(service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()), "order_id", order_id), WebUrlUtil.POST_ORDER_DETAIL)));
    }

    public void applyRefund(String order_id, int refund_type, String cancel_remark) {
        post(WebUrlUtil.POST_APPLY_REFUND, false, service -> manager.runHttp(service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()), "order_id", order_id, "refund_type", refund_type, "cancel_remark", cancel_remark), WebUrlUtil.POST_APPLY_REFUND)));
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
                case WebUrlUtil.POST_ORDER_DETAIL:
                    //todo 获取订单详情
                    if (aBoolean) {
                        L.e("xx", "输出返回结果 " + action.getUserData().toString());
                        OrderDetailDto orderDetailDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<OrderDetailDto>() {
                        }.getType());
                        if (orderDetailDto.getStatus() == 200) {
                            //todo 获取订单详情 成功
                            view.getOrderDetailSuccess(orderDetailDto);
                            return;
                        }
                        view.onError(orderDetailDto.getMsg(), action.getErrorType());
                        return;
                    }
                    view.onError(msg, action.getErrorType());
                    break;
                case WebUrlUtil.POST_APPLY_REFUND:
                    //todo 提交申请退款
                    if (aBoolean) {
                        L.e("xx", "输出返回结果 " + action.getUserData().toString());
                        RefundDto refundDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<RefundDto>() {
                        }.getType());
                        if (refundDto.getStatus() == 200) {
                            //todo 提交申请退款 成功
                            view.applyRefundSuccess(refundDto);
                            return;
                        }
                        view.onError(refundDto.getMsg(), action.getErrorType());
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
