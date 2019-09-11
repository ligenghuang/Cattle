package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BaseAction;
import com.zhifeng.cattle.actions.OrderAction;
import com.zhifeng.cattle.adapters.OrderListAdapter;
import com.zhifeng.cattle.modules.OrderListDto;
import com.zhifeng.cattle.ui.impl.OrderView;
import com.zhifeng.cattle.utils.base.UserBaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: 我的订单fragment
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/11 14:44
 * @Version: 1.0
 */

public class OrderFrament extends UserBaseFragment<OrderAction> implements OrderView {
    View view;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    OrderListAdapter orderListAdapter;

    int type;

    public OrderFrament(int type) {
        this.type = type;
    }

    @Override
    protected OrderAction initAction() {
        return new OrderAction((RxAppCompatActivity) getActivity(),this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getContext();
        mActivity = activity;
    }

    @Override
    protected void initialize() {
        init();
        loadView();
    }

    @Override
    protected void init() {
        super.init();

        refreshLayout.setEnableLoadMore(false);
        orderListAdapter = new OrderListAdapter(mContext);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(orderListAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);
        binding();
        return view;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible&&OrderActivity.Position == type){
            refreshLayout.autoRefresh();
        }
    }

    @Override
    protected void loadView() {
        super.loadView();
        orderListAdapter.setOnClickListener(new OrderListAdapter.OnClickListener() {
            @Override
            public void onClick(int id) {
                //todo 跳转至订单详情
                Intent intent = new Intent(mContext,OrderDetailActivity.class);
                intent.putExtra("order_id",id);
                startActivity(intent);
            }

            @Override
            public void Delete(int id) {
                //todo 删除订单
            }

            @Override
            public void Logistics(int id) {
                //todo 查看物流
            }

            @Override
            public void Cancel(int id) {
                //todo 取消订单
            }

            @Override
            public void Pay(int id) {
                //todo 去付款
            }

            @Override
            public void Refund(int id) {
                //todo 退款
            }

            @Override
            public void Confirm(int id) {
                //todo 确定收货
            }
        });
    }

    /**
     * 获取订单列表
     */
    @Override
    public void refresOrderList() {
        if (CheckNetwork.checkNetwork2(mContext)){
            baseAction.getOrderList(type);
        }else {
            refreshLayout.finishRefresh();
            loadDiss();
        }
    }

    /**
     * 获取订单列表成功
     * @param orderListDto
     */
    @Override
    public void getOrderListSuccess(OrderListDto orderListDto) {
        refreshLayout.finishRefresh();
        loadDiss();
        orderListAdapter.refresh(orderListDto.getData());
    }

    /**
     * 失败
     * @param message
     * @param code
     */
    @Override
    public void onError(String message, int code) {
        refreshLayout.finishRefresh();
        loadDiss();
        showToast(message);
    }

    @Override
    public void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }

    @Override
    public void onResume() {
        super.onResume();
        baseAction.toRegister();
    }
}
