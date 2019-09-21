package com.zhifeng.cattle.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.TemporaryAction;
import com.zhifeng.cattle.adapters.GoodsResAdapter;
import com.zhifeng.cattle.adapters.PayTypeAdapter;
import com.zhifeng.cattle.modules.Temporary;
import com.zhifeng.cattle.ui.impl.TemporaryView;
import com.zhifeng.cattle.ui.my.AddressListActivity;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TemporaryActivity extends UserBaseActivity<TemporaryAction> implements TemporaryView {
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivDingWei)
    ImageView ivDingWei;
    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.tv_moblie)
    TextView tvMoblie;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tvTotalGoodsNum)
    TextView tvTotalGoodsNum;
    @BindView(R.id.tvTotalGoodsPrice)
    TextView tvTotalGoodsPrice;
    @BindView(R.id.rvPayType)
    RecyclerView rvPayType;
    @BindView(R.id.tvTotalNum)
    TextView tvTotalNum;
    @BindView(R.id.tvTotalPrice)
    TextView tvTotalPrice;
    @BindView(R.id.btnPay)
    Button btnPay;
    private String cartId;
    private GoodsResAdapter adapter;
    private PayTypeAdapter payTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
        getTemporary();
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_temporary;
    }

    @Override
    protected TemporaryAction initAction() {
        return new TemporaryAction(this, this);
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        cartId = getIntent().getStringExtra("cartId");
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new GoodsResAdapter(mContext);
        rv.setAdapter(adapter);
        rvPayType.setLayoutManager(new LinearLayoutManager(mContext));
        payTypeAdapter = new PayTypeAdapter();
        rvPayType.setAdapter(payTypeAdapter);
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        adapter.setOnGoodsNumChangeListener((totalNum, totalPrice) -> {
            tvTotalGoodsNum.setText(ResUtil.getFormatString(R.string.cart_tab_26, String.valueOf(totalNum)));
            tvTotalGoodsPrice.setText(ResUtil.getFormatString(R.string.cart_tab_17, totalPrice));
            tvTotalNum.setText(ResUtil.getFormatString(R.string.cart_tab_32, String.valueOf(totalNum)));
            tvTotalPrice.setText(ResUtil.getFormatString(R.string.cart_tab_17, totalPrice));
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
                .addTag("TemporaryActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());

        fTitleTv.setText(ResUtil.getString(R.string.cart_tab_12));
    }

    @Override
    public void getTemporary() {
        baseAction.getTemporary(cartId);
    }

    @Override
    public void getTemporarySuccess(Temporary temporary) {
        bindView(temporary);
    }

    private void bindView(Temporary temporary) {
        Temporary.DataBean dataBean = temporary.getData();
        tvUser.setText(dataBean.getAddr_res().getConsignee());
        tvMoblie.setText(dataBean.getAddr_res().getMobile());
        tvAddress.setText(dataBean.getAddr_res().getAddress());
        adapter.setShipping_price(dataBean.getShipping_price());
        adapter.refresh(dataBean.getGoods_res());
        List<Temporary.DataBean.PayTypeBean> payTypeBeans = dataBean.getPay_type();
        payTypeBeans.get(0).setSelect(true);
        payTypeAdapter.refresh(payTypeBeans);
        int num = 0;
        double totalPrice = 0;
        for (Temporary.DataBean.GoodsResBean goodsResBean : dataBean.getGoods_res()) {
            num += goodsResBean.getGoods_num();
            totalPrice += Double.parseDouble(goodsResBean.getSubtotal_price());
        }
        tvTotalGoodsNum.setText(ResUtil.getFormatString(R.string.cart_tab_26, String.valueOf(num)));
        tvTotalGoodsPrice.setText(ResUtil.getFormatString(R.string.cart_tab_17, String.valueOf(totalPrice)));
        tvTotalNum.setText(ResUtil.getFormatString(R.string.cart_tab_32, String.valueOf(num)));
        tvTotalPrice.setText(ResUtil.getFormatString(R.string.cart_tab_17, String.valueOf(totalPrice)));
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
        showNormalToast(message);
    }

    private void buyNow() {
        for (Temporary.DataBean.PayTypeBean payTypeBean : payTypeAdapter.getAllData()) {
            if (payTypeBean.isSelect()) {
                int pay_type = payTypeBean.getPay_type();
                String address_id = tvAddress.getText().toString();
                StringBuilder sb = new StringBuilder();
                for (Temporary.DataBean.GoodsResBean goodsResBean : adapter.getAllData()) {
                    if (!TextUtils.isEmpty(sb.toString())) {
                        sb.append(",");
                    }
                    sb.append(goodsResBean.getCart_id());
                    break;
                }
                break;
            }
        }
    }

    @OnClick({R.id.ll, R.id.tvCertificate, R.id.ivDingWei, R.id.btnPay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll:
                ll.requestFocus();
                break;
            case R.id.tvCertificate:
                jumpActivityNotFinish(mContext,CertificationActivity.class);
                break;
            case R.id.ivDingWei:
                Intent i = new Intent(mContext, AddressListActivity.class);
                i.putExtra("isGoods", true);
                startActivityForResult(i, 200);
                break;
            case R.id.btnPay:
                buyNow();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 200 && resultCode == 200) {
            if (data != null) {
                String address = data.getStringExtra("address");
                tvAddress.setText(address);
            }
        }
    }
}
