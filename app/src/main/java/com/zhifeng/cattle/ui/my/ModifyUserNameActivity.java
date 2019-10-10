package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.ModifyUserNameAction;
import com.zhifeng.cattle.ui.impl.ModifyUserNameView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: 修改用户名
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/10/9 14:24
 * @Version: 1.0
 */

public class ModifyUserNameActivity extends UserBaseActivity<ModifyUserNameAction> implements ModifyUserNameView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.tv_user_name_save)
    TextView tvUserNameSave;

    String userName;

    @Override
    public int intiLayout() {
        return R.layout.activity_modify_user_name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected ModifyUserNameAction initAction() {
        return new ModifyUserNameAction(this, this);
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
                .addTag("ModifyUserNameActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.security_tab_1));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
        userName = getIntent().getStringExtra("userName");
        etUserName.setText(userName);
    }


    /**
     * 修改用户名
     *
     * @param name
     */
    @Override
    public void modifyUserName(String name) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.modifyUserName(name);
        }
    }

    /**
     * 修改用户名成功
     * @param msg
     */
    @Override
    public void modifyUserNameSuccess(String msg) {
        loadDiss();
        showNormalToast(msg);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }

    /**
     * 失败
     * @param message
     * @param code
     */
    @Override
    public void onError(String message, int code) {
        loadDiss();
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

    @OnClick(R.id.tv_user_name_save)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_user_name_save:
                save();
                break;
        }
    }

    /**
     * 提交
     */
    private void save() {
        if (TextUtils.isEmpty(etUserName.getText().toString())){
            showNormalToast(ResUtil.getString(R.string.modify_user_name_tab_1));
            return;
        }
        modifyUserName(etUserName.getText().toString());
    }
}
