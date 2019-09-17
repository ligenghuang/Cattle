package com.zhifeng.cattle.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BaseAction;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

public class GoodsDetailActivity extends UserBaseActivity {

    @BindView(R.id.banner_main)
    BGABanner bannerMain;
//    @BindView(R.id.top_view)
//    View topView;
    @BindView(R.id.f_right_iv)
    ImageView fRightIv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;

    @Override
    public int intiLayout() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected BaseAction initAction() {
        return null;
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initTitlebar() {
        super.initTitlebar();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mImmersionBar
//                .statusBarView(R.id.top_view)
//                .keyboardEnable(true)
//                .statusBarDarkFont(true, 0.2f)
//                .addTag("GoodsDetailActivity")  //给上面参数打标记，以后可以通过标记恢复
//                .navigationBarWithKitkatEnable(false)
//                .init();
        toolbar.setNavigationOnClickListener(view -> finish());

    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

    }
}
