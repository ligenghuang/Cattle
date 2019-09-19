package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.GoodsComment;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.GoodsCommentsView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;

public class GoodsCommentsAction extends BaseAction<GoodsCommentsView> {
    public GoodsCommentsAction(RxAppCompatActivity _rxAppCompatActivity, GoodsCommentsView commentsView) {
        super(_rxAppCompatActivity);
        attachView(commentsView);
    }

    public void getComments(String goods_id, int pageSize) {
        post(WebUrlUtil.POST_COMMENT_LIST, false, service -> manager.runHttp(service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()), "goods_id", goods_id, "pageSize", pageSize), WebUrlUtil.POST_COMMENT_LIST)));
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
                case WebUrlUtil.POST_COMMENT_LIST:
                    //todo 获取商品评价
                    if (aBoolean) {
                        L.e("xx", "输出返回结果 " + action.getUserData().toString());
                        GoodsComment goodsComment = new Gson().fromJson(action.getUserData().toString(), new TypeToken<GoodsComment>() {
                        }.getType());
                        if (goodsComment.getStatus() == 1) {
                            //todo 获取商品评价成功
                            view.getGoodsCommentsSuccess(goodsComment);
                            return;
                        }
                        view.onError(goodsComment.getMsg(), action.getErrorType());
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
