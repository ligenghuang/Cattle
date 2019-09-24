package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.OrderAction;
import com.zhifeng.cattle.adapters.OrderListAdapter;
import com.zhifeng.cattle.modules.OrderListDto;
import com.zhifeng.cattle.modules.PayOrderDto;
import com.zhifeng.cattle.modules.post.SubmitOrderPost;
import com.zhifeng.cattle.ui.impl.OrderView;
import com.zhifeng.cattle.utils.base.UserBaseFragment;
import com.zhifeng.cattle.utils.dialog.PayPwdDialog;

import java.util.List;

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
        if (isVisible && OrderActivity.Position == type){
            L.e("lgh_type","OrderActivity.Position   = "+ OrderActivity.Position);
            L.e("lgh_type","type   = "+ type);
            refreshLayout.autoRefresh();
        }
    }

    @Override
    protected void loadView() {
        super.loadView();
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresOrderList();
            }
        });

        orderListAdapter.setOnClickListener(new OrderListAdapter.OnClickListener() {
            @Override
            public void onClick(int id) {
                //todo 跳转至订单详情
                Intent intent = new Intent(mContext,OrderDetailActivity.class);
                intent.putExtra("order_id",id);
                startActivity(intent);
            }

            @Override
            public void Delete(int id,int status) {
                //todo 删除订单
                editOrderStatus(id,status);
            }

            @Override
            public void Logistics(int id) {
                //todo 查看物流
            }

            @Override
            public void Cancel(int id) {
                //todo 取消订单
                editOrderStatus(id,1);
            }

            @Override
            public void Pay(int id,int payType,String money) {
                //todo 去付款
                if (payType == 1){
                    //todo 余额支付
                    PayPwdDialog bugPwdDialog = new PayPwdDialog(mContext, R.style.MY_AlertDialog, Double.parseDouble(money), "余额支付");
                    bugPwdDialog.setOnFinishInput(new PayPwdDialog.OnFinishInput() {
                        @Override
                        public void inputFinish(String password) {
                            SubmitOrderPost post = new SubmitOrderPost();
                            post.setCart_id(id+"");
                            post.setPay_type(payType + "");
                            post.setPwd(password);
                            submitOrder(post);
                        }
                    });
                    bugPwdDialog.show();
                }else if (payType == 2){
                    //todo 微信支付
                    showToast("微信支付");
                }else if (payType == 3){
                    //todo 支付宝支付
                    showToast("支付宝支付");
                }
            }

            @Override
            public void Refund(int id) {
                //todo 退款
            }

            @Override
            public void Confirm(int id) {
                //todo 确定收货
                editOrderStatus(id,3);
            }

            @Override
            public void evaluation(int orderId, List<OrderListDto.DataBean.GoodsBean> goodsBeans) {
                //todo 去评价
                Intent i=new Intent(mContext,OrderCommentActivity.class);
                i.putExtra("order_id",orderId);
                i.putExtra("goods_id",goodsBeans.get(0).getGoods_id());
                i.putExtra("sku_id",goodsBeans.get(0).getSku_id());
                startActivity(i);
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
     * 修改订单状态
     * @param id
     * @param status
     */
    @Override
    public void editOrderStatus(int id, int status) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.editOrderStatus(id,status);
        }
    }

    /**
     * 修改订单状态 成功  需做处理
     * @param msg
     * @param status
     */
    @Override
    public void editOrderStatusSuccess(String msg, int status) {
        loadDiss();
        refreshLayout.autoRefresh();
    }

    /**
     * 订单支付
     *
     * @param submitOrderPost
     */
    @Override
    public void submitOrder(SubmitOrderPost submitOrderPost) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.submitOrder(submitOrderPost);
        }
    }

    /**
     * 订单支付 成功
     * @param submitOrderDto
     */
    @Override
    public void submitOrderSuccess(PayOrderDto submitOrderDto) {
        loadDiss();
        refreshLayout.autoRefresh();
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
