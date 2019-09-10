package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.CategoryListDto;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.ClassifyView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
  *
  * @ClassName:     分类
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/10 10:14
  * @Version:        1.0
 */

public class ClassifyAction extends BaseAction<ClassifyView> {
    public ClassifyAction(RxAppCompatActivity _rxAppCompatActivity,ClassifyView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取商品分类列表
     */
    public void getCategoryList(){
        post(WebUrlUtil.POST_CATEGORY_LIST,false, service -> manager.runHttp(
                service.GetData( WebUrlUtil.POST_CATEGORY_LIST)));
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
                    case WebUrlUtil.POST_CATEGORY_LIST:
//                        //todo 获取分类列表
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            CategoryListDto categoryListDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<CategoryListDto>() {
                            }.getType());
                            if (categoryListDto.getStatus() == 200){
                                //todo 获取分类列表成功
                                view.getCategoryListSuccess(categoryListDto);
                                return;
                            }
                            view.onError(categoryListDto.getMsg(),action.getErrorType());
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
