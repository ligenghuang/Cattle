package com.zhifeng.cattle.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.lgh.huanglib.util.data.ValidateUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.CertificationAction;
import com.zhifeng.cattle.modules.CertificationDto;
import com.zhifeng.cattle.modules.post.CertificationPost;
import com.zhifeng.cattle.ui.impl.CertificationView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;
import com.zhifeng.cattle.utils.dialog.PicturesDialog;
import com.zhifeng.cattle.utils.imageloader.GlideImageLoader;
import com.zhifeng.cattle.utils.photo.PicUtils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: 身份认证
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/20 12:28
 * @Version: 1.0
 */

public class CertificationActivity extends UserBaseActivity<CertificationAction> implements CertificationView {

    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    public static final int REQUEST_CODE_TAKE = 102;
    public static final int REQUEST_CODE_ALBUM = 103;
    public static int REQUEST_SELECT_TYPE = -1;//选择的类型
    private ArrayList<ImageItem> selImageList1 = new ArrayList<>(); //当前选择的所有图片
    ArrayList<ImageItem> images1 = null;
    private ArrayList<ImageItem> selImageList2 = new ArrayList<>(); //当前选择的所有图片
    ArrayList<ImageItem> images2 = null;
    boolean isFront = true;//判断是否为正面

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_certification_name)
    EditText etCertificationName;
    @BindView(R.id.et_certification_card)
    EditText etCertificationCard;
    @BindView(R.id.iv_add_positive)
    ImageView ivAddPositive;
    @BindView(R.id.ll_add_positive)
    LinearLayout llAddPositive;
    @BindView(R.id.rl_add_positive)
    RelativeLayout rlAddPositive;
    @BindView(R.id.iv_add_back)
    ImageView ivAddBack;
    @BindView(R.id.ll_add_back)
    LinearLayout llAddBack;
    @BindView(R.id.rl_add_back)
    RelativeLayout rlAddBack;
    @BindView(R.id.tv_save)
    TextView tvSave;

    String pic_front;
    String pic_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
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

    @Override
    public int intiLayout() {
        return R.layout.activity_certification;
    }

    @Override
    protected CertificationAction initAction() {
        return new CertificationAction(this, this);
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
                .addTag("CertificationActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.certification_tab_10));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
        initImagePicker();
    }

    /**
     * 身份认证
     *
     * @param certificationPost
     */
    @Override
    public void certification(CertificationPost certificationPost) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.certification(certificationPost);
        }
    }

    /**
     * 身份认证传成功
     *
     * @param certificationDto
     */
    @Override
    public void certificationSuccess(CertificationDto certificationDto) {
        loadDiss();
        showNormalToast(certificationDto.getMsg());
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

    @OnClick({R.id.rl_add_positive, R.id.rl_add_back, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_add_positive:
                //todo 添加身份证正面
                isFront = true;
                showSelectDiaLog();
                break;
            case R.id.rl_add_back:
                //todo 添加身份证背面
                isFront = false;
                showSelectDiaLog();
                break;
            case R.id.tv_save:
                //todo  提交
                save();
                break;
        }
    }

    /**
     * 提交
     */
    private void save() {
        //todo 判断是否输入姓名
        if (TextUtils.isEmpty(etCertificationName.getText().toString())) {
            showNormalToast(ResUtil.getString(R.string.certification_tab_2));
            return;
        }

        String name = etCertificationName.getText().toString();

        //todo 判断是否输入身份证号
        if (TextUtils.isEmpty(etCertificationCard.getText().toString())) {
            showNormalToast(ResUtil.getString(R.string.certification_tab_4));
            return;
        }

        //todo 判断输入的身份证号格式是否正确
        if (!ValidateUtils.isIDCard(etCertificationCard.getText().toString())) {
            showNormalToast(ResUtil.getString(R.string.certification_tab_12));
            return;
        }
        String idcard = etCertificationCard.getText().toString();

        //todo 判断是否上传身份证
        if (TextUtils.isEmpty(pic_front) || TextUtils.isEmpty(pic_back)) {
            showNormalToast(ResUtil.getString(R.string.certification_tab_11));
            return;
        }

        CertificationPost post = new CertificationPost(pic_front, pic_back, name, idcard);
        certification(post);

    }


    /**
     * 选择图片
     */
    public void showSelectDiaLog() {
        PicturesDialog dialog = new PicturesDialog(this, R.style.MY_AlertDialog);
        dialog.setOnClickListener(new PicturesDialog.OnClickListener() {
            @Override
            public void onCamera() {
                takePhoto();
            }

            @Override
            public void onPhoto() {
                takeUserGally();
            }
        });
        dialog.show();
    }

    /**
     * 相机
     */
    private void takePhoto() {
        /**
         * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
         *
         * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
         *
         * 如果实在有所需要，请直接下载源码引用。
         */
        REQUEST_SELECT_TYPE = REQUEST_CODE_TAKE;
        //打开选择,本次允许选择的数量
        ImagePicker.getInstance().setSelectLimit(1);
        Intent intent = new Intent(mContext, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intent, REQUEST_CODE_SELECT);
    }

    /**
     * 打开相册
     */
    private void takeUserGally() {
        //打开选择,本次允许选择的数量

        REQUEST_SELECT_TYPE = REQUEST_CODE_ALBUM;
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
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setMultiMode(false);
        imagePicker.setSaveRectangle(true);
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
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
                if (isFront) {
                    //todo 正面
                    images1 = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    if (images1 != null) {
                        GlideUtil.setImage(mContext, images1.get(0).path, ivAddPositive, R.drawable.icon_add_idcard_positive);
                        llAddPositive.setVisibility(View.GONE);
                        pic_front = "data:image/gif;base64," + PicUtils.imageToBase64(images1.get(0).path);
                        L.e("lgh_e", pic_front);
                    }
                } else {
                    //todo 背面
                    images2 = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    if (images2 != null) {
                        GlideUtil.setImage(mContext, images2.get(0).path, ivAddBack, R.drawable.icon_add_idcard_back);
                        llAddBack.setVisibility(View.GONE);
                        pic_back = "data:image/gif;base64," + PicUtils.imageToBase64(images2.get(0).path);
                    }
                }
            }
        }

    }
}
