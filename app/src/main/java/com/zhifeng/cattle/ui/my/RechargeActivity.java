package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.RechargeAction;
import com.zhifeng.cattle.modules.BankBto;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.ui.impl.RechargeView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;
import com.zhifeng.cattle.utils.dialog.RechargePwdDialog;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: 充值
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/20 10:28
 * @Version: 1.0
 */

public class RechargeActivity extends UserBaseActivity<RechargeAction> implements RechargeView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_recharge_bank)
    TextView tvRechargeBank;
    @BindView(R.id.tv_recharge_limit)
    TextView tvRechargeLimit;
    @BindView(R.id.et_recharge_money)
    EditText etRechargeMoney;
    @BindView(R.id.cv_next)
    CardView cvNext;

    @Override
    public int intiLayout() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected RechargeAction initAction() {
        return new RechargeAction(this, this);
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
                .addTag("RechargeActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.recharge_tab_1));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        new Thread(new Runnable() {

            public void run() {
                InputMethodManager imm = (InputMethodManager) mActicity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etRechargeMoney, InputMethodManager.RESULT_SHOWN);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        }).start();

        //todo 2019/09/20 12:00 获取银行卡接口暂无 先注释
//        getBank();
//        loadView();
    }

    /**
     * 获取银行卡
     */
    @Override
    public void getBank() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getBank();
        }
    }

    /**
     * 获取银行卡 成功
     *
     * @param bankBto
     */
    @Override
    public void getBankSucces(BankBto bankBto) {
        loadDiss();
        BankBto.DataBean dataBean = bankBto.getData();
        String card =dataBean.getBank_card();
        tvRechargeBank.setText(dataBean.getBank_name()+"("+card.substring(card.length()-5,card.length())+")");
    }

    /**
     * 充值
     * @param num
     * @param pwd
     */
    @Override
    public void recharge(double num, String pwd) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.recharge(num,pwd);
        }
    }

    /**
     * 充值成功
     * @param generalDto
     */
    @Override
    public void rechargeSuccess(GeneralDto generalDto) {
        loadDiss();
        showNormalToast(generalDto.getMsg());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {  finish();
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

    @OnClick(R.id.cv_next)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.cv_next:
                next();
                break;
        }
    }

    /**
     * 下一步
     */
    private void next() {
        //todo 判断是否输入充值金额
        if (TextUtils.isEmpty(etRechargeMoney.getText().toString())){
            showNormalToast(ResUtil.getString(R.string.recharge_tab_8));
            return;
        }
        double money = Double.parseDouble(etRechargeMoney.getText().toString());
        //todo 判断输入金额是否小于0
        if (money <= 0){
            showNormalToast(ResUtil.getString(R.string.recharge_tab_9));
            return;
        }

        RechargePwdDialog rechargePwdDialog = new RechargePwdDialog(mContext,R.style.MY_AlertDialog,money);
        rechargePwdDialog.setOnFinishInput(new RechargePwdDialog.OnFinishInput() {
            @Override
            public void inputFinish(String password) {
                //请求接口
                recharge(money,password);
            }
        });
        rechargePwdDialog.show();
    }
}
