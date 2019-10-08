package com.zhifeng.cattle.ui.my;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.base.MyFragmentPagerAdapter;
import com.lgh.huanglib.util.cusview.CustomViewPager;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BaseAction;
import com.zhifeng.cattle.ui.classify.ClassifyFragment;
import com.zhifeng.cattle.ui.home.HomeFragment;
import com.zhifeng.cattle.ui.login.LoginActivity;
import com.zhifeng.cattle.utils.base.UserBaseActivity;
import com.zhifeng.cattle.utils.data.MySp;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;

/**
 * @ClassName:
 * @Description: 我的-账单明细
 * @Author: Administrator
 * @Date: 2019/9/23 17:09
 */
public class CheckDetailActivity extends UserBaseActivity {
    public static int Position = 0;
    private static final int POIONTONE = 0;
    private static final int POIONTTWO = 1;
    private ArrayList<Fragment> fragments;
    private MyFragmentPagerAdapter fragmentPagerAdapter;
    private int fragmentSize = 2;

    CheckDetailFragment incomeFragment;
    CheckDetailFragment outcomeFragment;

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvIncome)
    TextView tvIncome;
    @BindView(R.id.tvOutCome)
    TextView tvOutCome;
    @BindView(R.id.vp)
    CustomViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_check_detail;
    }

    @Override
    protected BaseAction initAction() {
        return null;
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
        initViewPager();

    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initTitlebar() {
        super.initTitlebar();
        mImmersionBar
                .statusBarView(R.id.top_view)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0.2f)
                .addTag("CheckDetailActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.balance_tab_7));
    }

    private void initViewPager() {
        fragments = new ArrayList<Fragment>();
        for (int i = 0; i < fragmentSize; i++) {
            switch (i) {
                case POIONTONE://
                    incomeFragment = new CheckDetailFragment(POIONTONE);
                    if (Position != POIONTONE) {
                        incomeFragment.setUserVisibleHint(false);//
                    }

                    fragments.add(incomeFragment);
                    break;
                case POIONTTWO://
                    outcomeFragment = new CheckDetailFragment(POIONTTWO);
                    if (Position != POIONTTWO) {
                        outcomeFragment.setUserVisibleHint(false);//
                    }
                    fragments.add(outcomeFragment);
                    break;  default:
                    break;
            }
        }

        fragmentPagerAdapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager(), fragments);

        fragmentPagerAdapter.setFragments(fragments);
        setSelectedLin(Position);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                vp.setAdapter(fragmentPagerAdapter);
                vp.setCurrentItem(Position, false);
                vp.setOffscreenPageLimit(fragmentSize);


            }
        }, 500);
    }

    @OnTouch({R.id.tvIncome, R.id.tvOutCome})
    public boolean onTouch(View v) {
        switch (v.getId()) {
            case R.id.tvIncome:
                Position = POIONTONE;
                break;
            case R.id.tvOutCome:
                Position = POIONTTWO;
                break;
            default:
                break;
        }
        setSelectedLin(Position);
        vp.setCurrentItem(Position, false);
        return false;
    }
    /**
     * 选择
     *
     * @param position
     */
    public void setSelectedLin(int position) {
        tvIncome.setSelected(false);
        tvOutCome.setSelected(false);

        //设置状态栏黑色字体与图标
//        QMUIStatusBarHelper.setStatusBarLightMode(this);
        mImmersionBar.statusBarDarkFont(true);
        switch (position) {
            case 0:
                tvIncome.setSelected(true);
                break;
            case 1:
                tvOutCome.setSelected(true);
                break;
            default:
                break;
        }
    }
}
