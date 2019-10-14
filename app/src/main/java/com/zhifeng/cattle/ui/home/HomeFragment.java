package com.zhifeng.cattle.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.lgh.huanglib.util.base.MyFragmentPagerAdapter;
import com.lgh.huanglib.util.cusview.CustomViewPager;
import com.lxj.xpopup.XPopup;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BaseAction;
import com.zhifeng.cattle.modules.Catenav2Bean;
import com.zhifeng.cattle.modules.RecommendHomeDto;
import com.zhifeng.cattle.utils.base.UserBaseFragment;
import com.zhifeng.cattle.utils.popup.CustomPopup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

/**
 * @ClassName: 首页fragment
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/9 16:58
 * @Version: 1.0
 */

public class HomeFragment extends UserBaseFragment {

    public static int Position = 0;
    private static final int POIONTONE = 0;
    private static final int POIONTTWO = 1;
    private static final int POIONTTHREE = 2;
    private static final int POIONTFOUR = 3;

    static HomeFragment fragment;
    View view;
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.viewpager)
    CustomViewPager myPager;
    @BindView(R.id.tv_type_1)
     TextView tvType1;
    @BindView(R.id.tv_type_2)
     TextView tvType2;
    @BindView(R.id.tv_type_3)
     TextView tvType3;
    @BindView(R.id.tv_type_4)
     TextView tvType4;
    @BindView(R.id.ll_top)
    LinearLayout llTop;

    private ArrayList<Fragment> fragments;
    private MyFragmentPagerAdapter fragmentPagerAdapter;
    private int fragmentSize = 4;

    RecommendHomeFragment recommendHomeFragment;
    PovertyReliefFragment povertyReliefFragment;
    HomeOtherFragment importFragment;
    HomeOtherFragment foodDrinkFragment;
    List<RecommendHomeDto.DataBean.Catenav1Bean> list = new ArrayList<>();

    @Override
    protected BaseAction initAction() {
        return null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getContext();
        mActivity = activity;
        fragment = this;
    }

    @Override
    protected void initialize() {
        init();
        loadView();
    }

    @Override
    protected void init() {
        super.init();
        initViewPager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        binding();
        mImmersionBar.setStatusBarView(getActivity(), topView);
        return view;
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        fragments = new ArrayList<Fragment>();
        for (int i = 0; i < fragmentSize; i++) {
            switch (i) {
                case POIONTONE://
                    recommendHomeFragment = new RecommendHomeFragment();
                    if (Position != POIONTONE) {
                        recommendHomeFragment.setUserVisibleHint(false);//
                    }

                    fragments.add(recommendHomeFragment);
                    break;
                case POIONTTWO://
                    povertyReliefFragment = new PovertyReliefFragment();
                    if (Position != POIONTTWO) {
                        povertyReliefFragment.setUserVisibleHint(false);//
                    }
                    fragments.add(povertyReliefFragment);
                    break;
                case POIONTTHREE://
                    importFragment = new HomeOtherFragment(1);
                    if (Position != POIONTTHREE) {
                        importFragment.setUserVisibleHint(false);//
                    }
                    fragments.add(importFragment);
                    break;
                case POIONTFOUR://
                    foodDrinkFragment = new HomeOtherFragment(2);
                    if (Position != POIONTFOUR) {
                        foodDrinkFragment.setUserVisibleHint(false);
                    }
                    fragments.add(foodDrinkFragment);
                    break;
                default:
                    break;
            }
        }

        fragmentPagerAdapter = new MyFragmentPagerAdapter(
                getChildFragmentManager(), fragments);

        fragmentPagerAdapter.setFragments(fragments);
        setSelectedLin(Position);
        setListSetClick();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                myPager.setAdapter(fragmentPagerAdapter);
                myPager.setCurrentItem(Position, false);
                myPager.setOffscreenPageLimit(fragmentSize);


            }
        }, 500);
    }

    @OnTouch({R.id.tv_type_1, R.id.tv_type_2, R.id.tv_type_3
            ,R.id.tv_type_4
    })
    public boolean onTouch(View v) {
        switch (v.getId()) {
            case R.id.tv_type_1:
                Position = POIONTONE;
                break;
            case R.id.tv_type_2:
                Position = POIONTTWO;
                break;
            case R.id.tv_type_3:
                Position = POIONTTHREE;
                break;
            case R.id.tv_type_4:
                Position = POIONTFOUR;
                break;
            default:
                break;
        }

        setSelectedLin(Position);
        setListSetClick();
        myPager.setCurrentItem(Position, false);
        return false;
    }

    /**
     * 选择
     *
     * @param position
     */
    private void setSelectedLin(int position) {
        tvType1.setSelected(false);
        tvType2.setSelected(false);
        tvType3.setSelected(false);
        tvType4.setSelected(false);
        //设置状态栏黑色字体与图标
//        QMUIStatusBarHelper.setStatusBarLightMode(this);
        mImmersionBar.statusBarDarkFont(true);
        switch (position) {
            case 0:
                tvType1.setSelected(true);
                break;
            case 1:
                tvType2.setSelected(true);
                break;
            case 2:
                tvType3.setSelected(true);
                break;
            case 3:
                tvType4.setSelected(true);
                break;
            default:
                break;
        }
    }

    public void setTitle(List<String> list){
        tvType1.setText(list.get(0));
        tvType2.setText(list.get(1));
        tvType3.setText(list.get(2));
        tvType4.setText(list.get(3));
    }

    private void setListSetClick(){
        for (int i = 1; i <list.size() ; i++) {
            list.get(i).setClick(i == (Position+1));
        }
    }

    /**
     * 获取二级分类列表
     * @param list
     */
    public void setCatenavList(List<RecommendHomeDto.DataBean.Catenav1Bean> list){
        this.list = list;
    }

    @OnClick({R.id.et_classify_search,R.id.iv_home_more})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.et_classify_search:
                jumpActivityNotFinish(mContext, SearchGoodsActivity.class);
                break;
            case R.id.iv_home_more:
                new XPopup.Builder(mActivity)
                        .hasShadowBg(false)
                        .atView(llTop)
                        .asCustom(new CustomPopup(mContext, list,new CustomPopup.OnListClickListener() {
                            @Override
                            public void onClick(int position) {
                                Position = position;
                                setSelectedLin(Position);
                                myPager.setCurrentItem(Position, false);
                            }
                        }))
                        .show();
                break;
        }
    }
}
