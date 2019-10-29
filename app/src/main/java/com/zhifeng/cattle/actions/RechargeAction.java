package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.BankBto;
import com.zhifeng.cattle.modules.BankImgListDto;
import com.zhifeng.cattle.modules.BankListDto;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.modules.RateDto;
import com.zhifeng.cattle.modules.RechargeTypeDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.RechargeView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;

/**
 * @ClassName: 充值
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/20 10:59
 * @Version: 1.0
 */

public class RechargeAction extends BaseAction<RechargeView> {
    public RechargeAction(RxAppCompatActivity _rxAppCompatActivity, RechargeView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取汇率
     */
    public void getRate(){
        post(WebUrlUtil.POST_EXCHANGE_RATE,false,service -> manager.runHttp(service.GetData(WebUrlUtil.POST_EXCHANGE_RATE)));
    }

    /**
     * 获取付款码
     */
    public void getRechargeType(){
        post(WebUrlUtil.POST_RACHARGE_TYPE,false,service -> manager.runHttp(service.GetData(WebUrlUtil.POST_RACHARGE_TYPE)));
    }


    /**
     * 充值
     * @param num
     * @param img
     */
    public void recharge(double num,int recharge_type,String img){
        post(WebUrlUtil.POST_RECHARGE,false,service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()),"money",num,"recharge_type",recharge_type
                ,"img",img),WebUrlUtil.POST_RECHARGE)
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
                case WebUrlUtil.POST_EXCHANGE_RATE:
                    //todo 获取汇率
                    if (aBoolean) {
                        try{
                            RateDto rateDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<RateDto>() {
                            }.getType());
                            //todo 获取汇率成功
                            if (rateDto.getStatus() == 200){
                                view.getRateSuccess(rateDto);
                                return;
                            }
                            view.onError(rateDto.getMsg(),rateDto.getStatus());
                            return;
                        }catch (JsonSyntaxException e){
                            GeneralDto generalDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.onError(generalDto.getMsg(),generalDto.getStatus());
                            return;
                        }
                    }
                    view.onError(msg,action.getErrorType());
                    break;
                case WebUrlUtil.POST_RACHARGE_TYPE:
                    //todo 获取付款码
                    if (aBoolean) {
                        try{
                            RechargeTypeDto rechargeTypeDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<RechargeTypeDto>() {
                            }.getType());
                            //todo 获取付款码成功
                            if (rechargeTypeDto.getStatus() == 200){
                                view.getRechargeTypeSuccess(rechargeTypeDto);
                                return;
                            }
                            view.onError(rechargeTypeDto.getMsg(),rechargeTypeDto.getStatus());
                            return;
                        }catch (JsonSyntaxException e){
                            GeneralDto generalDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            view.onError(generalDto.getMsg(),generalDto.getStatus());
                            return;
                        }
                    }
                    view.onError(msg,action.getErrorType());
                    break;
                case WebUrlUtil.POST_RECHARGE:
                    //todo 充值
                    if (aBoolean) {
                        L.e("xx", "输出返回结果 " + action.getUserData().toString());
                        GeneralDto generalDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                        }.getType());
                        if (generalDto.getStatus() == 200){
                            //todo 充值成功
                            view.rechargeSuccess(generalDto);
                            return;
                        }
                        view.onError(generalDto.getMsg(),action.getErrorType());
                        return;
                    }
                    view.onError(msg,action.getErrorType());
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
