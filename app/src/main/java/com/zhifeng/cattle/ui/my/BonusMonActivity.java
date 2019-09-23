package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BonusMonAction;
import com.zhifeng.cattle.adapters.BonusMonListAdapter;
import com.zhifeng.cattle.modules.BonusMonDto;
import com.zhifeng.cattle.ui.impl.BonusMonView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;

/**
 * @ClassName: 当月累计奖金
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/12 10:16
 * @Version: 1.0
 */

public class BonusMonActivity extends UserBaseActivity<BonusMonAction> implements BonusMonView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_bonus_day)
    TextView tvBonusDay;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ll_data)
    LinearLayout llData;

    BonusMonListAdapter bonusMonListAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_bonus_mon;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected BonusMonAction initAction() {
        return new BonusMonAction(this,this);
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
                .addTag("BonusMonActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.my_tab_12));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        refreshLayout.setEnableLoadMore(false);
        bonusMonListAdapter = new BonusMonListAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(bonusMonListAdapter);

        loadDialog();
        getBonusMon();
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getBonusMon();
            }
        });
    }

    /**
     * 获取当日累计奖励
     */
    @Override
    public void getBonusMon() {
        if (CheckNetwork.checkNetwork2(mContext)){
            baseAction.getBonusMon();
        }else {
            loadDiss();
            refreshLayout.finishRefresh();
        }
    }

    /**
     * 获取当日累计奖励 成功
     * @param bonusMonDto
     */
    @Override
    public void getBonusMonSuccess(BonusMonDto bonusMonDto) {
        loadDiss();
        refreshLayout.finishRefresh();
        tvBonusDay.setText(bonusMonDto.getData().getTotal());
        L.e("lgh_total","Total   = "+bonusMonDto.getData().getTotal());
        llData.setVisibility(bonusMonDto.getData().getList().size() == 0?View.GONE:View.VISIBLE);
        bonusMonListAdapter.refresh(bonusMonDto.getData().getList());

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
