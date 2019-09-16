package com.zhifeng.cattle.actions;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.actions.Action;
import com.lgh.huanglib.net.CollectionsUtils;
import com.lgh.huanglib.util.L;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.modules.AddressDetailDto;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.modules.RegionDto;
import com.zhifeng.cattle.modules.post.AddOrEditAddressPost;
import com.zhifeng.cattle.net.WebUrlUtil;
import com.zhifeng.cattle.ui.impl.AddAddressView;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 *
 * @ClassName:     添加或修改收货地址
 * @Description:
 * @Author:         lgh
 * @CreateDate:     2019/9/16 9:56
 * @Version:        1.0
 */
public class AddAddressAction extends BaseAction<AddAddressView>{
    public AddAddressAction(RxAppCompatActivity _rxAppCompatActivity,AddAddressView view) {
        super(_rxAppCompatActivity);
        attachView(view);
    }

    /**
     * 获取收货地址详情
     * @param id
     */
    public  void getAddress(int id){
        post(WebUrlUtil.POST_ADDRESS_DETAIL,false,service -> manager.runHttp(
                service.PostData(CollectionsUtils.generateMap("token",MySp.getAccessToken(MyApp.getContext()),"address_id",id),WebUrlUtil.POST_ADDRESS_DETAIL)
        ));
    }

    /**
     * 编辑添加地址
     * @param addOrEditAddressPost
     */
    public void AddOrEditAddress(AddOrEditAddressPost addOrEditAddressPost){
        Map<Object, Object> map = new HashMap<>();
        map = CollectionsUtils.generateMap("token", MySp.getAccessToken(MyApp.getContext()),
                "consignee",addOrEditAddressPost.getConsignee(),"district",Integer.parseInt(addOrEditAddressPost.getDistrict()),"address",addOrEditAddressPost.getAddress(),
                "mobile",addOrEditAddressPost.getMobile(),"is_default",addOrEditAddressPost.getIs_default());
        if (addOrEditAddressPost.getAddress_id() != 0){
            map.put("address_id",addOrEditAddressPost.getAddress_id());
        }


        Map<Object, Object> finalMap = map;
        post(WebUrlUtil.POST_ADD_ADDRESS,false, service -> manager.runHttp(
                service.PostData(finalMap,WebUrlUtil.POST_ADD_ADDRESS)
        ));
    }

    /**
     * 获取下级地址信息
     * @param code
     */
    public void getRegion(String code){
       if (code.equals("0")){
           post(WebUrlUtil.POST_ADDRESS_GET_REGION,false,service -> manager.runHttp(
                   service.PostData(WebUrlUtil.POST_ADDRESS_GET_REGION)
           ));
       }else {
           post(WebUrlUtil.POST_ADDRESS_GET_REGION,false,service -> manager.runHttp(
                   service.GetData(WebUrlUtil.POST_ADDRESS_GET_REGION,Integer.parseInt(code))
           ));
       }
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
                    case WebUrlUtil.POST_ADD_ADDRESS:
                        //todo 编辑添加地址
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            GeneralDto generalDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<GeneralDto>() {
                            }.getType());
                            if (generalDto.getStatus() == 200||generalDto.getStatus()==1){
                                //todo 编辑添加地址
                                view.addOrEditAddressSuccess(generalDto);
                                return;
                            }
                            view.onError(generalDto.getMsg(),action.getErrorType());
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_ADDRESS_DETAIL:
                        //todo 获取地址详情
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            AddressDetailDto addressDetailDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<AddressDetailDto>() {
                            }.getType());
                            if (addressDetailDto.getStatus() == 200){
                                //todo 获取地址详情
                                view.getAddressSuccess(addressDetailDto);
                                return;
                            }
                            view.onError(addressDetailDto.getMsg(),action.getErrorType());
                            return;
                        }
                        view.onError(msg,action.getErrorType());
                        break;
                    case WebUrlUtil.POST_ADDRESS_GET_REGION:
                        //todo 获取下级地址信息
                        if (aBoolean) {
                            L.e("xx", "输出返回结果 " + action.getUserData().toString());
                            try{
                                RegionDto regionDto = new Gson().fromJson(action.getUserData().toString(), new TypeToken<RegionDto>() {
                                }.getType());
                                if (regionDto.getStatus() == 200){
                                    //todo 获取下级地址信息
                                    view.getRegionSuccess(regionDto);
                                    return;
                                }
                                view.onError(regionDto.getMsg(),action.getErrorType());
                                return;
                            }catch (JsonSyntaxException e){
                                view.getRegionError();
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
