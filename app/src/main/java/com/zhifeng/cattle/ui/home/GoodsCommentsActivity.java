package com.zhifeng.cattle.ui.home;

import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
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
    @BindView(R.id.ivLookUpAll)
    ImageView ivLookUpAll;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private GoodsCommentsAdapter adapter;
    private boolean isRefresh = true;
    private boolean isMore = true;
    private int page = 1;
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
        String text = "(" + goods_comment_num + ")";
        tvCommentsNum.setText(text);
        RotateAnimation ra = new RotateAnimation(0, -90f, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        ra.setFillAfter(true);
        ivLookUpAll.startAnimation(ra);
        adapter = new GoodsCommentsAdapter();
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
            page = 1;
            baseAction.getComments(goods_id, page);
        } else {
            refreshLayout.finishRefresh();
        }
    }

    private void loadMoreGoodsComments() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            isRefresh = false;
            page++;
            baseAction.getComments(goods_id, page);
        } else {
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void getGoodsCommentsSuccess(GoodsComment goodsComment) {
        refreshLayout.finishRefresh();
        List<GoodsComment.DataBean> beans = goodsComment.getData();
        if (beans.size() > 0) {
            recyclerview.setVisibility(View.VISIBLE);
            adapter.refresh(beans);
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

//    @OnClick(R.id.llLookUpAll)
//    public void onViewClicked(View view) {
//
//    }
}
