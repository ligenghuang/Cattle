package com.zhifeng.cattle.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.DensityUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.SearchGoodsAction;
import com.zhifeng.cattle.adapters.HotSearchAdapter;
import com.zhifeng.cattle.adapters.SearchGoodsAdapter;
import com.zhifeng.cattle.modules.SearchGoods;
import com.zhifeng.cattle.modules.SearchHistory;
import com.zhifeng.cattle.ui.impl.SearchGoodsView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;
import com.zhifeng.cattle.utils.view.SpaceItemDecoration;

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
    @BindView(R.id.llHotSearch)
    LinearLayout llHotSearch;
    @BindView(R.id.rv_hotSearch)
    RecyclerView rv_hotSearch;
    @BindView(R.id.rv_goods)
    RecyclerView rv_goods;
    //    @BindView(R.id.refreshLayout)
//    SmartRefreshLayout refreshLayout;
    private int page = 1;
    private int num = 10;
    private boolean isRefresh = true;
    private HotSearchAdapter hotSearchAdapter;
    private SearchGoodsAdapter adapter;
    private boolean isMore = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
        getSearchHistory();
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
        hotSearchAdapter = new HotSearchAdapter();
        rv_hotSearch.addItemDecoration(new SpaceItemDecoration(DensityUtil.dp2px(5)));
        rv_hotSearch.setLayoutManager(new GridLayoutManager(mContext, 5));
        rv_hotSearch.setAdapter(hotSearchAdapter);
        adapter = new SearchGoodsAdapter(mContext);
        rv_goods.setLayoutManager(new GridLayoutManager(mContext, 2));
        rv_goods.setAdapter(adapter);
        etSearch.requestFocus();
//        refreshLayout.autoRefresh();
        loadView();
    }

    @Override
    protected void initTitlebar() {
        mImmersionBar
                .statusBarView(R.id.top_view)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0.2f)
                .addTag("SearchGoodsActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
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
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (adapter.getAllData().size() == 0) {
                    llHotSearch.setVisibility(TextUtils.isEmpty(s.toString()) ? View.VISIBLE : View.GONE);
                } else {
                    llHotSearch.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                getGoods();
            }
            return false;
        });
        hotSearchAdapter.setOnItemClickListener((parent, view, position, id) -> {
            SearchHistory.DataBean dataBean = (SearchHistory.DataBean) hotSearchAdapter.getItem(position);
            etSearch.setText(dataBean.getKeyword());
            etSearch.setSelection(dataBean.getKeyword().length());
            getGoods();
        });
        adapter.setOnItemClickListener((parent, view, position, id) -> {
            SearchGoods.DataBean dataBean = (SearchGoods.DataBean) adapter.getItem(position);
            Intent i = new Intent(mContext, GoodsDetailActivity.class);
            i.putExtra("goods_id", dataBean.getGoods_id());
            startActivity(i);
        });
    }

    @Override
    public void getSearchHistory() {
        baseAction.getSearchHistory();
    }

    @Override
    public void getSearchHistorySuccess(SearchHistory searchHistory) {
        List<SearchHistory.DataBean> dataBeans = searchHistory.getData();
        hotSearchAdapter.refresh(dataBeans);
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
        llHotSearch.setVisibility(View.GONE);
        List<SearchGoods.DataBean> beans = searchGoods.getData();
        if (beans.size() > 0) {
//            isMore = page < searchGoods.getData();
//            if (isRefresh) {
            adapter.refresh(beans);
//            } else {
//                adapter.loadMore(beans);
//            }
        } else {
            isMore = false;
//            loadSwapTab();
        }
    }

    @Override
    public void onError(String message, int code) {
        llHotSearch.setVisibility(View.GONE);
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
