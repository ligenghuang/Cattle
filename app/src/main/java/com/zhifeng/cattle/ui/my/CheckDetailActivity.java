package com.zhifeng.cattle.ui.my;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.base.MyFragmentPagerAdapter;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BaseAction;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName:
 * @Description: 我的-账单明细
 * @Author: Administrator
 * @Date: 2019/9/23 17:09
 */
public class CheckDetailActivity extends UserBaseActivity {
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
    ViewPager vp;

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
        tvIncome.setSelected(true);
        ArrayList<Fragment> fragments = new ArrayList<>();

        CheckDetailFragment incomeFragment = new CheckDetailFragment();
        incomeFragment.setLog_type(1);
        fragments.add(incomeFragment);

        CheckDetailFragment outcomeFragment = new CheckDetailFragment();
        outcomeFragment.setLog_type(0);
        fragments.add(outcomeFragment);

        vp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        loadView();
    }

    @Override
    protected void loadView() {
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvIncome.setSelected(position == 0);
                tvOutCome.setSelected(position == 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

    @OnClick({R.id.tvIncome, R.id.tvOutCome})
    public void onClick(View v) {
        int position = v.getId() == R.id.tvIncome ? 0 : 1;
        vp.setCurrentItem(position);
    }
}
