package com.zhifeng.cattle.ui.my;

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
import com.zhifeng.cattle.actions.CheckDetailAction;
import com.zhifeng.cattle.adapters.CheckDetailAdapter;
import com.zhifeng.cattle.modules.CheckDetail;
import com.zhifeng.cattle.ui.impl.CheckDetailView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName:
 * @Description: 我的-账单明细
 * @Author: Administrator
 * @Date: 2019/9/23 17:09
 */
public class CheckDetailActivity extends UserBaseActivity<CheckDetailAction> implements CheckDetailView {
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
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv)
    RecyclerView rv;
    private final int pageSize = 20;
    private int page = 1;
    private boolean isRefresh = true;
    private boolean isMore = true;
    private int log_type = 1;
    private CheckDetailAdapter adapter;

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
    protected CheckDetailAction initAction() {
        return new CheckDetailAction(this, this);
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
        adapter = new CheckDetailAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        tvIncome.setBackgroundColor(ResUtil.getColor(R.color.color_46b29b));
        refreshLayout.autoRefresh();
        loadView();
    }

    @Override
    protected void loadView() {
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getCheckDetail();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreCheckDetail();
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

    @Override
    public void getCheckDetail() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            page = 1;
            isRefresh = true;
            baseAction.getCheckDetail(log_type, pageSize, page);
        } else {
            refreshLayout.finishRefresh();
        }
    }

    private void loadMoreCheckDetail() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            isRefresh = false;
            page++;
            baseAction.getCheckDetail(log_type, pageSize, page);
        } else {
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void getCheckDetailSuccess(CheckDetail checkDetail) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        List<CheckDetail.DataBeanX.DataBean> beans = checkDetail.getData().getData();
        if (beans.size() > 0) {
            rv.setVisibility(View.VISIBLE);
            isMore = page < checkDetail.getData().getLast_page();
            if (isRefresh) {
                adapter.refresh(beans);
            } else {
                adapter.loadMore(beans);
            }
        } else {
            isMore = false;
            loadSwapTab();
        }
    }

    /**
     * tab变换 加载更多的显示方式
     */
    public void loadSwapTab() {
        if (!isMore) {
            L.e("xx", "设置为没有加载更多....");
            refreshLayout.finishLoadMoreWithNoMoreData();
            refreshLayout.setNoMoreData(true);
        } else {
            L.e("xx", "设置为可以加载更多....");
            refreshLayout.setNoMoreData(false);
        }
    }

    @Override
    public void onError(String message, int code) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
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

    @OnClick({R.id.tvIncome, R.id.tvOutCome})
    public void onClick(View v) {
        isMore = true;
        page = 1;
        log_type = v.getId() == R.id.tvIncome ? 1 : 0;
        tvIncome.setBackgroundColor(v.getId() == R.id.tvIncome ? ResUtil.getColor(R.color.color_46b29b) : ResUtil.getColor(R.color.white));
        tvOutCome.setBackgroundColor(v.getId() == R.id.tvOutCome ? ResUtil.getColor(R.color.color_46b29b) : ResUtil.getColor(R.color.white));
        getCheckDetail();
    }
}
