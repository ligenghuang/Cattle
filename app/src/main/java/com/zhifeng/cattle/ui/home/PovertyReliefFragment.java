package com.zhifeng.cattle.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.PovertyReliefAction;
import com.zhifeng.cattle.adapters.BannerHome;
import com.zhifeng.cattle.adapters.HomeClassifyAdapter;
import com.zhifeng.cattle.adapters.HomeSpreeAdapter;
import com.zhifeng.cattle.adapters.PovertyReliefLikeAdapter;
import com.zhifeng.cattle.modules.Catenav2Bean;
import com.zhifeng.cattle.modules.PovertyReliefDto;
import com.zhifeng.cattle.ui.impl.PovertyReliefView;
import com.zhifeng.cattle.utils.base.UserBaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * @ClassName: 兴农扶贫
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/19 11:17
 * @Version: 1.0
 */

public class PovertyReliefFragment extends UserBaseFragment<PovertyReliefAction> implements PovertyReliefView {
    View view;

    @BindView(R.id.banner_recomment)
    BGABanner bannerRecomment;
    @BindView(R.id.rv_classify)
    RecyclerView rvClassify;
    //广告图片
    @BindView(R.id.iv_selfnav_1)
    ImageView ivSelfnav1;
    @BindView(R.id.iv_selfnav_2)
    ImageView ivSelfnav2;
    @BindView(R.id.iv_selfnav_3)
    ImageView ivSelfnav3;
    @BindView(R.id.iv_selfnav_4)
    ImageView ivSelfnav4;

    @BindView(R.id.rv_spree)
    RecyclerView rvSpree;
    @BindView(R.id.rv_like)
    RecyclerView rvLike;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    /**
     * 轮播图所需参数
     */
    BannerHome banner;

    List<String> imgs = new ArrayList<>();
    List<String> tips = new ArrayList<>();
    List<String> url = new ArrayList<>();

    HomeClassifyAdapter homeClassifyAdapter;//分类列表
    HomeSpreeAdapter recommendSpreeAdapter;//优选单品列表
    PovertyReliefLikeAdapter povertyReliefLikeAdapter;//猜你喜欢商品列表

    @Override
    protected PovertyReliefAction initAction() {
        return new PovertyReliefAction((RxAppCompatActivity) getActivity(), this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getContext();
        mActivity = activity;
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

        //轮播图
        banner = new BannerHome();
        bannerRecomment.setAdapter(banner);

        homeClassifyAdapter = new HomeClassifyAdapter(mContext);
        rvClassify.setLayoutManager(new GridLayoutManager(mContext, 5));
        rvClassify.setAdapter(homeClassifyAdapter);

        recommendSpreeAdapter = new HomeSpreeAdapter(mContext);
        rvSpree.setLayoutManager(new LinearLayoutManager(mContext));
        rvSpree.setAdapter(recommendSpreeAdapter);

        povertyReliefLikeAdapter = new PovertyReliefLikeAdapter(mContext);
        rvLike.setLayoutManager(new GridLayoutManager(mContext, 2));
        rvLike.setAdapter(povertyReliefLikeAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_poverty_relief, container, false);
        ButterKnife.bind(this, view);
        binding();
        return view;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
            getPovertyRelief();
        }
    }

    @Override
    protected void loadView() {
        super.loadView();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getPovertyRelief();
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
    }

    /**
     * 获取首页兴农扶贫数据
     */
    @Override
    public void getPovertyRelief() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getPovertyRelief();
        } else {
            refreshLayout.finishRefresh();
        }
    }

    /**
     * 获取首页兴农扶贫数据成功
     *
     * @param povertyReliefDto
     */
    @Override
    public void getPovertyReliefSuccess(PovertyReliefDto povertyReliefDto) {
        refreshLayout.finishRefresh();
        PovertyReliefDto.DataBean dataBean = povertyReliefDto.getData();
        setCatenavList(dataBean.getCatenav2());
        setBanner(dataBean.getBanners());//轮播图
        homeClassifyAdapter.refresh(dataBean.getCatenav2());//二级分类列表
        setSelfnav(dataBean.getSelfnav());//设置广告图
        recommendSpreeAdapter.refresh(dataBean.getYouxuan_goods());//优选单品
        povertyReliefLikeAdapter.refresh(dataBean.getLike());//猜你喜欢
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
     * 失败
     *
     * @param message
     * @param code
     */
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

    /**
     * 设置轮播图
     *
     * @param banners
     */
    private void setBanner(List<PovertyReliefDto.DataBean.BannersBean> banners) {
        //设置轮播图
        if (banners.size() != 0) {
            bannerRecomment.setVisibility(View.VISIBLE);
            imgs = new ArrayList<>();
            tips = new ArrayList<>();
            url = new ArrayList<>();
            for (int i = 0; i < banners.size(); i++) {
                PovertyReliefDto.DataBean.BannersBean bannersBean = banners.get(i);
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
     * 设置广告图
     *
     * @param selfnav
     */
    private void setSelfnav(List<PovertyReliefDto.DataBean.SelfnavBean> selfnav) {
        List<ImageView> imgs = new ArrayList<>();
        imgs.add(ivSelfnav1);
        imgs.add(ivSelfnav2);
        imgs.add(ivSelfnav3);
        imgs.add(ivSelfnav4);
        int size = selfnav.size() >= 4 ? 4 : selfnav.size();
        L.e("lgh_selfnav","selfnav  = "+selfnav.size());

        for (int i = 0; i < size; i++) {
            imgs.get(i).setVisibility(View.VISIBLE);
            GlideUtil.setImage(mContext, selfnav.get(i).getImage(), imgs.get(i), R.drawable.icon_selfnav_banner);
            L.e("lgh_selfnav","selfnav_img  = "+selfnav.get(i).getImage());
        }
    }

}
