package com.zhifeng.cattle.ui.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.CheckDetailAction;
import com.zhifeng.cattle.adapters.CheckDetailAdapter;
import com.zhifeng.cattle.modules.CheckDetail;
import com.zhifeng.cattle.ui.impl.CheckDetailView;
import com.zhifeng.cattle.utils.base.UserBaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName:
 * @Description: 账单明细收益或支出
 * @Author: Administrator
 * @Date: 2019/9/24 10:55
 */
public class CheckDetailFragment extends UserBaseFragment<CheckDetailAction> implements CheckDetailView {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv)
    RecyclerView rv;
    private final int pageSize = 20;
    private int page = 1;
    private boolean isRefresh = true;
    private boolean isMore = true;
    private int log_type;
    private CheckDetailAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_item_check_detail, container, false);
        ButterKnife.bind(this, view);
        binding();
        return view;
    }

    @Override
    protected CheckDetailAction initAction() {
        return new CheckDetailAction((RxAppCompatActivity) getActivity(), this);
    }

    @Override
    protected void initialize() {
        adapter = new CheckDetailAdapter();
        rv.addItemDecoration(new DividerItemDecoration(mContext, RecyclerView.VERTICAL));
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        rv.setAdapter(adapter);
        loadView();
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        rv.setVisibility(View.GONE);
        if (isVisible && ((CheckDetailActivity) mActivity).mLogType == log_type) {
            refreshLayout.autoRefresh();
        }
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
            loadSwapTab();
            if (isRefresh) {
                adapter.refresh(beans);
            } else {
                adapter.loadMore(beans);
            }
        } else {
            rv.setVisibility(View.GONE);
            isMore = false;
            loadSwapTab();
        }
    }

    /**
     * tab变换 加载更多的显示方式
     */
    private void loadSwapTab() {
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
    }

    void setLog_type(int log_type) {
        this.log_type = log_type;
    }

    @Override
    public void onResume() {
        super.onResume();
        baseAction.toRegister();
    }

    @Override
    public void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }
}
