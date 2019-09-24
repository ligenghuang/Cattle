package com.zhifeng.cattle.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.InviteCodeAction;
import com.zhifeng.cattle.ui.MainActivity;
import com.zhifeng.cattle.ui.impl.InviteCodeView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;
import com.zhifeng.cattle.utils.data.MySp;

import java.lang.ref.WeakReference;

import butterknife.BindView;

/**
 * @ClassName: 填写邀请码
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/24 9:58
 * @Version: 1.0
 */

public class InviteCodeActivity extends UserBaseActivity<InviteCodeAction> implements InviteCodeView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.et_invite_code)
    EditText etInviteCode;

    String token;

    @Override
    public int intiLayout() {
        return R.layout.activity_invite_code;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
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
                .addTag("AgreementActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
        token = getIntent().getStringExtra("token");

        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        etInviteCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_PREVIOUS) {
                    if (TextUtils.isEmpty(etInviteCode.getText().toString())) {
                        showNormalToast(ResUtil.getString(R.string.invite_code_tab_2));
                        return false;
                    }
                    inviteCode(etInviteCode.getText().toString());
                }
                return false;
            }
        });
    }

    @Override
    protected InviteCodeAction initAction() {
        return new InviteCodeAction(this, this);
    }

    /**
     * 填写邀请码
     * @param code
     */
    @Override
    public void inviteCode(String code) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.inviteCode(token, code);
        }
    }

    /**
     * 填写邀请码 成功
     * @param msg
     */
    @Override
    public void inviteCodeSuccess(String msg) {
        loadDiss();
//        showNormalToast(msg);
        MySp.setAccessToken(mContext, token);
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.putExtra("isLogin", true);
        startActivity(intent);
        MainActivity.Position = 0;
        ActivityStack.getInstance().exitIsNotHaveMain(MainActivity.class);
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
}
