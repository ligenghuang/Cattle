package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BonusDayAction;
import com.zhifeng.cattle.adapters.BonusDayListAdapter;
import com.zhifeng.cattle.modules.BonusDayDto;
import com.zhifeng.cattle.ui.impl.BonusDayView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;

/**
 * @ClassName: 当日累计奖金
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/12 10:16
 * @Version: 1.0
 */

public class BonusDayActivity extends UserBaseActivity<BonusDayAction> implements BonusDayView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_bonus_time)
    TextView tvBonusTime;
    @BindView(R.id.tv_bonus_day)
    TextView tvBonusDay;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    BonusDayListAdapter bonusDayListAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_bonus_day;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected BonusDayAction initAction() {
        return new BonusDayAction(this,this);
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
                .addTag("BonusDayActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.my_tab_10));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        refreshLayout.setEnableLoadMore(false);
        bonusDayListAdapter = new BonusDayListAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(bonusDayListAdapter);

        loadDialog();
        getBonusDay();
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getBonusDay();
            }
        });
    }

    /**
     * 获取当日累计奖励
     */
    @Override
    public void getBonusDay() {
        if (CheckNetwork.checkNetwork2(mContext)){
            baseAction.getBonusDay();
        }else {
            loadDiss();
            refreshLayout.finishRefresh();
        }
    }

    /**
     * 获取当日累计奖励 成功
     * @param bonusDayDto
     */
    @Override
    public void getBonusDaySuccess(BonusDayDto bonusDayDto) {
        loadDiss();
        refreshLayout.finishRefresh();
        tvBonusDay.setText(bonusDayDto.getData().getTotal());
        tvBonusTime.setText(ResUtil.getFormatString(R.string.bonus_day_tab_4,bonusDayDto.getData().getLottery_time()));

        bonusDayListAdapter.refresh(bonusDayDto.getData().getList());

    }

    /**
     * 失败
     * @param message
     * @param code
     */
    @Override
    public void onError(String message, int code) {
        loadDiss();
        refreshLayout.finishRefresh();
        showNormalToast(message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        baseAction.toRegister();
    }

    @Override
    protected void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }
}
