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
import com.zhifeng.cattle.actions.DetailRecordAction;
import com.zhifeng.cattle.adapters.DetailRecordAdapter;
import com.zhifeng.cattle.modules.DetailRecord;
import com.zhifeng.cattle.ui.impl.DetailRecordView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;

/**
 * @ClassName:
 * @Description: 明细记录
 * @Author: Administrator
 * @Date: 2019/9/17 15:36
 */
public class DetailRecordActivity extends UserBaseActivity<DetailRecordAction> implements DetailRecordView {
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
    private DetailRecordAdapter adapter;
    private int page = 1;
    private boolean isRefresh = true;
    //是否加载更多
    private boolean isSlect = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_detail_record;
    }

    @Override
    protected DetailRecordAction initAction() {
        return new DetailRecordAction(this, this);
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        adapter = new DetailRecordAdapter();
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
                getDetailRecordList();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreRecordList();
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
                .addTag("DetailRecordActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());

        fTitleTv.setText(ResUtil.getString(R.string.detail_record_tab_1));
    }

    @Override
    public void getDetailRecordList() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            page = 1;
            isRefresh = true;
            baseAction.getDetailRecordList(page);
        } else {
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void loadMoreRecordList() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            isRefresh = false;
            page++;
            baseAction.getDetailRecordList(page);
        } else {
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void getDetailRecordListSuccess(DetailRecord detailRecord) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        List<DetailRecord.DataBeanX.DataBean> dataBeans = detailRecord.getData().getData();
        if (dataBeans.size() != 0) {
            recyclerview.setVisibility(View.VISIBLE);
            isSlect = page < detailRecord.getData().getLast_page();
            if (isRefresh) {
                adapter.refresh(dataBeans);
            } else {
                adapter.loadMore(dataBeans);
            }
        } else {
            isSlect = false;
            loadSwapTab();
        }
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
