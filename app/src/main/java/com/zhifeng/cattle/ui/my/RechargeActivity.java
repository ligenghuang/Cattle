package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.RechargeAction;
import com.zhifeng.cattle.modules.BankImgListDto;
import com.zhifeng.cattle.modules.BankListDto;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.ui.impl.RechargeView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;
import com.zhifeng.cattle.utils.dialog.BankListDialog;
import com.zhifeng.cattle.utils.dialog.RechargePwdDialog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.tv_no_bank)
    TextView tvNoBank;
    @BindView(R.id.rl_bank)
    RelativeLayout rlBank;

    List<BankListDto.DataBean> list = new ArrayList<>();
    List<BankImgListDto.DataBean> dataBeanList = new ArrayList<>();
    String bankCard;
    boolean isBindBank = false;
    boolean isNext = false;

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


    }

    private void showEt(){
        etRechargeMoney.setFocusable(true);
        etRechargeMoney.setFocusableInTouchMode(true);
        etRechargeMoney.requestFocus();

        new Thread(new Runnable() {

            public void run() {
                InputMethodManager imm = (InputMethodManager) mActicity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etRechargeMoney, InputMethodManager.RESULT_SHOWN);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        }).start();
    }

    /**
     * 获取已绑定银行卡列表
     */
    @Override
    public void getBankList() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getBankList();
        }
    }

    /**
     * 获取已绑定银行卡列表 成功
     *
     * @param bankListDto
     */
    @Override
    public void getBankListSuccess(BankListDto bankListDto) {
        isBindBank = bankListDto.getData().size() != 0;
        getBankImgList();
        if (bankListDto.getData().size() != 0) {
            list = bankListDto.getData();
            list.get(0).setClick(true);
            bankCard = list.get(0).getBank_card();
            String num = list.get(0).getBank_card().substring(bankCard.length() - 4, bankCard.length());
            String name = list.get(0).getBank_name() + "(" + num + ")";
            tvRechargeBank.setText(name);
            rlBank.setVisibility(View.VISIBLE);
            tvNoBank.setVisibility(View.GONE);
        } else {
            rlBank.setVisibility(View.GONE);
            tvNoBank.setVisibility(View.VISIBLE);
        }
        showEt();
    }

    /**
     * 获取银行图标
     */
    @Override
    public void getBankImgList() {
        baseAction.getBankImgList();
    }

    /**
     * 获取银行图标 成功
     *
     * @param bankListDto
     */
    @Override
    public void getBankImgListSuccess(BankImgListDto bankListDto) {
        loadDiss();
        dataBeanList = bankListDto.getData();
    }

    /**
     * 充值
     *
     * @param num
     * @param pwd
     */
    @Override
    public void recharge(double num, String pwd) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.recharge(num, pwd);
        }
    }

    /**
     * 充值成功
     *
     * @param generalDto
     */
    @Override
    public void rechargeSuccess(GeneralDto generalDto) {
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
        baseAction.toRegister();
        if (!isNext){
            getBankList();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }

    @OnClick({R.id.cv_next, R.id.ll_bank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_next:
                next();
                break;
            case R.id.ll_bank:
                hideInput();
                if (isBindBank){
                //选择银行卡
                BankListDialog bankListDialog = new BankListDialog(mContext, R.style.MY_AlertDialog, list, dataBeanList);
                bankListDialog.setOnClickListener(new BankListDialog.OnClickListener() {
                    @Override
                    public void OnClick(List<BankListDto.DataBean> BankList, BankListDto.DataBean model) {
                        bankCard = model.getBank_card();
                        String num = model.getBank_card().substring(bankCard.length() - 4, bankCard.length());
                        String name = model.getBank_name() + "(" + num + ")";
                        tvRechargeBank.setText(name);
                        rlBank.setVisibility(View.VISIBLE);
                        tvNoBank.setVisibility(View.GONE);
                        list = BankList;
                        showEt();
                    }
                });
                bankListDialog.show();

                }else {
                    //绑定银行卡 跳转至绑定银行卡页面
                    jumpActivityNotFinish(mContext, BindBankCardActivity.class);
                }
                break;
        }
    }

    /**
     * 下一步
     */
    private void next() {
        //todo 判断是否输入充值金额
        if (TextUtils.isEmpty(etRechargeMoney.getText().toString())) {
            showNormalToast(ResUtil.getString(R.string.recharge_tab_8));
            return;
        }
        double money = Double.parseDouble(etRechargeMoney.getText().toString());
        //todo 判断输入金额是否小于0
        if (money <= 0) {
            showNormalToast(ResUtil.getString(R.string.recharge_tab_9));
            return;
        }
        isNext = true;
        RechargePwdDialog rechargePwdDialog = new RechargePwdDialog(mContext, R.style.MY_AlertDialog, money);
        rechargePwdDialog.setOnFinishInput(new RechargePwdDialog.OnFinishInput() {
            @Override
            public void inputFinish(String password) {
                //请求接口
                recharge(money, password);
            }
        });
        hideInput();
        rechargePwdDialog.show();
    }
}
