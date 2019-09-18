package com.zhifeng.cattle.ui.my;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.RankingListAction;
import com.zhifeng.cattle.adapters.RankingListAdapter;
import com.zhifeng.cattle.modules.RankingList;
import com.zhifeng.cattle.ui.impl.RankingListView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;

public class RankingListActivity extends UserBaseActivity<RankingListAction> implements RankingListView {
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
    @BindView(R.id.tvMyRaking)
    TextView tvMyRaking;
    private RankingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_ranking_list;
    }

    @Override
    protected RankingListAction initAction() {
        return new RankingListAction(this, this);
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        adapter = new RankingListAdapter();
        recyclerview.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
        refreshLayout.setEnableLoadMore(false);

        refreshLayout.autoRefresh();
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            getRankingList();
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
                .addTag("RankingListActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());

        fTitleTv.setText(ResUtil.getString(R.string.rankinglist_tab_1));
    }

    @Override
    public void getRankingList() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getRankingList();
        } else {
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void getRankingListSuccess(RankingList rankingList) {
        refreshLayout.finishRefresh();
        List<RankingList.DataBean> beans = rankingList.getData();
        if (beans.size() > 0) {
            recyclerview.setVisibility(View.VISIBLE);
            adapter.refresh(beans);
            tvMyRaking.setText(ResUtil.getFormatString(R.string.rankinglist_myranking, rankingList.getMy_ranking()));
        } else {

        }
    }

    @Override
    public void onError(String message, int code) {
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
