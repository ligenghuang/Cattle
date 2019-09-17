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
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.WithdrawalDetailAction;
import com.zhifeng.cattle.adapters.WithdrawalDetailAdapter;
import com.zhifeng.cattle.modules.WithdrawalListDto;
import com.zhifeng.cattle.ui.impl.WithdrawalDetailView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;

/**
 * @ClassName: 提现明细
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/17 11:25
 * @Version: 1.0
 */

public class WithdrawalDetailActivity extends UserBaseActivity<WithdrawalDetailAction> implements WithdrawalDetailView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    WithdrawalDetailAdapter withdrawalDetailAdapter;

    boolean isRefresh = true;
    int page =1;
    //是否加载更多
    boolean isSlect = true;

    @Override
    public int intiLayout() {
        return R.layout.activity_withdrawal_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected WithdrawalDetailAction initAction() {
        return new WithdrawalDetailAction(this,this);
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
                .addTag("WithdrawalDetailActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());

        fTitleTv.setText(ResUtil.getString(R.string.balance_tab_5));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        withdrawalDetailAdapter = new WithdrawalDetailAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(withdrawalDetailAdapter);

        refreshLayout.autoRefresh();
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //上拉加载
                moreWithdrawalList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //下拉刷新
                getWithdrawalList();
            }
        });
    }

    /**
     * 获取提现明细
     */
    @Override
    public void getWithdrawalList() {
        if(CheckNetwork.checkNetwork2(mContext)){
            isRefresh = true;
            page = 1;
            baseAction.getWithdrawalList(page);
        }else {
            refreshLayout.finishRefresh();
        }
    }

    /**
     * 获取更多
     */
    @Override
    public void moreWithdrawalList() {
        if(CheckNetwork.checkNetwork2(mContext)){
            isRefresh = false;
            page++;
            baseAction.getWithdrawalList(page);
        }else {
            refreshLayout.finishLoadMore();
        }
    }

    /**
     * 获取提现明细成功
     * @param withdrawalListDto
     */
    @Override
    public void getWithdrawalListSuccess(WithdrawalListDto withdrawalListDto) {
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
        WithdrawalListDto.DataBeanX.ListBean dataBean = withdrawalListDto.getData().getList();
        if (dataBean.getData().size() != 0){
            recyclerview.setVisibility(View.VISIBLE);
            isSlect = page < dataBean.getLast_page();
            loadSwapTab();
            if (isRefresh){
                withdrawalDetailAdapter.refresh(dataBean.getData());
            }else {
                withdrawalDetailAdapter.loadMore(dataBean.getData());
            }
        }else {
            isSlect = false;
            loadSwapTab();
            //todo 添加空布局
        }

    }

    /**
     * 失败
     * @param message
     * @param code
     */
    @Override
    public void onError(String message, int code) {
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
        showNormalToast(message);
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

    /**
     * tab变换 加载更多的显示方式
     */
    public void loadSwapTab() {

        if (!isSlect) {
            L.e("xx", "设置为没有加载更多....");
            refreshLayout.finishLoadMoreWithNoMoreData();
            refreshLayout.setNoMoreData(true);
        } else {
            L.e("xx", "设置为可以加载更多....");
            refreshLayout.setNoMoreData(false);
        }

    }
}
