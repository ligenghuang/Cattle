package com.zhifeng.cattle.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.ListPageAction;
import com.zhifeng.cattle.adapters.ListPageAdapter;
import com.zhifeng.cattle.modules.ListPage;
import com.zhifeng.cattle.ui.impl.ListPageView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName:
 * @Description: 列表页
 * @Author: Administrator
 * @Date: 2019/9/21 16:09
 */
public class ListPageActivity extends UserBaseActivity<ListPageAction> implements ListPageView {
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
    private final int pageSize = 20;
    private int page;
    private boolean isRefresh = true;
    private boolean isMore = true;
    private ListPageAdapter adapter;
    private String title;
    private String cat_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        cat_id = getIntent().getStringExtra("cat_id");
        title = getIntent().getStringExtra("name");
        binding();
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_listpage;
    }

    @Override
    protected ListPageAction initAction() {
        return new ListPageAction(this, this);
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        adapter = new ListPageAdapter(mContext);
        recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
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
                getListPage();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreListPage();
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
                .addTag("ListPageActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());

        fTitleTv.setText(title);
    }

    @Override
    public void getListPage() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            isRefresh = true;
            page = 1;
            baseAction.getListPage(cat_id, pageSize, page);
        } else {
            refreshLayout.finishRefresh();
        }
    }

    private void loadMoreListPage() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            isRefresh = false;
            page++;
            baseAction.getListPage(cat_id, pageSize, page);
        } else {
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void getListPageSuccess(ListPage listPage) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        List<ListPage.DataBeanX.GoodsBean.DataBean> beans = listPage.getData().getGoods().getData();
        if (beans.size() > 0) {
            recyclerview.setVisibility(View.VISIBLE);
            isMore = page < listPage.getData().getGoods().getLast_page();
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

    @Override
    public void onError(String message, int code) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        showNormalToast(message);
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
