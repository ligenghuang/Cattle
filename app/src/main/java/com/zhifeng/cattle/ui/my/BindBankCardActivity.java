package com.zhifeng.cattle.ui.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.lgh.huanglib.util.data.ValidateUtils;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BindBankCardAction;
import com.zhifeng.cattle.modules.BindBankCardDto;
import com.zhifeng.cattle.ui.impl.BindBankCardView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * @ClassName:
 * @Description: 绑定银行卡
 * @Author: Administrator
 * @Date: 2019/9/27 14:59
 */
public class BindBankCardActivity extends UserBaseActivity<BindBankCardAction> implements BindBankCardView {
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_bankName)
    EditText et_bankName;
    @BindView(R.id.et_bankCard)
    EditText etBankCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_bind_bank_card;
    }

    @Override
    protected BindBankCardAction initAction() {
        return new BindBankCardAction(this, this);
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
                .addTag("BindBankCardActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.boundbank_tab1));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
    }

    @Override
    public void bindBankCard() {
        String bankName = et_bankName.getText().toString();
        if (TextUtils.isEmpty(bankName)) {
            showNormalToast(ResUtil.getString(R.string.boundbank_tab3));
            return;
        }
        String bankCard = etBankCard.getText().toString();
        if (TextUtils.isEmpty(bankCard)) {
            showNormalToast(ResUtil.getString(R.string.boundbank_tab5));
            return;
        }
        if (!ValidateUtils.isBankNo(bankCard)) {
            showNormalToast(ResUtil.getString(R.string.boundbank_tab6));
            return;
        }
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.bindBankCard(bankName, Long.parseLong(bankCard));
        }
    }

    @Override
    public void bindBankCardSuccess(BindBankCardDto bindBankCardDto) {
        showNormalToast(bindBankCardDto.getMsg());
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

    @OnClick(R.id.tv_save)
    public void onClick(View view) {
        bindBankCard();
    }
}
