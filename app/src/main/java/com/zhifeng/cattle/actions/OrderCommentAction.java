package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.modules.OrderComment;
import com.zhifeng.cattle.modules.OrderCommentResult;
import com.zhifeng.cattle.modules.post.OrderCommentPost;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.OrderCommentView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class OrderCommentAction extends BaseAction<OrderCommentView> {
    public OrderCommentAction(RxAppCompatActivity _rxAppCompatActivity, OrderCommentView orderCommentView) {
        super(_rxAppCompatActivity);
        attachView(orderCommentView);
    }

    public void postComment(OrderComment orderComment) {
        String comments = new Gson().toJson(orderComment,OrderComment.class);
        L.e("lgh_json","comments  = "+comments);
        OrderCommentPost commentPost = new OrderCommentPost(MySp.getAccessToken(MyApp.getContext()),"["+comments+"]");
        post(WebUrlUtil.POST_ORDER_COMMENT, false,
                service -> manager.runHttp(service.PostData(
                        RequestBody.create(MediaType.parse("application/json; charset=utf-8"),commentPost.toString()), WebUrlUtil.POST_ORDER_COMMENT)));
    }

    private String getComments(String comments){
        return comments.replaceAll("\\\\","");
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
                case WebUrlUtil.POST_ORDER_COMMENT:
                    //todo 提交商品评价
                    if (aBoolean) {
                        L.e("xx", "输出返回结果 " + action.getUserData().toString());
                      try{
                          OrderCommentResult orderCommentResult = new Gson().fromJson(action.getUserData().toString(), new TypeToken<OrderCommentResult>() {
                          }.getType());
                          if (orderCommentResult.getStatus() == 200){
                              //todo 提交商品评价 成功
                              view.postCommentSuccess(orderCommentResult);
                              return;
                          }
                          view.onError(orderCommentResult.getMsg(),action.getErrorType());
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
