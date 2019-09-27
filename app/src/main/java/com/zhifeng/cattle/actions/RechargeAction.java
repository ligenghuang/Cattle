package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.BankBto;
import com.zhifeng.cattle.modules.BankImgListDto;
import com.zhifeng.cattle.modules.BankListDto;
import com.zhifeng.cattle.modules.GeneralDto;
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
     * 充值
     * @param num
     * @param pwd
     */
    public void recharge(double num,String pwd){
        post(WebUrlUtil.POST_RECHARGE,false,service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()),"num",num
                ,"pwd",pwd),WebUrlUtil.POST_RECHARGE)
        ));
    }

    /**
     * 获取已绑定银行卡列表
     */
    public void getBankList(){
        post(WebUrlUtil.POST_GET_BANKLIST,false,service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext())),WebUrlUtil.POST_GET_BANKLIST)
        ));
    }

    public void getBankImgList() {
        post(WebUrlUtil.POST_BOUND_BANK,false,service -> manager.runHttp(
                service.PostData(WebUrlUtil.POST_BOUND_BANK)
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
                case WebUrlUtil.POST_GET_BANKLIST:
                    //todo 获取已绑定银行卡列表
                    if (aBoolean) {
                        L.e("xx", "输出返回结果 " + action.getUserData().toString());
                        BankListDto bankListDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<BankListDto>() {
                        }.getType());
                        if (bankListDto.getStatus() == 200){
                            //todo 获取已绑定银行卡列表 成功
                            view.getBankListSuccess(bankListDto);
                            return;
                        }
                        view.onError(bankListDto.getMsg(),action.getErrorType());
                        return;
                    }
                    view.onError(msg,action.getErrorType());
                    break;
                case WebUrlUtil.POST_BOUND_BANK:
                    //todo 获取银行图标列表
                    if (aBoolean) {
                        L.e("xx", "输出返回结果 " + action.getUserData().toString());
                        BankImgListDto bankImgListDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<BankImgListDto>() {
                        }.getType());
                        if (bankImgListDto.getStatus() == 200){
                            //todo 获取银行图标列表 成功
                            view.getBankImgListSuccess(bankImgListDto);
                            return;
                        }
                        view.onError(bankImgListDto.getMsg(),action.getErrorType());
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
