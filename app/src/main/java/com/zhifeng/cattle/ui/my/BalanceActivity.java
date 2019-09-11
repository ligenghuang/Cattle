package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BalanceAction;
import com.zhifeng.cattle.modules.BalanceDto;
import com.zhifeng.cattle.ui.impl.BalanceView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: 我的余额
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/11 9:20
 * @Version: 1.0
 */

public class BalanceActivity extends UserBaseActivity<BalanceAction> implements BalanceView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_balance_money)
    TextView tvBalanceMoney;
    @BindView(R.id.RefreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    public int intiLayout() {
        return R.layout.activity_balance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected BalanceAction initAction() {
        return new BalanceAction(this, this);
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
                .addTag("BalanceActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.balance_tab_1));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        refreshLayout.setEnableLoadMore(false);

        loadDialog();
        getBalance();
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getBalance();
            }
        });
    }

    /**
     * 获取提现余额
     */
    @Override
    public void getBalance() {
        if (CheckNetwork.checkNetwork2(mContext)){
            baseAction.getBalance();
        }else {
            loadDiss();
            refreshLayout.finishRefresh();
        }
    }

    /**
     * 获取余额成功
     * @param balanceDto
     */
    @Override
    public void getBalanceSuccess(BalanceDto balanceDto) {
        loadDiss();
        refreshLayout.finishRefresh();
        double money = Double.parseDouble(balanceDto.getData().getRemainder_money());
        DecimalFormat df = new DecimalFormat("#.00");
        tvBalanceMoney.setText("￥"+df.format(money));
    }

    @Override
    public void onError(String message, int code) {
        loadDiss();
        refreshLayout.finishRefresh();
        showNormalToast(message);
        tvBalanceMoney.setText("￥ 0");
    }

    @Override
    protected void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }

    @Override
    protected void onResume() {
        super.onResume();
        baseAction.toRegister();
    }

    @OnClick({R.id.tv_balance_recharge, R.id.tv_balance_withdrawal, R.id.cv_balance_withdrawal_detail, R.id.cv_balance_recharge_detail, R.id.cv_balance_bill_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_balance_recharge:
                //todo 充值
                break;
            case R.id.tv_balance_withdrawal:
                //todo 提现
                break;
            case R.id.cv_balance_withdrawal_detail:
                //todo 提现明细
                break;
            case R.id.cv_balance_recharge_detail:
                //todo 充值明细
                break;
            case R.id.cv_balance_bill_detail:
                //todo 账单明细
                break;
        }
    }
}