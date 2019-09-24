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
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.ReChargeDetailAction;
import com.zhifeng.cattle.adapters.RechargeDetailAdapter;
import com.zhifeng.cattle.modules.ReChargeDetail;
import com.zhifeng.cattle.ui.impl.ReChargeDetailView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
/**
 * @ClassName:
 * @Description: 充值明细
 * @Author: Administrator
 * @Date: 2019/9/18 18:21
 */
public class RechargeDetailActivity extends UserBaseActivity<ReChargeDetailAction> implements ReChargeDetailView {
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
    private RechargeDetailAdapter adapter;

    boolean isRefresh = true;
    int page =1;
    //是否加载更多
    boolean isSlect = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_recharge_detail;
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        adapter = new RechargeDetailAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
        refreshLayout.autoRefresh();
        loadView();
    }

    @Override
    protected ReChargeDetailAction initAction() {
        return new ReChargeDetailAction(this, this);
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
                .addTag("RechargeDetailActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());

        fTitleTv.setText(ResUtil.getString(R.string.team_rechargedetail_tab_1));
    }

    @Override
    protected void loadView() {
        super.loadView();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getChargeDetail();
            }
        });
    }

    @Override
    public void getChargeDetail() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            isRefresh = true;
            page = 1;
            baseAction.getDetailRecordList(page);
        } else {
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void moreChargeDetail() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            isRefresh = false;
            page ++;
            baseAction.getDetailRecordList(page);
        } else {
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void getChargeDetailSuccess(ReChargeDetail reChargeDetail) {
        refreshLayout.finishRefresh();
        ReChargeDetail.DataBeanX dataBean = reChargeDetail.getData();
        if (dataBean.getList().getData().size() != 0){
            recyclerview.setVisibility(View.VISIBLE);
            isSlect = page < dataBean.getList().getCurrent_page();
            loadSwapTab();
            if (isRefresh){
                adapter.refresh(dataBean.getList().getData());
            }else {
                adapter.loadMore(dataBean.getList().getData());
            }
        }else {
            isSlect = false;
            loadSwapTab();
            //todo 添加空布局
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
}
