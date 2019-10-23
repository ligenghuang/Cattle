package com.zhifeng.cattle.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.FormatUtils;
import com.lgh.huanglib.util.data.ResUtil;
import com.lgh.huanglib.util.data.ValidateUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.LoginAction;
import com.zhifeng.cattle.modules.LoginDto;
import com.zhifeng.cattle.modules.RegisterThirdDto;
import com.zhifeng.cattle.modules.WxLoginDto;
import com.zhifeng.cattle.ui.MainActivity;
import com.zhifeng.cattle.ui.impl.LoginView;
import com.zhifeng.cattle.utils.LoginUtil;
import com.zhifeng.cattle.utils.base.UserBaseActivity;
import com.zhifeng.cattle.utils.config.MyApp;
import com.zhifeng.cattle.utils.data.MySp;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: 登录注册页
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/9 14:39
 * @Version: 1.0
 */

public class LoginActivity extends UserBaseActivity<LoginAction> implements LoginView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.et_login_code)
    EditText etLoginCode;
    @BindView(R.id.tv_login_get_code)
    TextView tvLoginGetCode;
    @BindView(R.id.tv_to_login)
    TextView tvToLogin;
    @BindView(R.id.iv_login_frame)
    ImageView ivLoginFrame;
    @BindView(R.id.iv_login_phone_close)
    ImageView ivLoginPhoneClose;
    @BindView(R.id.iv_login_code_close)
    ImageView ivLoginCodeClose;
    @BindView(R.id.tv_login_agreement)
    TextView tvLoginAgreement;
    @BindView(R.id.iv_login_weixin)
    ImageView ivLoginWeixin;
    @BindView(R.id.iv_login_top)
    ImageView ivLoginTop;


    //获取验证码倒计时
    private MyCountDownTimer timer;

    /**
     * 是否阅读协议
     */
    boolean isReadAgreement = false;

    String phone;

    LoginUtil shareUtil;
    //todo openID
    String wechat;

    @Override
    public int intiLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected LoginAction initAction() {
        return new LoginAction(this, this);
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
                .addTag("LoginActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
        phone = getIntent().getStringExtra("phone");
        etLoginPhone.setText(phone);
        timer = new MyCountDownTimer(60000, 1000);
        shareUtil = new LoginUtil(this);
        shareUtil.register();
        loadView();

    }

    @Override
    protected void initView() {
        super.initView();
        shareUtil.setLoginListener(new LoginUtil.OnLoginResponseListener() {

            @Override
            public void onSuccess(RegisterThirdDto dto) {
                //todo 微信登录
                L.e("lgh_wechat", "打印 ..onSuccess.." + dto.toString());
                wechat = dto.getOpenId();
                if (CheckNetwork.checkNetwork2(getApplicationContext())) {

                    baseAction.wxLogin(wechat, dto.getNickname(), dto.getHeaderImg());
                }
            }

            @Override
            public void onCancel() {
                L.e("lgh_wechat", "打印 ..onCancel..");
                showToast(ResUtil.getString(R.string.app_login_user_tip_9));
                loadDiss();
            }

            @Override
            public void onFail(String message) {
                L.e("lgh_wechat", "打印 ..onFail.." + message);
                showToast(ResUtil.getString(R.string.app_login_user_tip_10));
                loadDiss();
            }
        });
    }

    /**
     * 事件监听
     */
    @Override
    protected void loadView() {
        super.loadView();
        setEditText(etLoginPhone, ivLoginPhoneClose);
        setEditText(etLoginCode, ivLoginCodeClose);

    }

    /**
     * 输入框是否获取焦点
     *
     * @param e
     * @param imageView
     */
    private void setEditText(EditText e, final ImageView imageView) {
        //根据是否有焦点更新背景（这里只是演示使用，其实这种更换背景的效果可以通过Selector来实现）
        e.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    L.e("abc", "et1获取到焦点了。。。。。。");
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    L.e("abc", "et1失去焦点了。。。。。。");
                    imageView.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 获取验证码
     */
    private void getCode() {
        if (TextUtils.isEmpty(etLoginPhone.getText().toString())) {
            //todo 判断是否手机号码为空
            showNormalToast(ResUtil.getString(R.string.login_tab_1));
            return;
        }

        if (!ValidateUtils.isPhone2(etLoginPhone.getText().toString())) {
            //todo 判断手机号格式是否正确
            showNormalToast(ResUtil.getString(R.string.login_tab_8));
            return;
        }

        //todo 判断网络后请求接口
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getCode(etLoginPhone.getText().toString());
        }
    }

    /**
     * 登录或注册
     */
    private void loginOrRegistered() {
        if (TextUtils.isEmpty(etLoginPhone.getText().toString())) {
            //todo 判断是否手机号码为空
            showNormalToast(ResUtil.getString(R.string.login_tab_1));
            return;
        }

        if (!ValidateUtils.isPhone2(etLoginPhone.getText().toString())) {
            //todo 判断手机号格式是否正确
            showNormalToast(ResUtil.getString(R.string.login_tab_8));
            return;
        }

        if (TextUtils.isEmpty(etLoginCode.getText().toString())) {
            //todo 判断验证码是否为空
            showNormalToast(ResUtil.getString(R.string.login_tab_2));
            return;
        }

        if (!isReadAgreement) {
            //todo 判断是否勾选用户协议
            showNormalToast(ResUtil.getString(R.string.login_tab_9));
            return;
        }

        //todo 判断网络后请求接口
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.loginOrRegistered(etLoginPhone.getText().toString(), etLoginCode.getText().toString());
        }
    }

    /**
     * 获取验证码成功
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
     * 登录或注册成功
     *
     * @param loginDto
     */
    @Override
    public void loginOrRegisteredSuccess(LoginDto loginDto) {
        loadDiss();
        //todo 保存登录或注册返回的数据
        L.e("lgh_user", "token  = " + loginDto.getData().getToken());
        if (loginDto.getData().getIs_first() == 1) {
            //todo 第一次登录 跳转至填写邀请码页面
            Intent intent = new Intent(mContext, InviteCodeActivity.class);
            intent.putExtra("token", loginDto.getData().getToken());
            startActivity(intent);
            finish();
        } else {
            MySp.setAccessToken(mContext, loginDto.getData().getToken());
            Intent intent = new Intent(mContext, MainActivity.class);
            intent.putExtra("isLogin", true);
            startActivity(intent);
//        jumpActivity(mContext,MainActivity.class);
            ActivityStack.getInstance().exitIsNotHaveMain(MainActivity.class);
        }
    }

    /**
     * 微信登录
     *
     * @param wxLoginDto
     */
    @Override
    public void wxLoginSuccess(WxLoginDto wxLoginDto) {
        loadDiss();
        //todo 保存登录或注册返回的数据
        L.e("lgh_user", "token  = " + wxLoginDto.getData().getToken());
        if (wxLoginDto.getData().getMobile().equals("0")) {
            //todo 未绑定手机号
        } else if (wxLoginDto.getData().getIs_first() == 1) {
            //todo 第一次登录 跳转至填写邀请码页面
            Intent intent = new Intent(mContext, InviteCodeActivity.class);
            intent.putExtra("token", wxLoginDto.getData().getToken());
            startActivity(intent);
            finish();
        } else {
            //todo 登录成功  跳转至首页
            MySp.setAccessToken(mContext, wxLoginDto.getData().getToken());
            Intent intent = new Intent(mContext, MainActivity.class);
            intent.putExtra("isLogin", true);
            startActivity(intent);
//        jumpActivity(mContext,MainActivity.class);
            ActivityStack.getInstance().exitIsNotHaveMain(MainActivity.class);
        }
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
        L.e("rx", "msg = " + message);
        showNormalToast(message);
        if (timer != null) {
            timer.cancel();
        }
        tvLoginGetCode.setEnabled(true);
        tvLoginGetCode.setSelected(false);
        tvLoginGetCode.setText(R.string.login_tab_3);
    }

    @Override
    protected void onResume() {
        super.onResume();
        baseAction.toRegister();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
        baseAction.toUnregister();
    }

    @Override
    public void finish() {
        super.finish();
        shareUtil.unregister();
    }

    /**
     * 点击事件监听
     *
     * @param view
     */
    @OnClick({R.id.tv_login_get_code, R.id.iv_login_frame
            , R.id.tv_login_agreement, R.id.iv_login_weixin
            , R.id.iv_login_phone_close, R.id.iv_login_code_close, R.id.tv_to_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login_get_code:
                //todo 获取验证码
                getCode();
                break;
            case R.id.tv_to_login:
                //todo 登录或注册
                loginOrRegistered();
                break;
            case R.id.iv_login_frame:
                //todo 是否阅读用户协议
                isReadAgreement = !isReadAgreement;
                ivLoginFrame.setSelected(isReadAgreement);
                break;
            case R.id.tv_login_agreement:
                //todo 阅读用户协议 跳转至用户协议页面
                jumpActivityNotFinish(mContext, AgreementActivity.class);
                break;
            case R.id.iv_login_weixin:
                //todo 微信登录
                if (!MyApp.getWxApi().isWXAppInstalled()) {
                    showToast(ResUtil.getString(R.string.wechat_login));
                    return;
                }
                loadDialog(ResUtil.getString(R.string.main_process));
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
//                req.scope = "snsapi_login";//提示 scope参数错误，或者没有scope权限
                req.state = "xzmall_login";
                MyApp.getWxApi().sendReq(req);
                break;
            case R.id.iv_login_phone_close:
                //todo  删除手机号输入框内容
                if (!TextUtils.isEmpty(etLoginPhone.getText().toString())) {
                    etLoginPhone.setText("");
                }
                break;
            case R.id.iv_login_code_close:
                //todo  删除验证码输入框内容
                if (!TextUtils.isEmpty(etLoginCode.getText().toString())) {
                    etLoginCode.setText("");
                }
                break;
        }
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
            tvLoginGetCode.setEnabled(false);
            tvLoginGetCode.setSelected(true);
            tvLoginGetCode.setText(FormatUtils.format(getString(R.string.login_tab_4), millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            tvLoginGetCode.setEnabled(true);
            tvLoginGetCode.setSelected(false);
            tvLoginGetCode.setText(R.string.login_tab_3);
        }
    }
/*****************************************计时器 end**************************************************/
}
