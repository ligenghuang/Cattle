package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.SalesReturnAction;
import com.zhifeng.cattle.adapters.SalesReturnAdapter;
import com.zhifeng.cattle.modules.OrderListDto;
import com.zhifeng.cattle.ui.impl.SalesReturnView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;

/**
 * @ClassName: 退货
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/11 17:48
 * @Version: 1.0
 */

public class SalesReturnActivity extends UserBaseActivity<SalesReturnAction> implements SalesReturnView {

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

    SalesReturnAdapter salesReturnAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_sales_return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected SalesReturnAction initAction() {
        return new SalesReturnAction(this,this);
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
                .addTag("SalesReturnActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        fTitleTv.setText(ResUtil.getString(R.string.my_tab_9));
        toolbar.setNavigationOnClickListener(view -> finish());
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        refreshLayout.setEnableLoadMore(false);
        salesReturnAdapter = new SalesReturnAdapter(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(salesReturnAdapter);

        refreshLayout.autoRefresh();
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getSalesReturnList();
            }
        });
    }

    /**
     * 获取退货列表
     */
    @Override
    public void getSalesReturnList() {
        if (CheckNetwork.checkNetwork2(mContext)){
            baseAction.getOrderList();
        }else {
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void getSalesReturnListSucces(OrderListDto orderListDto) {
        refreshLayout.finishRefresh();
        salesReturnAdapter.refresh(orderListDto.getData());
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
