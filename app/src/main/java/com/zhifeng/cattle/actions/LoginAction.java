package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.modules.LoginDto;
import com.zhifeng.cattle.modules.WxLoginDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.LoginView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
  *
  * @ClassName:     登录注册页
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/9 14:40
  * @Version:        1.0
 */

public class LoginAction extends BaseAction<LoginView> {
    public LoginAction(RxAppCompatActivity _rxAppCompatActivity,LoginView view) {
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
     * 登录或注册
     * @param phone
     * @param verify_code
     */
    public void loginOrRegistered(String phone,String verify_code){
        post(WebUrlUtil.POST_LOGIN,false, service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("phone",phone,"verify_code",verify_code), WebUrlUtil.POST_LOGIN)));
    }

    /**
     * 微信登录
     * @param openId
     * @param nickname
     * @param headimgurl
     */
    public void wxLogin(String openId,String nickname,String headimgurl){
        post(WebUrlUtil.POST_WXLOGIN,false, service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("openid",openId,"nickname",nickname,"headimgurl",headimgurl), WebUrlUtil.POST_WXLOGIN)));
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
                    case WebUrlUtil.POST_LOGIN:
//                        //todo 登录或注册
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                           try{
                               LoginDto loginDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<LoginDto>() {
                               }.getType());
                               if (loginDto.getStatus() == 200){
                                   //todo 登录或注册成功
                                   view.loginOrRegisteredSuccess(loginDto);
                                   return;
                               }
                               view.onError(loginDto.getMsg(),action.getErrorType());
                               return;
                           }catch (JsonSyntaxException e){
                               GeneralDto generalDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                               }.getType());
                               view.onError(generalDto.getMsg(),action.getErrorType());
                               return;
                           }
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_WXLOGIN:
                        //todo 微信登录
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            WxLoginDto wxLoginDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<WxLoginDto>() {
                            }.getType());
                            if (wxLoginDto.getStatus() == 200){
                                //todo 微信登录
                                view.wxLoginSuccess(wxLoginDto);
                                return;
                            }
                            view.onError(wxLoginDto.getMsg(),wxLoginDto.getStatus());
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
