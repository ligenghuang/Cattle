package com.zhifeng.cattle.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.TemporaryAction;
import com.zhifeng.cattle.adapters.GoodsResAdapter;
import com.zhifeng.cattle.adapters.PayTypeAdapter;
import com.zhifeng.cattle.modules.PayOrderDto;
import com.zhifeng.cattle.modules.SubmitOrderDto;
import com.zhifeng.cattle.modules.Temporary;
import com.zhifeng.cattle.modules.post.SubmitOrderPost;
import com.zhifeng.cattle.ui.impl.TemporaryView;
import com.zhifeng.cattle.ui.my.AddressListActivity;
import com.zhifeng.cattle.ui.my.OrderDetailActivity;
import com.zhifeng.cattle.utils.base.UserBaseActivity;
import com.zhifeng.cattle.utils.dialog.PayPwdDialog;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName:
 * @Description: 提交订单
 * @Author: Administrator
 * @Date: 2019/9/21 16:53
 */
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
    @BindView(R.id.tvShipping_price)
    TextView tvShippingPrice;
    @BindView(R.id.et_order_note)
    EditText etOrderNote;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.tv_no_address)
    TextView tvNoAddress;
    @BindView(R.id.ll_order_address)
    LinearLayout llOrderAddress;
    private String cartId;
    private GoodsResAdapter adapter;
    private PayTypeAdapter payTypeAdapter;

    double money = 0;
    String payTypeNam;
    int payType;
    int addressId = -1;
    PayPwdDialog bugPwdDialog;
    int pwd;

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
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getTemporary(cartId);
        }
    }

    @Override
    public void getTemporarySuccess(Temporary temporary) {
        bindView(temporary);
    }

    /**
     * 提交订单
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
     * 提交订单成功
     *
     * @param submitOrderDto
     */
    @Override
    public void submitOrderSuccess(SubmitOrderDto submitOrderDto) {
        loadDiss();
        switch (payType) {
            case 1:
                //余额支付
                if (pwd == 1) {
                    bugPwdDialog = new PayPwdDialog(mContext, R.style.MY_AlertDialog, money, payTypeNam);
                    bugPwdDialog.setOnFinishInput(new PayPwdDialog.OnFinishInput() {
                        @Override
                        public void inputFinish(String password) {
                            //支付订单
                            SubmitOrderPost post = new SubmitOrderPost();
                            post.setCart_id(submitOrderDto.getData());
                            post.setPay_type(payType + "");
                            post.setPwd(password);
                            payOrder(post);
                        }

                        @Override
                        public void close() {
                            //取消支付  跳转至订单详情页
                            Intent intent = new Intent(mContext, OrderDetailActivity.class);
                            intent.putExtra("order_id", Integer.parseInt(submitOrderDto.getData()));
                            startActivity(intent);
                            finish();
                        }
                    });
                    bugPwdDialog.show();
                } else {
                   showNormalToast(ResUtil.getString(R.string.goods_detail_tab_30));
                }
                break;
            case 2:
                break;
            case 3:
                break;
        }

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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext, OrderDetailActivity.class);
                intent.putExtra("order_id", submitOrderDto.getData().getOrder_id());
                startActivity(intent);
                finish();
            }
        }, 2000);
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

    private void bindView(Temporary temporary) {
        Temporary.DataBean dataBean = temporary.getData();
        if (dataBean.getAddr_res().getAddress_id() == 0) {
            llAddress.setVisibility(View.GONE);
            tvNoAddress.setVisibility(View.VISIBLE);
        } else {
            llAddress.setVisibility(View.VISIBLE);
            tvNoAddress.setVisibility(View.GONE);
            addressId = dataBean.getAddr_res().getAddress_id();
            tvUser.setText(dataBean.getAddr_res().getConsignee());
            tvMoblie.setText(dataBean.getAddr_res().getMobile());
            tvAddress.setText(dataBean.getAddr_res().getAddress());
        }
        double freight = Double.parseDouble(dataBean.getShipping_price());
        tvShippingPrice.setText(freight == 0 ? ResUtil.getString(R.string.goods_detail_tab_7) : "￥" + dataBean.getShipping_price());//运费
        adapter.refresh(dataBean.getGoods_res());
        List<Temporary.DataBean.PayTypeBean> payTypeBeans = dataBean.getPay_type();
        payTypeBeans.get(0).setSelect(true);
        payTypeNam = payTypeBeans.get(0).getPay_name();
        payType = payTypeBeans.get(0).getPay_type();
        payTypeAdapter.refresh(payTypeBeans);
        int num = 0;
        double totalPrice = 0;
        for (Temporary.DataBean.GoodsResBean goodsResBean : dataBean.getGoods_res()) {
            num += goodsResBean.getGoods_num();
            totalPrice += Double.parseDouble(goodsResBean.getSubtotal_price());
        }
        money = totalPrice;
        tvTotalGoodsNum.setText(ResUtil.getFormatString(R.string.cart_tab_26, String.valueOf(num)));
        tvTotalGoodsPrice.setText(ResUtil.getFormatString(R.string.cart_tab_17, String.valueOf(totalPrice)));
        tvTotalNum.setText(ResUtil.getFormatString(R.string.cart_tab_32, String.valueOf(num)));
        tvTotalPrice.setText(ResUtil.getFormatString(R.string.cart_tab_17, String.valueOf(totalPrice)));
        pwd = dataBean.getPwd();
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
        loadDiss();
        loadDiss();
        showNormalToast(message);
    }

    /**
     * 提交订单
     */
    private void buyNow() {
        //未选择收货地址
        if (addressId == -1) {
            showNormalToast(ResUtil.getString(R.string.cart_tab_36));
            return;
        }
        SubmitOrderPost post = new SubmitOrderPost();
        post.setCart_id(cartId);
        post.setAddress_id(addressId + "");
        post.setPay_type(payType + "");
        if (!TextUtils.isEmpty(etOrderNote.getText().toString())) {
            post.setUser_note(etOrderNote.getText().toString());
        }
        submitOrder(post);

    }

    @OnClick({R.id.ll, R.id.tvCertificate, R.id.ll_order_address, R.id.btnPay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll:
                ll.requestFocus();
                break;
            case R.id.tvCertificate:
                jumpActivityNotFinish(mContext, CertificationActivity.class);
                break;
            case R.id.ll_order_address:
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
                String address = data.getStringExtra("address2");
                String address_info = data.getStringExtra("address");
                String phone = data.getStringExtra("phone");
                String consignee = data.getStringExtra("consignee");
                addressId = data.getIntExtra("address_id", -1);
                tvAddress.setText(address_info + " " + address);
                tvUser.setText(consignee);
                tvMoblie.setText(phone);
                llAddress.setVisibility(View.VISIBLE);
                tvNoAddress.setVisibility(View.GONE);
            }
        }
    }


}
