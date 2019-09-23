package com.zhifeng.cattle.ui.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.lgh.huanglib.util.data.ValidateUtils;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.SupplierAction;
import com.zhifeng.cattle.modules.Supplier;
import com.zhifeng.cattle.ui.impl.SupplierView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: 供应商
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/16 15:32
 * @Version: 1.0
 */

public class SupplierActivity extends UserBaseActivity<SupplierAction> implements SupplierView {
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_supplier_name)
    EditText etSupplierName;
    @BindView(R.id.et_supplier_phone)
    EditText etSupplierPhone;

    @Override
    public int intiLayout() {
        return R.layout.activity_supplier;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    protected SupplierAction initAction() {
        return new SupplierAction(this, this);
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
                .addTag("SupplierActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.my_tab_20));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
    }

    @Override
    public void postSupplier() {
        String name = etSupplierName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showNormalToast(R.string.supplier_tab_5);
            return;
        }
        String phone = etSupplierPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            showNormalToast(R.string.supplier_tab_4);
            return;
        } else if (!ValidateUtils.isPhone2(phone)) {
            showNormalToast(R.string.supplier_tab_6);
            return;
        }
        baseAction.postSupplier(name, phone);
    }

    @Override
    public void postSupplierSuccess(Supplier supplier) {
        showNormalToast(R.string.supplier_tab_7);
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

    @OnClick(R.id.tv_supplier_save)
    public void onClick(View view) {
        postSupplier();
    }
}
