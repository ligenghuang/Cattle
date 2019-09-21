package com.zhifeng.cattle.ui.my;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.lgh.huanglib.util.base.ActivityStack;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BaseAction;
import com.zhifeng.cattle.ui.login.LoginActivity;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName:
 * @Description: 切换用户页面
 * @Author: Administrator
 * @Date: 2019/9/21 14:28
 */
public class ChangeAccountActivity extends UserBaseActivity {
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivAccount)
    ImageView ivAccount;
    @BindView(R.id.tvAccount)
    TextView tvAccount;
    @BindView(R.id.btnChangeAccount)
    Button btnChangeAccount;

    @Override
    public int intiLayout() {
        return R.layout.activity_changeaccount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
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
                .addTag("InvitationActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText("");
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
    }

    @OnClick(R.id.btnChangeAccount)
    public void onViewClicked(View view) {
        ActivityStack.getInstance().removeAll();
        jumpActivity(mContext, LoginActivity.class);
    }
}
