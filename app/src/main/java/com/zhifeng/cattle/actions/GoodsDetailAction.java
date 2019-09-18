package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.modules.GoodsDetailDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.GoodsDetailView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
  *
  * @ClassName:     商品详情
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/18 12:15
  * @Version:        1.0
 */

public class GoodsDetailAction  extends BaseAction<GoodsDetailView> {
    public GoodsDetailAction(RxAppCompatActivity _rxAppCompatActivity,GoodsDetailView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 商品详情
     */
    public void getGoodsDetail(int goods_id){
        post(WebUrlUtil.POST_GOODS_DETAILS,false,service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()),"goods_id",goods_id),WebUrlUtil.POST_GOODS_DETAILS)
        ));

    }

    /**
     * 删除关注商品
     * @param goods_id
     */
    public void deleteOrAddCollection(String goods_id){
        post(WebUrlUtil.POST_DELETE_COLLECTION,false,service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()),"goods_id",goods_id),WebUrlUtil.POST_DELETE_COLLECTION)
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
                    case WebUrlUtil.POST_GOODS_DETAILS:
                        //todo 获取商品详情
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            GoodsDetailDto goodsDetailDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<GoodsDetailDto>() {
                            }.getType());
                            if (goodsDetailDto.getStatus() == 200){
                                //todo 获取商品详情
                                view.getGoodsDetailSuccess(goodsDetailDto);
                                return;
                            }
                            view.onError(goodsDetailDto.getMsg(),action.getErrorType());
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_DELETE_COLLECTION:
                        //todo 取消关注或关注
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            GeneralDto generalDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            if (generalDto.getStatus() == 200){
                                //todo 取消关注或关注成功
                                view.deleteOrAddCollection(generalDto.getMsg());
                                return;
                            }
                            view.onError(generalDto.getMsg(),action.getErrorType());
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
