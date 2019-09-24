package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.InviteCodeView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.WeakHashMap;

import io.reactivex.Observable;

/**
  *
  * @ClassName:     填写邀请码
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/24 10:07
  * @Version:        1.0
 */

public class InviteCodeAction extends BaseAction<InviteCodeView> {
    public InviteCodeAction(RxAppCompatActivity _rxAppCompatActivity,InviteCodeView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 填写邀请码
     * @param token
     * @param code
     */
    public void inviteCode(String token,String code){
        post(WebUrlUtil.POST_INVITATION_CODE,false,service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token",token,"invitation_code",code),WebUrlUtil.POST_INVITATION_CODE)
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
                case WebUrlUtil.POST_INVITATION_CODE:
                    //todo 填写邀请码
                    if (aBoolean) {
                        L.e("xx", "输出返回结果 " + action.getUserData().toString());
                        GeneralDto generalDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                        }.getType());
                        if (generalDto.getStatus() == 1||generalDto.getStatus() == 200) {
                            //todo 填写邀请码成功
                            view.inviteCodeSuccess(generalDto.getMsg());
                            return;
                        }
                        view.onError(generalDto.getMsg(), action.getErrorType());
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
