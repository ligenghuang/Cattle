package com.zhifeng.cattle.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.FormatUtils;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BindMobileAction;
import com.zhifeng.cattle.modules.BindMobileDto;
import com.zhifeng.cattle.ui.MainActivity;
import com.zhifeng.cattle.ui.impl.BindMobileView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;
import com.zhifeng.cattle.utils.data.MySp;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: 微信登录 绑定手机
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/10/24 10:32
 * @Version: 1.0
 */

public class BandMobileActivity extends UserBaseActivity<BindMobileAction> implements BindMobileView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_modify_moblie)
    TextView tvModifyMoblie;
    @BindView(R.id.et_modify_moblie)
    EditText etModifyMoblie;
    @BindView(R.id.et_moblie_code)
    EditText etMoblieCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.tv_next)
    TextView tvNext;

    String token;

    //获取验证码倒计时
    private MyCountDownTimer timer;

    @Override
    public int intiLayout() {
        return R.layout.activity_band_mobile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected BindMobileAction initAction() {
        return new BindMobileAction(this, this);
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
                .addTag("BandMobileActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.bind_mobile_tip_3));
    }


    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        token = getIntent().getStringExtra("token");

        timer = new MyCountDownTimer(60000, 1000);
        loadView();
    }


    /**
     * 获取验证码
     */
    @Override
    public void getCode(String phone) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getCode(phone);
        }
    }

    /**
     * 获取验证码 成功
     *
     * @param msg
     */
    @Override
    public void getCodeSuccess(String msg) {
        loadDiss();
        showNormalToast(msg);
        //todo 启动计时器
        if (timer != null) {
            timer.cancel();
        }
        timer.start();
    }

    /**
     * 绑定手机号
     *
     * @param verify_code
     * @param phone
     */
    @Override
    public void bindMobile(String verify_code, String phone) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.bindPhone(token, verify_code, phone);
        }
    }

    /**
     * 绑定手机号 成功
     *
     * @param bindMobileDto
     */
    @Override
    public void bindMobileSuccess(BindMobileDto bindMobileDto) {
        loadDiss();
        //todo 保存登录或注册返回的数据
        if (bindMobileDto.getData().getIs_first() == 1) {
            //todo 第一次登录 跳转至填写邀请码页面
            Intent intent = new Intent(mContext, InviteCodeActivity.class);
            intent.putExtra("token", token);
            startActivity(intent);
            finish();
        } else {
            MySp.setAccessToken(mContext, token);
            Intent intent = new Intent(mContext, MainActivity.class);
            intent.putExtra("isLogin", true);
            startActivity(intent);
            ActivityStack.getInstance().exitIsNotHaveMain(MainActivity.class);
        }
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
        if (timer != null) {
            timer.cancel();
        }
        tvGetCode.setEnabled(true);
        tvGetCode.setSelected(false);
        tvGetCode.setText(R.string.modify_mobile_tab_12);
    }

    @OnClick({R.id.tv_get_code, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_code:
                //todo 获取验证码
                getPhoneCode();
                break;
            case R.id.tv_next:
                bind();
                break;
        }
    }

    /**
     * 判断是否输入手机号 和 验证码
     */
    private void bind() {
        if (TextUtils.isEmpty(etModifyMoblie.getText().toString())) {
            showNormalToast(ResUtil.getString(R.string.modify_mobile_tab_7));
            return;
        }

        String mobile = etModifyMoblie.getText().toString();

        if (TextUtils.isEmpty(etMoblieCode.getText().toString())) {
            showNormalToast(ResUtil.getString(R.string.modify_mobile_tab_16));
            return;
        }

        String code = etMoblieCode.getText().toString();

        bindMobile(code,mobile);

    }

    /**
     * 判断是否输入手机号
     */
    private void getPhoneCode() {
        if (TextUtils.isEmpty(etModifyMoblie.getText().toString())) {
            showNormalToast(ResUtil.getString(R.string.modify_mobile_tab_7));
            return;
        }

        getCode(etModifyMoblie.getText().toString());
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

    /**************************************计时器 start*******************************************/
    class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub

        }

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            tvGetCode.setEnabled(false);
            tvGetCode.setSelected(true);
            tvGetCode.setText(FormatUtils.format(getString(R.string.modify_mobile_tab_13), millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            tvGetCode.setEnabled(true);
            tvGetCode.setSelected(false);
            tvGetCode.setText(R.string.modify_mobile_tab_12);
        }
    }
/*****************************************计时器 end**************************************************/
}
