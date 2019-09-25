package com.zhifeng.cattle.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.HomeOtherAction;
import com.zhifeng.cattle.adapters.BannerHome;
import com.zhifeng.cattle.adapters.HomeClassifyAdapter;
import com.zhifeng.cattle.adapters.HomeOtherGoodsAdapter;
import com.zhifeng.cattle.adapters.HomeOtherSelfnavAdapter;
import com.zhifeng.cattle.modules.BannersBean;
import com.zhifeng.cattle.modules.Catenav2Bean;
import com.zhifeng.cattle.modules.FoodDrinkDto;
import com.zhifeng.cattle.modules.HomeImportDto;
import com.zhifeng.cattle.modules.HomeOtherSelfnavBean;
import com.zhifeng.cattle.ui.impl.HomeOtherView;
import com.zhifeng.cattle.utils.base.UserBaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * @ClassName: 首页进口货物和食品酒水
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/21 11:01
 * @Version: 1.0
 */

public class HomeOtherFragment extends UserBaseFragment<HomeOtherAction> implements HomeOtherView {

    View view;
    int type;
    @BindView(R.id.banner_recomment)
    BGABanner bannerRecomment;
    @BindView(R.id.rv_classify)
    RecyclerView rvClassify;
    @BindView(R.id.rv_selfnav)
    RecyclerView rvSelfnav;
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_other_selfnav)
    ImageView ivOtherSelfnav;
    @BindView(R.id.tv_goods_title)
    TextView tvGoodsTitle;


    /**
     * 轮播图所需参数
     */
    BannerHome banner;

    List<String> imgs = new ArrayList<>();
    List<String> tips = new ArrayList<>();
    List<String> url = new ArrayList<>();
    HomeClassifyAdapter homeClassifyAdapter;//分类列表
    HomeOtherSelfnavAdapter homeOtherSelfnavAdapter;//广告列表
    HomeOtherGoodsAdapter homeOtherGoodsAdapter;//商品列表

    @Override
    protected HomeOtherAction initAction() {
        return new HomeOtherAction((RxAppCompatActivity) getActivity(), this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getContext();
        mActivity = activity;
    }

    public HomeOtherFragment(int type) {
        this.type = type;
    }

    @Override
    protected void initialize() {
        init();
        loadView();
    }

    @Override
    protected void init() {
        super.init();
        refreshLayout.setEnableLoadMore(false);//禁止上拉加载更多

        tvGoodsTitle.setText(ResUtil.getString(type == 1?R.string.home_tab_10:R.string.home_tab_11));

        //轮播图
        banner = new BannerHome();
        bannerRecomment.setAdapter(banner);

        homeClassifyAdapter = new HomeClassifyAdapter(mContext);
        rvClassify.setLayoutManager(new GridLayoutManager(mContext, 4));
        rvClassify.setAdapter(homeClassifyAdapter);

        homeOtherSelfnavAdapter = new HomeOtherSelfnavAdapter(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rvSelfnav.setLayoutManager(linearLayoutManager);
        rvSelfnav.setAdapter(homeOtherSelfnavAdapter);

        homeOtherGoodsAdapter = new HomeOtherGoodsAdapter(mContext);
        rvGoods.setLayoutManager(new GridLayoutManager(mContext,2));
        rvGoods.setAdapter(homeOtherGoodsAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_other, container, false);
        ButterKnife.bind(this, view);
        binding();
        return view;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
           getData();
        }
    }

    @Override
    protected void loadView() {
        super.loadView();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });

        homeClassifyAdapter.setClickListener(new HomeClassifyAdapter.ClickListener() {
            @Override
            public void onClick(int id,String name) {
                //todo 跳转页面
                Intent i = new Intent(mContext, ListPageActivity.class);
                i.putExtra("cat_id", id);
                i.putExtra("name", name);
                startActivity(i);
            }
        });
        bannerRecomment.setDelegate(new BGABanner.Delegate() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, Object model, int position) {
                L.e("lghl", url.get(position));
                bannerRecomment.stopAutoPlay();
                jumpAdvertisingActivity(url.get(position));
            }
        });
    }

    /**
     * 跳转至广告详情页
     * @param url
     */
    private void jumpAdvertisingActivity(String url){
        Intent intent = new Intent(mContext,AdvertisingActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }

    /**
     * 获取数据
     */
    private void getData() {
        switch (type){
            case 2:
                //进口货物
                getImport();
                break;
            case 1:
                //食品酒水
                getFoodDrink();
                break;
        }
    }


    /**
     * 获取进口货物数据
     */
    @Override
    public void getImport() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getImport();
        } else {
            refreshLayout.finishRefresh();
        }
    }

    /**
     * 获取进口货物数据成功
     *
     * @param homeImportDto
     */
    @Override
    public void getImportSuccess(HomeImportDto homeImportDto) {
        refreshLayout.finishRefresh();
        HomeImportDto.DataBean dataBean = homeImportDto.getData();
        setCatenavList(dataBean.getCatenav2());
        setBanner(dataBean.getBanners());//轮播图
        homeClassifyAdapter.refresh(dataBean.getCatenav2());//二级分类列表
        setOtherSelfnav(dataBean.getSelfnav());//设置广告列表
        homeOtherGoodsAdapter.refresh(dataBean.getJinkou());//商品列表
    }

    /**
     * 设置二级分类数据
     * @param catenav2
     */
    private void setCatenavList(List<Catenav2Bean> catenav2) {
        List<Catenav2Bean> list = new ArrayList<>();
        Catenav2Bean catenav2Bean = new Catenav2Bean();
        list.add(catenav2Bean);
        list.addAll(catenav2);
        HomeFragment.fragment.setCatenavList(list);
    }

    /**
     * 获取食品酒水
     */
    @Override
    public void getFoodDrink() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getFoodDrink();
        } else {
            refreshLayout.finishRefresh();
        }
    }

    /**
     * 获取食品酒水成功
     *
     * @param foodDrinkDto
     */
    @Override
    public void getFoodDrinkSuccess(FoodDrinkDto foodDrinkDto) {
        refreshLayout.finishRefresh();
        FoodDrinkDto.DataBean dataBean = foodDrinkDto.getData();
        setBanner(dataBean.getBanners());//轮播图
        homeClassifyAdapter.refresh(dataBean.getCatenav2());//二级分类列表
        setOtherSelfnav(dataBean.getSelfnav());//设置广告列表
        L.e("lgh_other","other = "+dataBean.getFood_drink().size());
        homeOtherGoodsAdapter.refresh(dataBean.getFood_drink());//商品列表
    }


    @Override
    public void onError(String message, int code) {
        refreshLayout.finishRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        baseAction.toRegister();
    }

    @Override
    public void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }

    private void setBanner(List<BannersBean> banners) {
        //设置轮播图
        if (banners.size() != 0) {
            bannerRecomment.setVisibility(View.VISIBLE);
            imgs = new ArrayList<>();
            tips = new ArrayList<>();
            url = new ArrayList<>();
            for (int i = 0; i < banners.size(); i++) {
                BannersBean bannersBean = banners.get(i);
                imgs.add(bannersBean.getPicture());
                tips.add("");
                url.add(bannersBean.getUrl());
            }
            bannerRecomment.setAutoPlayAble(true);
            bannerRecomment.setData(imgs, tips);
            bannerRecomment.startAutoPlay();
        }
    }

    /**
     * 设置广告列表
     * @param selfnav
     */
    private void setOtherSelfnav(List<HomeOtherSelfnavBean> selfnav) {
        GlideUtil.setImage(mContext,selfnav.get(0).getImage(),ivOtherSelfnav,R.drawable.icon_selfnav_banner);
        List<HomeOtherSelfnavBean> list = new ArrayList<>();
        for (int i = 1; i <selfnav.size() ; i++) {
            list.add(selfnav.get(i));
        }
        homeOtherSelfnavAdapter.refresh(list);
    }

}
