package com.zhifeng.cattle.ui.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.OrderCommentAction;
import com.zhifeng.cattle.modules.OrderComment;
import com.zhifeng.cattle.modules.OrderCommentResult;
import com.zhifeng.cattle.ui.impl.OrderCommentView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;
import com.zhifeng.cattle.utils.view.FlowLayout;

import java.lang.ref.WeakReference;
import java.util.Base64;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName:
 * @Description: 商品评价
 * @Author: Administrator
 * @Date: 2019/9/19 16:41
 */
public class OrderCommentActivity extends UserBaseActivity<OrderCommentAction> implements OrderCommentView {
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etContent)
    EditText etContent;
    @BindView(R.id.pic_flow)
    FlowLayout pic_flow;
    @BindView(R.id.describeRatingBar)
    RatingBar describeRatingBar;
    @BindView(R.id.logisticsRatingBar)
    RatingBar logisticsRatingBar;
    @BindView(R.id.serviceRatingBar)
    RatingBar serviceRatingBar;
    private String order_id;
    private String goods_id;
    private String sku_id;
    private int describe;
    private int logistics;
    private int service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_ordercomment;
    }

    @Override
    protected OrderCommentAction initAction() {
        return new OrderCommentAction(this, this);
    }

    @Override
    protected void init() {
        super.init();
        order_id = getIntent().getStringExtra("order_id");
        goods_id = getIntent().getStringExtra("goods_id");
        sku_id = getIntent().getStringExtra("sku_id");
        loadView();
    }

    @Override
    protected void loadView() {
        describeRatingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            L.e("lghl", "rating   = " + rating);
            describe = (int) Math.ceil(rating);//获取多少星星
        });
        logisticsRatingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            L.e("lghl", "rating   = " + rating);
            logistics = (int) Math.ceil(rating);//获取多少星星
        });
        serviceRatingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            L.e("lghl", "rating   = " + rating);
            service = (int) Math.ceil(rating);//获取多少星星
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
                .addTag("OrderCommentActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.order_comment_tab_1));
    }

    private void postComment() {
        String content = etContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            showNormalToast("请输入评价内容");
            return;
        }
        Base64[] img = new Base64[3];
        OrderComment orderComment = new OrderComment(order_id, goods_id, sku_id, describe, logistics, service, content, img);
        baseAction.postComment(orderComment.toString());
    }

    @Override
    public void postCommentSuccess(OrderCommentResult orderCommentResult) {

    }

    @Override
    public void onError(String message, int code) {
        showNormalToast(message);
    }

    @OnClick(R.id.btnPost)
    public void onClick(View view) {
        postComment();
    }
}
