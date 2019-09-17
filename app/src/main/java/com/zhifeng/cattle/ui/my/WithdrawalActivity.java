package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.WithdrawalAction;
import com.zhifeng.cattle.modules.BalanceDto;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.ui.impl.WithdrawalView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: 提现
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/17 15:28
 * @Version: 1.0
 */

public class WithdrawalActivity extends UserBaseActivity<WithdrawalAction> implements WithdrawalView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_balance_money)
    TextView tvBalanceMoney;
    @BindView(R.id.tv_alipay_name)
    TextView tvAlipayName;
    @BindView(R.id.et_withdrawal_money)
    EditText etWithdrawalMoney;
    @BindView(R.id.tv_withdrawal_money_all)
    TextView tvWithdrawalMoneyAll;
    @BindView(R.id.tv_withdrawal_taxfee)
    TextView tvWithdrawalTaxfee;
    @BindView(R.id.tv_withdrawal)
    TextView tvWithdrawal;
    @BindView(R.id.tv_withdrawal_actual)
    TextView tvWithdrawalActual;

    double money;
    double rate;


    @Override
    public int intiLayout() {
        return R.layout.activity_withdrawal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected WithdrawalAction initAction() {
        return new WithdrawalAction(this, this);
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
                .addTag("WithdrawalActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.balance_tab_1));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        loadDialog();
        getBalance();
        loadView();
        tvWithdrawalTaxfee.setText(ResUtil.getFormatString(R.string.withdrawal_tab_6,"0"));//手续费
        tvWithdrawalActual.setText(ResUtil.getFormatString(R.string.withdrawal_tab_7,"0"));//实际到账
    }

    @Override
    protected void loadView() {
        super.loadView();
        etWithdrawalMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //计算手续费和实际到账金额
                double taxfee = 0;
                double actual = 0;
                if (!TextUtils.isEmpty(etWithdrawalMoney.getText().toString())){
                    double money = Double.valueOf(etWithdrawalMoney.getText().toString());
                    taxfee = money * rate;
                    actual = money - taxfee;
                }

                tvWithdrawalTaxfee.setText(ResUtil.getFormatString(R.string.withdrawal_tab_6,taxfee+""));//手续费
                tvWithdrawalActual.setText(ResUtil.getFormatString(R.string.withdrawal_tab_7,actual+""));//实际到账
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 获取提现余额
     */
    @Override
    public void getBalance() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getBalance();
        } else {
            loadDiss();
        }
    }

    /**
     * 获取余额成功
     *
     * @param balanceDto
     */
    @Override
    public void getBalanceSuccess(BalanceDto balanceDto) {
        loadDiss();
        money = Double.parseDouble(balanceDto.getData().getRemainder_money());
        DecimalFormat df = new DecimalFormat("#0.00000");
        tvBalanceMoney.setText("￥" + df.format(money));
        tvAlipayName.setText(balanceDto.getData().getAlipay());
        rate = balanceDto.getData().getRate();
    }

    /**
     * 提现
     *
     * @param money
     */
    @Override
    public void withdrawal(double money) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.withdrawal(money);
        }
    }

    /**
     * 提现成功
     *
     * @param generalDto
     */
    @Override
    public void withdrawalSuccess(GeneralDto generalDto) {
        loadDiss();
        showNormalToast(generalDto.getMsg());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }

    /**
     * 失败
     *
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
        if (baseAction != null) {
            baseAction.toRegister();
            getBalance();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }

    @OnClick({R.id.tv_alipay_name, R.id.tv_withdrawal, R.id.tv_withdrawal_money_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_alipay_name:
                //todo 绑定支付宝
                jumpActivityNotFinish(mContext,EditAliPayActivity.class);
                break;
            case R.id.tv_withdrawal:
                //todo 提现
                Withdrawal();
                break;
            case R.id.tv_withdrawal_money_all:
                //todo 全部提现
                etWithdrawalMoney.setText(money + "");
                break;
        }
    }

    /**
     * 提现
     */
    private void Withdrawal() {
        //判断是否输入提现金额
        if (TextUtils.isEmpty(etWithdrawalMoney.getText().toString())) {
            showNormalToast(ResUtil.getString(R.string.withdrawal_tab_4));
            return;
        }

        double Money = Double.valueOf(etWithdrawalMoney.getText().toString());
        //判断输入金额是否大于余额或小于0
        if (Money <= 0) {
            showNormalToast(ResUtil.getString(R.string.withdrawal_tab_10));
            return;
        } else if (Money > money) {
            showNormalToast(ResUtil.getString(R.string.withdrawal_tab_11));
            return;
        }

        withdrawal(Double.valueOf(etWithdrawalMoney.getText().toString()));
    }
}
