package com.zhifeng.cattle.ui.home;

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
import com.zhifeng.cattle.actions.GoodsCommentsAction;
import com.zhifeng.cattle.adapters.GoodsCommentsAdapter;
import com.zhifeng.cattle.modules.GoodsComment;
import com.zhifeng.cattle.ui.impl.GoodsCommentsView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName:
 * @Description: 商品评价
 * @Author: Administrator
 * @Date: 2019/9/18 17:17
 */
public class GoodsCommentsActivity extends UserBaseActivity<GoodsCommentsAction> implements GoodsCommentsView {
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tvCommentsNum)
    TextView tvCommentsNum;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private GoodsCommentsAdapter adapter;
    private boolean isRefresh = true;
    private boolean isMore = true;
    private int page = 1;
    private final int pageSize = 20;
    private String goods_id;
    private String goods_comment_num;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        goods_id = getIntent().getStringExtra("goods_id");
        goods_comment_num = getIntent().getStringExtra("goods_comment_num");
        binding();
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_comments;
    }

    @Override
    protected GoodsCommentsAction initAction() {
        return new GoodsCommentsAction(this, this);
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

        tvCommentsNum.setText(ResUtil.getFormatString(R.string.goods_detail_tab_12, String.valueOf(goods_comment_num)));
        adapter = new GoodsCommentsAdapter(mContext);
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
                getGoodsComments();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreGoodsComments();
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
                .addTag("GoodsCommentsActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());

        fTitleTv.setText(ResUtil.getString(R.string.goods_detail_tab_22));
    }

    @Override
    public void getGoodsComments() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            isRefresh = true;
            page = 1;
            baseAction.getComments(goods_id, pageSize, page);
        } else {
            refreshLayout.finishRefresh();
        }
    }

    private void loadMoreGoodsComments() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            isRefresh = false;
            page++;
            baseAction.getComments(goods_id, pageSize, page);
        } else {
            isMore=false;
            loadSwapTab();
        }
    }

    @Override
    public void getGoodsCommentsSuccess(GoodsComment goodsComment) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        List<GoodsComment.DataBeanX.DataBean> beans = goodsComment.getData().getData();
        if (beans.size() > 0) {
            recyclerview.setVisibility(View.VISIBLE);
            isMore = page < goodsComment.getData().getLast_page();
            loadSwapTab();
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

//    @OnClick(R.id.tvLookUpAll)
//    public void onViewClicked(View view) {
//
//    }
}
