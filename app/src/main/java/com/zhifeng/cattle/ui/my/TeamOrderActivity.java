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
import com.zhifeng.cattle.actions.TeamOrderAction;
import com.zhifeng.cattle.adapters.TeamOrderAdapter;
import com.zhifeng.cattle.modules.TeamOrder;
import com.zhifeng.cattle.ui.impl.TeamOrderView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
/**
 * @ClassName:
 * @Description: 我的 我的团队 团队列表查看
 * @Author: Administrator
 * @Date: 2019/9/18 18:23
 */
public class TeamOrderActivity extends UserBaseActivity<TeamOrderAction> implements TeamOrderView {
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
    private TeamOrderAdapter adapter;
    private int page = 1;
    private boolean isRefresh = true;
    private boolean isMore = true;
    private String team_member_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_team_list;
    }

    @Override
    protected TeamOrderAction initAction() {
        return new TeamOrderAction(this, this);
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        team_member_id=getIntent().getStringExtra("team_member_id");
        adapter = new TeamOrderAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
        refreshLayout.autoRefresh();
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getTeamOrder();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreTeamOrder();
            }
        });
    }

    @Override
    public void getTeamOrder() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            page = 1;
            isRefresh = true;
            baseAction.getTeamOrder(team_member_id,page);
        } else {
            refreshLayout.finishRefresh();
        }
    }

    private void loadMoreTeamOrder() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            isRefresh = false;
            page++;
            baseAction.getTeamOrder(team_member_id,page);
        } else {
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void getTeamOrderSuccess(TeamOrder teamOrder) {
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
        List<TeamOrder.DataBeanX.DataBean> dataBeans = teamOrder.getData().getData();
        if (dataBeans.size() != 0) {
            recyclerview.setVisibility(View.VISIBLE);
            isMore = page > teamOrder.getData().getLast_page();
            loadSwapTab();
            if (isRefresh) {
                adapter.refresh(dataBeans);
            } else {
                adapter.loadMore(dataBeans);
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
                .addTag("TeamListActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());

        fTitleTv.setText(ResUtil.getString(R.string.team_listorder_tab_1));
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

    @Override
    public void onError(String message, int code) {
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
        showNormalToast(message);
    }
}
