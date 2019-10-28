package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.enums.PopupPosition;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.RechargeAction;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.modules.RateDto;
import com.zhifeng.cattle.modules.RechargeTypeDto;
import com.zhifeng.cattle.ui.impl.RechargeView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;
import com.zhifeng.cattle.utils.dialog.RechargePwdDialog;
import com.zhifeng.cattle.utils.imageloader.GlideImageLoader;
import com.zhifeng.cattle.utils.photo.PicUtils;
import com.zhifeng.cattle.utils.popup.PayTypePopup;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.ArrayList;

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
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_TAKE = 102;
    public static final int REQUEST_CODE_ALBUM = 103;
    public static int REQUEST_SELECT_TYPE = -1;//选择的类型
    private ArrayList<ImageItem> selImageList = new ArrayList<>(); //当前选择的所有图片
    ArrayList<ImageItem> images = null;
    private int maxImgCount = 1;               //允许选择图片最大数

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_recharge_money)
    EditText etRechargeMoney;
    @BindView(R.id.cv_next)
    CardView cvNext;

    @BindView(R.id.tv_paytype)
    TextView tvPaytype;
    @BindView(R.id.tv_recharge_money_adu)
    TextView tvRechargeMoneyAdu;
    @BindView(R.id.iv_pay)
    ImageView ivPay;
    @BindView(R.id.iv_pay_credentials)
    ImageView ivPayCredentials;

    /**
     * 汇率
     */
    double aus_tormb = 0;//1澳元=？人民币
    double rmb_toaus = 0;//1人民币=？澳元
    /**
     * 微信付款码
     */
    String weixinImg = "";
    /**
     * 支付宝付款码
     */
    String aliImg = "";
    /**
     * 支付凭证
     */
    String payCredentials = "";

    /**
     * 付款类型
     */
    int recharge_type = 0;


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
        initImagePicker();

        loadDialog();
        getRate();
        getRechargeType();

        loadView();
    }

    private void showEt() {
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


    @Override
    protected void loadView() {
        super.loadView();
        etRechargeMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //todo 转换
                if (!TextUtils.isEmpty(etRechargeMoney.getText().toString())){
                    double money = Double.parseDouble(etRechargeMoney.getText().toString());
                    if (money > 0){
                        double AUDMoney = money * rmb_toaus;
                        DecimalFormat df = new DecimalFormat("#0.000");
                        tvRechargeMoneyAdu.setText(df.format(AUDMoney));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 获取付款码
     */
    @Override
    public void getRechargeType() {
        if (CheckNetwork.checkNetwork2(mContext)){
            baseAction.getRechargeType();
        }
    }

    /**
     * 获取付款码成功
     * @param rechargeTypeDto
     */
    @Override
    public void getRechargeTypeSuccess(RechargeTypeDto rechargeTypeDto) {
        loadDiss();
        RechargeTypeDto.DataBean dataBean = rechargeTypeDto.getData();
        weixinImg = dataBean.getWechat_qr_code();
        aliImg = dataBean.getAlipay_qr_code();
        recharge_type = 0;
        tvPaytype.setText(ResUtil.getString(R.string.recharge_tab_14));
        GlideUtil.setImage(mContext,aliImg,ivPay,R.drawable.icon_null_qc);
    }

    /**
     * 获取汇率
     */
    @Override
    public void getRate() {
        if (CheckNetwork.checkNetwork2(mContext)){
            baseAction.getRate();
        }
    }

    /**
     * 获取汇率成功
     * @param rateDto
     */
    @Override
    public void getRateSuccess(RateDto rateDto) {
        RateDto.DataBean dataBean = rateDto.getData();
        rmb_toaus = dataBean.getRmb_toaus();
        aus_tormb = dataBean.getAus_tormb();
    }

    /**
     * 充值
     *
     * @param num
     * @param img
     */
    @Override
    public void recharge(double num, String img) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.recharge(num,recharge_type, img);
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

    }

    @Override
    protected void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }

    @OnClick({R.id.cv_next, R.id.tv_paytype,R.id.iv_pay_credentials})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_next:
                //输入密码
                next();
                break;
            case R.id.tv_paytype:
                //选择支付方式
                new XPopup.Builder(mContext)
                        .hasShadowBg(false)
                        .atView(tvPaytype)
                        .popupPosition(PopupPosition.Bottom)
                        .asCustom(new PayTypePopup(mContext, tvPaytype.getWidth(),new PayTypePopup.OnListClickListener() {

                            @Override
                            public void onAliPay() {
                                //todo 支付宝
                                recharge_type = 0;
                                tvPaytype.setText(ResUtil.getString(R.string.recharge_tab_14));
                                GlideUtil.setImage(mContext,aliImg,ivPay,R.drawable.icon_null_qc);
                            }

                            @Override
                            public void onWatchPay() {
                                //todo 微信
                                recharge_type = 1;
                                tvPaytype.setText(ResUtil.getString(R.string.recharge_tab_15));
                                GlideUtil.setImage(mContext,weixinImg,ivPay,R.drawable.icon_null_qc);
                            }
                        }))
                        .show();
                break;
            case R.id.iv_pay_credentials:
                //todo 选择图片
                if (selImageList.size() != 0){
                    Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, selImageList);
                    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, 0);
                    intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                    startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                }else {
                    takeUserGally();
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
        //todo 判断是否上传支付凭证
        if (TextUtils.isEmpty(payCredentials)){
            showNormalToast(ResUtil.getString(R.string.recharge_tab_16));
            return;
        }

        //请求接口
        recharge(money, payCredentials);
    }


    /**
     * 打开相册
     */
    private void takeUserGally() {
        //打开选择,本次允许选择的数量
        ImagePicker.getInstance().setSelectLimit(1);
        Intent intent1 = new Intent(mContext, ImageGridActivity.class);
        /* 如果需要进入选择的时候显示已经选中的图片，
         * 详情请查看ImagePickerActivity
         * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
        startActivityForResult(intent1, REQUEST_CODE_SELECT);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(false);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setMultiMode(false);
        imagePicker.setSaveRectangle(true);
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.CIRCLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(400);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(400);                         //保存文件的高度。单位像素
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                selImageList = images;
                if (images != null && images.size() != 0) {
                    GlideUtil.setImage(mContext,images.get(0).path,ivPayCredentials);
                    payCredentials = "data:image/gif;base64," + PicUtils.imageToBase64(images.get(0).path);
                }else {
                    payCredentials = "";
                    ivPayCredentials.setImageResource(R.drawable.addpic);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                selImageList = images;
                if (images != null && images.size() != 0) {
                    GlideUtil.setImage(mContext,images.get(0).path,ivPayCredentials);
                    payCredentials = "data:image/gif;base64," + PicUtils.imageToBase64(images.get(0).path);
                }else {
                    payCredentials = "";
                    ivPayCredentials.setImageResource(R.drawable.addpic);
                }
            }
        }
    }


    /**********************************选择图片 end*********************************************/


}
