package com.zhifeng.cattle.ui.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.SearchGoodsAction;
import com.zhifeng.cattle.adapters.SearchGoodsAdapter;
import com.zhifeng.cattle.modules.SearchGoods;
import com.zhifeng.cattle.ui.impl.SearchGoodsView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName:
 * @Description: 首页搜索
 * @Author: Administrator
 * @Date: 2019/9/21 16:09
 */
public class SearchGoodsActivity extends UserBaseActivity<SearchGoodsAction> implements SearchGoodsView {
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    //    @BindView(R.id.refreshLayout)
//    SmartRefreshLayout refreshLayout;
    private int page = 1;
    private int num = 10;
    private boolean isRefresh = true;
    private SearchGoodsAdapter adapter;
    private boolean isMore = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_searchgoods;
    }

    @Override
    protected SearchGoodsAction initAction() {
        return new SearchGoodsAction(this, this);
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        adapter = new SearchGoodsAdapter();
        recyclerview.setLayoutManager(new GridLayoutManager(this, 5));
        recyclerview.setAdapter(adapter);
        etSearch.requestFocus();
//        refreshLayout.autoRefresh();
//        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
//        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                getGoods();
//            }
//
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                loadMoreListPage();
//            }
//        });
    }

    @Override
    public void getGoods() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            String keyword = etSearch.getText().toString();
            if (!TextUtils.isEmpty(keyword)) {
//                isRefresh = true;
                page = 1;
                baseAction.getGoods(etSearch.getText().toString(), page, num);
            }
        } else {
//            refreshLayout.finishRefresh();
        }
    }

    private void loadMoreListPage() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            isRefresh = false;
            page++;
            baseAction.getGoods(etSearch.getText().toString(), page, num);
        } else {
//            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void getGoodsSuccess(SearchGoods searchGoods) {
//        refreshLayout.finishRefresh();
//        refreshLayout.finishLoadMore();
        List<SearchGoods.DataBean> beans = searchGoods.getData();
        if (beans.size() > 0) {
            recyclerview.setVisibility(View.VISIBLE);
//            isMore = page < searchGoods.getData();
//            if (isRefresh) {
                adapter.refresh(beans);
//            } else {
//                adapter.loadMore(beans);
//            }
        } else {
            isMore = false;
            loadSwapTab();
        }
    }

    @Override
    public void onError(String message, int code) {
//        refreshLayout.finishRefresh();
//        refreshLayout.finishLoadMore();
        showNormalToast(message);
    }

    /**
     * tab变换 加载更多的显示方式
     */
    public void loadSwapTab() {
        if (!isMore) {
            L.e("xx", "设置为没有加载更多....");
//            refreshLayout.finishLoadMoreWithNoMoreData();
//            refreshLayout.setNoMoreData(true);
        } else {
            L.e("xx", "设置为可以加载更多....");
//            refreshLayout.setNoMoreData(false);
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

    @OnClick({R.id.ivSearch, R.id.tv_cancel})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivSearch:
                getGoods();
                break;
            case R.id.tv_cancel:
                finish();
                break;
        }
    }
}
