package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BaseAction;
import com.zhifeng.cattle.ui.impl.ModifyPayPwdView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

public class PayPwdActivity extends UserBaseActivity {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String Mobile;

    @Override
    public int intiLayout() {
        return R.layout.activity_pay_pwd;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected BaseAction initAction() {
        return null;
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
                .addTag("PayPwdActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.security_tab_3));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        Mobile = getIntent().getStringExtra("phone");
    }


    @OnClick({R.id.tv_modify_pwd, R.id.tv_forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_modify_pwd:
                //todo 修改支付密码
                jumpActivityNotFinish(mContext, ModifyPayPwdActivity.class);
                break;
            case R.id.tv_forget_pwd:
                //todo 忘记支付密码
                Intent intent = new Intent(mContext,ForgetPwdActivity.class);
                intent.putExtra("type",0);
                intent.putExtra("phone",Mobile);
                startActivity(intent);
                break;
        }
    }
}
