package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.FoodDrinkDto;
import com.zhifeng.cattle.modules.HomeImportDto;
import com.zhifeng.cattle.modules.PovertyReliefDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.HomeOtherView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;

/**
  *
  * @ClassName:     首页进口货物和食品酒水
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/21 11:20
  * @Version:        1.0
 */

public class HomeOtherAction extends BaseAction<HomeOtherView> {
    public HomeOtherAction(RxAppCompatActivity _rxAppCompatActivity,HomeOtherView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取进口货物数据
     */
    public void getImport(){
        post(WebUrlUtil.POST_IMPORT,false, service -> manager.runHttp(
                service.GetData(WebUrlUtil.POST_IMPORT)
        ));
    }

    /**
     * 获取食品酒水数据
     */
    public void getFoodDrink(){
        post(WebUrlUtil.POST_FOODDRINK,false, service -> manager.runHttp(
                service.GetData(WebUrlUtil.POST_FOODDRINK)
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
                case WebUrlUtil.POST_IMPORT:
                    //todo 获取进口货物数据
                    if (aBoolean) {
                        L.e("xx", "输出返回结果 " + action.getUserData().toString());
                        HomeImportDto homeImportDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<HomeImportDto>() {
                        }.getType());
                        if (homeImportDto.getStatus() == 200) {
                            //todo 获取进口货物数据 成功
                            view.getImportSuccess(homeImportDto);
                            return;
                        }
                        view.onError(homeImportDto.getMsg(), action.getErrorType());
                        return;
                    }
                    view.onError(msg, action.getErrorType());
                    break;
                case WebUrlUtil.POST_FOODDRINK:
                    //todo 获取食品酒水数据
                    if (aBoolean) {
                        L.e("xx", "输出返回结果 " + action.getUserData().toString());
                        FoodDrinkDto foodDrinkDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<FoodDrinkDto>() {
                        }.getType());
                        if (foodDrinkDto.getStatus() == 200) {
                            //todo 获取食品酒水数据 成功
                            view.getFoodDrinkSuccess(foodDrinkDto);
                            return;
                        }
                        view.onError(foodDrinkDto.getMsg(), action.getErrorType());
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
