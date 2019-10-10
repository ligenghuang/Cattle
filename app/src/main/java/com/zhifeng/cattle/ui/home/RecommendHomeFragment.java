package com.zhifeng.cattle.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.RecommendHomeAction;
import com.zhifeng.cattle.adapters.BannerHome;
import com.zhifeng.cattle.adapters.HomeClassifyAdapter;
import com.zhifeng.cattle.adapters.RecommenSelfnavAdapter;
import com.zhifeng.cattle.adapters.HomeSpreeAdapter;
import com.zhifeng.cattle.modules.Catenav2Bean;
import com.zhifeng.cattle.modules.RecommendHomeDto;
import com.zhifeng.cattle.ui.impl.RecommendHomeView;
import com.zhifeng.cattle.utils.base.UserBaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * @ClassName: 首页推荐
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/18 17:12
 * @Version: 1.0
 */

public class RecommendHomeFragment extends UserBaseFragment<RecommendHomeAction> implements RecommendHomeView {
    View view;
    @BindView(R.id.banner_recomment)
    BGABanner bannerRecomment;
    @BindView(R.id.rv_classify)
    RecyclerView rvClassify;
    @BindView(R.id.banner_selfnav)
    BGABanner bannerSelfnav;
    @BindView(R.id.rv_selfnav)
    RecyclerView rvSelfnav;
    @BindView(R.id.rv_spree)
    RecyclerView rvSpree;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    /**
     * 轮播图所需参数
     */
    BannerHome banner;
    BannerHome beSelfnav;

    List<String> imgs = new ArrayList<>();
    List<String> tips = new ArrayList<>();
    List<String> url = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    List<String> imgsSelfnav = new ArrayList<>();
    List<String> tipsSelfnav = new ArrayList<>();
    List<String> urlSelfnav = new ArrayList<>();
    List<String> titleSelfnav = new ArrayList<>();

    HomeClassifyAdapter homeClassifyAdapter;//分类列表
    RecommenSelfnavAdapter recommenSelfnavAdapter;//商品列表
    HomeSpreeAdapter recommendSpreeAdapter;//优选单品列表

    @Override
    protected RecommendHomeAction initAction() {
        return new RecommendHomeAction((RxAppCompatActivity) getActivity(), this);
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

        beSelfnav = new BannerHome();
        bannerSelfnav.setAdapter(beSelfnav);

        homeClassifyAdapter = new HomeClassifyAdapter(mContext);
        rvClassify.setLayoutManager(new GridLayoutManager(mContext,4));
        rvClassify.setAdapter(homeClassifyAdapter);

        recommenSelfnavAdapter = new RecommenSelfnavAdapter(mContext);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rvSelfnav.setLayoutManager(linearLayoutManager);
        rvSelfnav.setAdapter(recommenSelfnavAdapter);

        recommendSpreeAdapter = new HomeSpreeAdapter(mContext);
        rvSpree.setLayoutManager(new LinearLayoutManager(mContext));
        rvSpree.setAdapter(recommendSpreeAdapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_recommend, container, false);
        ButterKnife.bind(this, view);
        binding();
        return view;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
            baseAction.toRegister();
            getRecommendHome();
        }
    }

    @Override
    protected void loadView() {
        super.loadView();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getRecommendHome();
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
                jumpAdvertisingActivity(url.get(position),titles.get(position));
            }
        });
        bannerSelfnav.setDelegate(new BGABanner.Delegate() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, Object model, int position) {
                L.e("lghl", urlSelfnav.get(position));
                bannerSelfnav.stopAutoPlay();
                jumpAdvertisingActivity(urlSelfnav.get(position),titleSelfnav.get(position));
            }
        });
    }

    /**
     * 跳转至广告详情页
     * @param url
     */
    private void jumpAdvertisingActivity(String url,String title){
        Intent intent = new Intent(mContext,AdvertisingActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        startActivity(intent);
    }

    /**
     * 获取首页推荐
     */
    @Override
    public void getRecommendHome() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getRecommendHome();
        }else {
            refreshLayout.finishRefresh();
        }
    }

    /**
     * 获取首页推荐成功
     * @param recommendHomeDto
     */
    @Override
    public void getRecommendHomeSuccess(RecommendHomeDto recommendHomeDto) {
        refreshLayout.finishRefresh();
        RecommendHomeDto.DataBean dataBean = recommendHomeDto.getData();
        setTitle(dataBean.getCatenav1());
        setCatenavList(dataBean.getCatenav2());
        setBanner(dataBean.getBanners());//轮播图
        homeClassifyAdapter.refresh(dataBean.getCatenav2());//二级分类列表
        setBannerSelfnav(dataBean.getSelfnav());//轮播图
        recommenSelfnavAdapter.refresh(dataBean.getBuy_now());//商品列表
        recommendSpreeAdapter.refresh(dataBean.getYouxuan_goods());//优选单品
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
//        HomeFragment.fragment.setCatenavList(list);
    }

    /**
     * 失败
     * @param message
     * @param code
     */
    @Override
    public void onError(String message, int code) {
        refreshLayout.finishRefresh();
    }

    @Override
    public void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }

    @Override
    public void onResume() {
        super.onResume();
        baseAction.toRegister();
    }

    /**
     * 设置轮播图
     * @param banners
     */
    private void setBanner(List<RecommendHomeDto.DataBean.BannersBean> banners) {
        //设置轮播图
        if (banners.size() != 0) {
            bannerRecomment.setVisibility(View.VISIBLE);
            imgs = new ArrayList<>();
            tips = new ArrayList<>();
            url = new ArrayList<>();
            titles = new ArrayList<>();
            for (int i = 0; i < banners.size(); i++) {
                RecommendHomeDto.DataBean.BannersBean bannersBean = banners.get(i);
                imgs.add(bannersBean.getPicture());
                tips.add("");
                url.add(bannersBean.getUrl());
                titles.add(bannersBean.getTitle());
            }
            bannerRecomment.setAutoPlayAble(true);
            bannerRecomment.setData(imgs, tips);
            bannerRecomment.startAutoPlay();
        }
    }

    /**
     * 设置广告轮播图
     * @param selfnav
     */
    private void setBannerSelfnav(List<RecommendHomeDto.DataBean.SelfnavBean> selfnav) {
        //设置轮播图
        if (selfnav.size() != 0) {
            bannerSelfnav.setVisibility(View.VISIBLE);
            imgsSelfnav = new ArrayList<>();
            tipsSelfnav = new ArrayList<>();
            urlSelfnav = new ArrayList<>();
            titleSelfnav = new ArrayList<>();
            for (int i = 0; i < selfnav.size(); i++) {
                RecommendHomeDto.DataBean.SelfnavBean bannersBean = selfnav.get(i);
                imgsSelfnav.add(bannersBean.getImage());
                tipsSelfnav.add("");
                urlSelfnav.add(bannersBean.getUrl());
                titleSelfnav.add(bannersBean.getTitle());
            }
            bannerSelfnav.setAutoPlayAble(true);
            bannerSelfnav.setData(imgsSelfnav, tipsSelfnav);
            bannerSelfnav.startAutoPlay();
        }
    }

    private void setTitle(List<RecommendHomeDto.DataBean.Catenav1Bean> catenav1) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < catenav1.size(); i++) {
            list.add(catenav1.get(i).getCat_name());
        }
        HomeFragment.fragment.setTitle(list);
        List<RecommendHomeDto.DataBean.Catenav1Bean> catenav1BeanList = new ArrayList<>();
        RecommendHomeDto.DataBean.Catenav1Bean catenav1Bean = new RecommendHomeDto.DataBean.Catenav1Bean();
        catenav1BeanList.add(catenav1Bean);
        catenav1BeanList.addAll(catenav1);
        HomeFragment.fragment.setCatenavList(catenav1BeanList);
    }

}
