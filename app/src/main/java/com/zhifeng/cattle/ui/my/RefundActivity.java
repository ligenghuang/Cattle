package com.zhifeng.cattle.ui.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.RefundAction;
import com.zhifeng.cattle.adapters.RefundGoodsAdapter;
import com.zhifeng.cattle.modules.OrderDetailDto;
import com.zhifeng.cattle.modules.RefundDto;
import com.zhifeng.cattle.ui.impl.RefundView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName:
 * @Description: 申请退款
 * @Author: Administrator
 * @Date: 2019/9/24 17:37
 */
public class RefundActivity extends UserBaseActivity<RefundAction> implements RefundView {
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.etRemark)
    EditText etRemark;
    @BindView(R.id.tvTotalPrice)
    TextView tvTotalPrice;
    private String order_id;
    private RefundGoodsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
        getOrderDetail();
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_refund;
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        order_id = getIntent().getStringExtra("order_id");
        adapter = new RefundGoodsAdapter(mContext);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(adapter);
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
                .addTag("RefundActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());

        fTitleTv.setText(ResUtil.getString(R.string.refund_tab1));
    }

    @Override
    protected RefundAction initAction() {
        return new RefundAction(this, this);
    }

    @Override
    public void getOrderDetail() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getGoodsDetail(order_id);
        }
    }

    @Override
    public void getOrderDetailSuccess(OrderDetailDto orderDetailDto) {
        List<OrderDetailDto.DataBean.GoodsResBean> goodsResBeans = orderDetailDto.getData().getGoods_res();
        adapter.refresh(goodsResBeans);
        tvTotalPrice.setText(ResUtil.getFormatString(R.string.cart_tab_17, orderDetailDto.getData().getTotal_amount()));
    }

    @Override
    public void applyRefund() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            String remark = etRemark.getText().toString();
            if (TextUtils.isEmpty(remark)) {
                showNormalToast(ResUtil.getString(R.string.refund_tab5));
                return;
            }
            baseAction.applyRefund(order_id, 0, remark);
        }
    }

    @Override
    public void applyRefundSuccess(RefundDto refundDto) {
        showNormalToast(ResUtil.getString(R.string.refund_tab6));
        finish();
    }

    @Override
    public void onError(String message, int code) {
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

    @OnClick(R.id.btnPost)
    public void onClick(View v) {
        applyRefund();
    }
}
