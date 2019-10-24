package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.BindMobileDto;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.BindMobileView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
  *
  * @ClassName:     微信登录 绑定手机
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/10/24 10:37
  * @Version:        1.0
 */

public class BindMobileAction extends BaseAction<BindMobileView> {
    public BindMobileAction(RxAppCompatActivity _rxAppCompatActivity,BindMobileView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取验证码
     * @param phone
     */
    public void getCode(String phone){
        post(WebUrlUtil.POST_SEND_CODE,false, service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("phone",phone), WebUrlUtil.POST_SEND_CODE)));
    }

    /**
     * 绑定手机
     * @param verify_code
     * @param phone
     */
    public void bindPhone(String token,String verify_code,String phone){
        post(WebUrlUtil.POST_BIND_MOBILE,false,service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", token,"verify_code",verify_code,
                        "phone",phone),WebUrlUtil.POST_BIND_MOBILE)
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
                    case WebUrlUtil.POST_SEND_CODE:
//                        //todo 获取验证码
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            GeneralDto generalDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            if (generalDto.getStatus() == 200){
                                //todo 获取验证码成功
                                view.getCodeSuccess(generalDto.getData());
                                return;
                            }
                            view.onError(generalDto.getMsg(),action.getErrorType());
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_BIND_MOBILE:
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                           try{
                               BindMobileDto bindMobileDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<BindMobileDto>() {
                               }.getType());
                               if (bindMobileDto.getStatus() == 200){
                                   //todo 绑定手机成功
                                   view.bindMobileSuccess(bindMobileDto);
                                   return;
                               }
                               view.onError(bindMobileDto.getMsg(),bindMobileDto.getStatus());
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
