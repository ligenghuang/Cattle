package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
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
import com.zhifeng.cattle.actions.OrderDetailAction;
import com.zhifeng.cattle.adapters.OrderDetailGoodsAdapter;
import com.zhifeng.cattle.adapters.PayTypeAdapter;
import com.zhifeng.cattle.modules.OrderDetailDto;
import com.zhifeng.cattle.modules.PayOrderDto;
import com.zhifeng.cattle.modules.PayTypeDto;
import com.zhifeng.cattle.modules.Temporary;
import com.zhifeng.cattle.modules.post.SubmitOrderPost;
import com.zhifeng.cattle.ui.impl.OrderDetailView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;
import com.zhifeng.cattle.utils.data.MySp;
import com.zhifeng.cattle.utils.dialog.PayPwdDialog;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: 订单详情页
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/11 16:09
 * @Version: 1.0
 */

public class OrderDetailActivity extends UserBaseActivity<OrderDetailAction> implements OrderDetailView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_order_goods)
    RecyclerView rvOrderGoods;
    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_order_address)
    TextView tvOrderAddress;
    @BindView(R.id.tv_order_consignee)
    TextView tvOrderConsignee;
    @BindView(R.id.tv_order_pay_type)
    TextView tvOrderPayType;
    @BindView(R.id.tv_order_distribution_type)
    TextView tvOrderDistributionType;
    @BindView(R.id.tv_order_message)
    TextView tvOrderMessage;
    @BindView(R.id.tv_order_goods_price)
    TextView tvOrderGoodsPrice;
    @BindView(R.id.tv_order_freight)
    TextView tvOrderFreight;
    @BindView(R.id.tv_order_integral)
    TextView tvOrderIntegral;
    @BindView(R.id.tv_order_total_price)
    TextView tvOrderTotalPrice;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ll_pay)
    LinearLayout llPay;
    @BindView(R.id.ll_pay_type)
    LinearLayout llPayType;
    @BindView(R.id.rvPayType)
    RecyclerView rvPayType;

    private PayTypeAdapter payTypeAdapter;
    double money = 0;
    String payTypeNam;
    int payType;
    int id;
    PayPwdDialog bugPwdDialog;
    int is_pwd;

    OrderDetailGoodsAdapter orderDetailGoodsAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected OrderDetailAction initAction() {
        return new OrderDetailAction(this, this);
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
                .addTag("OrderDetailActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.order_tab_28));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        id = getIntent().getIntExtra("order_id", 0);

        refreshLayout.setEnableLoadMore(false);
        orderDetailGoodsAdapter = new OrderDetailGoodsAdapter(this);
        rvOrderGoods.setLayoutManager(new LinearLayoutManager(this));
        rvOrderGoods.setAdapter(orderDetailGoodsAdapter);

        rvPayType.setLayoutManager(new LinearLayoutManager(mContext));
        payTypeAdapter = new PayTypeAdapter();
        rvPayType.setAdapter(payTypeAdapter);

        loadDialog();
        getOrderDetail();
        getPayType();
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getOrderDetail();
            }
        });
        payTypeAdapter.setOnClickListener(new PayTypeAdapter.OnClickListener() {
            @Override
            public void onClick(int type, String name) {
                List<Temporary.DataBean.PayTypeBean> list = payTypeAdapter.getAllData();
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setSelect(list.get(i).getPay_type() == type);
                }
                payTypeNam = name;
                payType = type;
                payTypeAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 获取订单详情
     */
    @Override
    public void getOrderDetail() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getOrderDetail(id);
        }
    }

    /**
     * 获取订单成功
     *
     * @param orderDetailDto
     */
    @Override
    public void getOrderDetailSuccess(OrderDetailDto orderDetailDto) {
        loadDiss();
        refreshLayout.finishRefresh();
        OrderDetailDto.DataBean dataBean = orderDetailDto.getData();

        orderDetailGoodsAdapter.refresh(dataBean.getGoods_res());

        tvOrderId.setText(dataBean.getOrder_sn());//订单编号
        tvOrderTime.setText(dataBean.getAdd_time());//下单时间
        tvOrderAddress.setText(dataBean.getAddress());//收货地址
        tvOrderConsignee.setText(dataBean.getConsignee());//收货人
        tvOrderPayType.setText(dataBean.getPay_type().getPay_name());//支付方式
        tvOrderMessage.setText(TextUtils.isEmpty(dataBean.getUser_note()) ? "无" : dataBean.getUser_note());//买家留言
        tvOrderGoodsPrice.setText("AU$" + dataBean.getTotal_amount());
        tvOrderTotalPrice.setText("AU$" + dataBean.getTotal_amount());
        tvOrderFreight.setText("AU$" + dataBean.getShipping_price());
        tvOrderIntegral.setText(dataBean.getCoupon_price());
        tvOrderPayType.setVisibility(dataBean.getStatus() != 1 ? View.VISIBLE : View.GONE);
        llPay.setVisibility(dataBean.getStatus() == 1 ? View.VISIBLE : View.GONE);
        llPayType.setVisibility(dataBean.getStatus() == 1 ? View.VISIBLE : View.GONE);
        money = Double.parseDouble(dataBean.getOrder_amount());
        is_pwd = dataBean.getIs_pwd();
        MySp.setPwd(mContext,is_pwd);
    }

    /**
     * 获取支付方式
     */
    @Override
    public void getPayType() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getPayType();
        }
    }

    /**
     * 获取支付方式 成功
     *
     * @param payTypeDto
     */
    @Override
    public void getPayTypeSuccess(PayTypeDto payTypeDto) {
        List<Temporary.DataBean.PayTypeBean> payTypeBeans = payTypeDto.getData();
        payTypeBeans.get(0).setSelect(true);
        payTypeNam = payTypeBeans.get(0).getPay_name();
        payType = payTypeBeans.get(0).getPay_type();
        payTypeAdapter.refresh(payTypeBeans);
    }

    /**
     * 支付
     *
     * @param submitOrderPost
     */
    @Override
    public void payOrder(SubmitOrderPost submitOrderPost) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.payOrder(submitOrderPost);
        }
    }

    /**
     * 支付成功
     *
     * @param submitOrderDto
     */
    @Override
    public void payOrderSuccess(PayOrderDto submitOrderDto) {
        loadDiss();
        showNormalToast(ResUtil.getString(R.string.goods_detail_tab_29));
        if (bugPwdDialog!= null){
            bugPwdDialog.dismiss();
        }
        getOrderDetail();
    }

    /**
     * 支付失败
     *
     * @param msg
     */
    @Override
    public void payOrderError(String msg) {
        L.e("lgh_pay", "输出返回结果4" + msg);
        loadDiss();
        showNormalToast(msg);
    }


    /**
     * 失败
     *
     * @param message
     * @param code
     */
    @Override
    public void onError(String message, int code) {
        loadDiss();
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

    @OnClick(R.id.btnPay)
    public void onViewClicked() {
        switch (payType) {
            case 1:
                //余额支付
                if (is_pwd == 1) {
                    bugPwdDialog = new PayPwdDialog(mContext, R.style.MY_AlertDialog, money, payTypeNam);
                    bugPwdDialog.setOnFinishInput(new PayPwdDialog.OnFinishInput() {
                        @Override
                        public void inputFinish(String password) {
                            //支付订单
                            SubmitOrderPost post = new SubmitOrderPost();
                            post.setCart_id(id+"");
                            post.setPay_type(payType + "");
                            post.setPwd(password);
                            payOrder(post);
                        }

                        @Override
                        public void close() {

                        }
                    });
                    bugPwdDialog.show();
                } else {
                    showNormalToast(ResUtil.getString(R.string.goods_detail_tab_30));
                }
                break;
            case 2:
            case 3:
                showNormalToast("暂无开通微信支付和支付宝支付");
                break;
        }
    }
}
