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
import com.zhifeng.cattle.actions.EditAliPayAction;
import com.zhifeng.cattle.modules.AliPayDto;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.ui.impl.EditAliPayView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: 填写支付宝
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/17 17:03
 * @Version: 1.0
 */

public class EditAliPayActivity extends UserBaseActivity<EditAliPayAction> implements EditAliPayView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_alipay)
    EditText etAlipay;
    @BindView(R.id.et_alipay_name)
    EditText etAlipayName;
    int type;

    @Override
    public int intiLayout() {
        return R.layout.activity_edit_ali_pay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected EditAliPayAction initAction() {
        return new EditAliPayAction(this, this);
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
                .addTag("EditAliPayActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.edit_alipay_tab_1));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        type = getIntent().getIntExtra("type", 0);
    }

    /**
     * 填写支付宝
     *
     * @param alipay_name
     * @param alipay
     */
    @Override
    public void editAlipay(String alipay_name, String alipay) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.editAlipay(alipay_name, alipay);
        }
    }

    /**
     * 填写支付宝 成功
     *
     * @param generalDto
     */
    @Override
    public void editAlipaySuccess(AliPayDto generalDto) {
        loadDiss();
        showNormalToast(generalDto.getMsg());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (type == 1) {
                    jumpActivity(mContext, WithdrawalActivity.class);
                } else {
                    finish();
                }
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

    @OnClick(R.id.tv_save)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_save:
                //保存
                save();
                break;
        }
    }

    /**
     * 保存
     */
    private void save() {
        //判断是否输入支付宝账号
        if (TextUtils.isEmpty(etAlipay.getText().toString())){
            showNormalToast(ResUtil.getString(R.string.edit_alipay_tab_3));
            return;
        }

        //判断是否输入姓名
        if (TextUtils.isEmpty(etAlipayName.getText().toString())){
            showNormalToast(ResUtil.getString(R.string.edit_alipay_tab_5));
            return;
        }

        editAlipay(etAlipayName.getText().toString(),etAlipay.getText().toString());
    }
}
