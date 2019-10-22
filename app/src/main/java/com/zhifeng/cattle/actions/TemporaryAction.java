package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.AlipayOrderDto;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.modules.PayOrderDto;
import com.zhifeng.cattle.modules.ShowIdCardDto;
import com.zhifeng.cattle.modules.SubmitOrderDto;
import com.zhifeng.cattle.modules.Temporary;
import com.zhifeng.cattle.modules.post.SubmitOrderPost;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.TemporaryView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;

public class TemporaryAction extends BaseAction<TemporaryView> {
    public TemporaryAction(RxAppCompatActivity _rxAppCompatActivity, TemporaryView temporaryView) {
        super(_rxAppCompatActivity);
        attachView(temporaryView);
    }

    public void getTemporary(String cart_id) {
        post(WebUrlUtil.POST_TEMPORARY, false, service -> manager.runHttp(service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()), "cart_id", cart_id), WebUrlUtil.POST_TEMPORARY)));
    }

    /**
     * 提交订单
     *
     * @param submitOrderPost
     */
    public void submitOrder(SubmitOrderPost submitOrderPost) {
        post(WebUrlUtil.POST_SUBMITORDER, false, service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()),
                        "cart_id", submitOrderPost.getCart_id(), "address_id", submitOrderPost.getAddress_id(), "pay_type", submitOrderPost.getPay_type()
                        , "user_note", submitOrderPost.getUser_note(), "pwd", submitOrderPost.getPwd()), WebUrlUtil.POST_SUBMITORDER)
        ));
    }

    /**
     * 支付
     *
     * @param submitOrderPost
     */
    public void payOrder(SubmitOrderPost submitOrderPost) {
        post(WebUrlUtil.POST_PAY_ORDER, false, service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()),
                        "order_id", submitOrderPost.getCart_id(), "pay_type", submitOrderPost.getPay_type()
                        , "pwd", submitOrderPost.getPwd()), WebUrlUtil.POST_PAY_ORDER)
        ));
    }

    /**
     * 显示身份认证信息
     */
    public void showIdCsrd() {
        post(WebUrlUtil.POST_SHOW_IDCARD, false, service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext())), WebUrlUtil.POST_SHOW_IDCARD)
        ));
    }

    /**
     * 支付宝
     *
     * @param order_id
     */
    public void payAli(String order_id) {
        post(WebUrlUtil.POST_ALI_PAY, false, service -> manager.runHttp(
                service.PostDataAli(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()), "order_id", order_id), WebUrlUtil.POST_ALI_PAY)
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
                case WebUrlUtil.POST_TEMPORARY:
                    //todo 获取购物车提交订单
                    if (aBoolean) {
                        L.e("xx", "输出返回结果 " + action.getUserData().toString());
                        Temporary temporary = new Gson().fromJson(action.getUserData().toString(), new TypeToken<Temporary>() {
                        }.getType());
                        if (temporary.getStatus() == 200) {
                            //todo 获取购物车提交订单成功
                            view.getTemporarySuccess(temporary);
                            return;
                        }
                        view.onError(temporary.getMsg(), action.getErrorType());
                        return;
                    }
                    view.onError(msg, action.getErrorType());
                    break;
                case WebUrlUtil.POST_SUBMITORDER:
                    //todo 提交订单
                    if (aBoolean) {
                        L.e("xx", "输出返回结果 " + action.getUserData().toString());
                        SubmitOrderDto submitOrderDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<SubmitOrderDto>() {
                        }.getType());
                        if (submitOrderDto.getStatus() == 200) {
                            //todo 提交订单成功
                            view.submitOrderSuccess(submitOrderDto);
                            return;
                        }
                        view.onError(submitOrderDto.getMsg(), action.getErrorType());
                        return;
                    }
                    view.onError(msg, action.getErrorType());
                    break;
                case WebUrlUtil.POST_PAY_ORDER:
                    //todo 订单支付
                    if (aBoolean) {
                        L.e("xx", "输出返回结果 " + action.getUserData().toString());
                        try {
                            PayOrderDto payOrderDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<PayOrderDto>() {
                            }.getType());
                            if (payOrderDto.getStatus() == 200) {
                                //todo 订单支付成功
                                view.payOrderSuccess(payOrderDto);
                                return;
                            }
                            view.onError(payOrderDto.getMsg(), action.getErrorType());
                            return;
                        } catch (JsonSyntaxException e) {
                            GeneralDto generalDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.payOrderError(generalDto.getMsg());
                            return;
                        }
                    }
                    view.payOrderError(msg);
                    break;
                case WebUrlUtil.POST_SHOW_IDCARD:
                    if (aBoolean) {
                        ShowIdCardDto certificationDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<ShowIdCardDto>() {
                        }.getType());
                        if (certificationDto.getStatus() == 200) {
                            //todo 身份认证信息
                            view.showIdCardSuccess(certificationDto);
                            return;
                        }
                        view.onError(certificationDto.getMsg(), action.getErrorType());
                        return;
                    }
                    view.onError(msg, action.getErrorType());
                    break;
                case WebUrlUtil.POST_ALI_PAY:
                    //todo 支付宝 支付
                    if (aBoolean) {
                      try{
                          AlipayOrderDto alipayOrderDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<AlipayOrderDto>() {
                          }.getType());
                          view.aliPaySuccess(alipayOrderDto);
                          return;
                      }catch (JsonSyntaxException e){
                          view.aliPayErroe();
                          return;
                      }
                    }
                    view.aliPayErroe();
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
