package com.zhifeng.cattle.ui.my;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.zhifeng.cattle.BuildConfig;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.OrderCommentAction;
import com.zhifeng.cattle.adapters.ImagePickerAdapter;
import com.zhifeng.cattle.modules.OrderComment;
import com.zhifeng.cattle.modules.OrderCommentResult;
import com.zhifeng.cattle.ui.impl.OrderCommentView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;
import com.zhifeng.cattle.utils.data.MySp;
import com.zhifeng.cattle.utils.dialog.PicturesDialog;
import com.zhifeng.cattle.utils.imageloader.GlideImageLoader;
import com.zhifeng.cattle.utils.photo.PicUtils;
import com.zhifeng.cattle.utils.view.FlowLayout;

import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName:
 * @Description: 商品评价
 * @Author: Administrator
 * @Date: 2019/9/19 16:41
 */
public class OrderCommentActivity extends UserBaseActivity<OrderCommentAction> implements OrderCommentView, ImagePickerAdapter.OnRecyclerViewItemClickListener {
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_TAKE = 102;
    public static final int REQUEST_CODE_ALBUM = 103;
    public static int REQUEST_SELECT_TYPE = -1;//选择的类型
    private ArrayList<ImageItem> selImageList = new ArrayList<>(); //当前选择的所有图片
    ArrayList<ImageItem> images = null;
    private int maxImgCount = 9;               //允许选择图片最大数
    private ImagePickerAdapter adapter;

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etContent)
    EditText etContent;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.describeRatingBar)
    RatingBar describeRatingBar;
    @BindView(R.id.logisticsRatingBar)
    RatingBar logisticsRatingBar;
    @BindView(R.id.serviceRatingBar)
    RatingBar serviceRatingBar;
    private String order_id;
    private String goods_id;
    private String sku_id;
    private int describe = 1;
    private int logistics = 1;
    private int service = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_ordercomment;
    }

    @Override
    protected OrderCommentAction initAction() {
        return new OrderCommentAction(this, this);
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        order_id = String.valueOf(getIntent().getIntExtra("order_id",0));
        goods_id = getIntent().getStringExtra("goods_id");
        sku_id = getIntent().getStringExtra("sku_id");

        initImagePicker();
        initWidget();
        loadView();
    }

    @Override
    protected void loadView() {
        describeRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                L.e("lghl", "rating   = " + rating);
                describe = (int) Math.ceil(rating);//获取多少星星
                describeRatingBar.setRating(rating);
            }
        });
        logisticsRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                L.e("lghl", "rating   = " + rating);
                logistics = (int) Math.ceil(rating);//获取多少星星
            }
        });
        serviceRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                L.e("lghl", "rating   = " + rating);
                service = (int) Math.ceil(rating);//获取多少星星
            }
        });
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
                .addTag("OrderCommentActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.order_comment_tab_1));
    }

    private void postComment() {
        String content = etContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            showNormalToast("请输入评价内容");
            return;
        }

       List<String> str = new ArrayList<>();
        for (int i = 0; i <selImageList.size() ; i++) {
            str.add("data:image/gif;base64,"+PicUtils.imageToBase64(selImageList.get(i).path));
        }

        OrderComment orderComment = new OrderComment(Integer.parseInt(order_id), Integer.parseInt(goods_id), Integer.parseInt(sku_id), describe+"", logistics+"", service+"", content, str);
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.postComment(orderComment);
        }
        L.e("lgh_json",orderComment.toString());
    }

    /**
     * 提交评价成功
     */
    @Override
    public void postCommentSuccess() {
        loadDiss();
        showNormalToast(ResUtil.getString(R.string.order_tab_37));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
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

    @OnClick(R.id.btnPost)
    public void onClick(View view) {
        postComment();
    }


    private void initWidget() {
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
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

    private void takePhoto() {
        // 直接调起相机
        /**
         * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
         *
         * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
         *
         * 如果实在有所需要，请直接下载源码引用。
         */
        //打开选择,本次允许选择的数量
        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
        Intent intent = new Intent(mContext, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intent, REQUEST_CODE_SELECT);
    }

    /**
     * 打开相册
     */
    private void takeUserGally() {
        //打开选择,本次允许选择的数量
        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
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
        imagePicker.setMultiMode(true);
        imagePicker.setSaveRectangle(true);
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
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
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                //添加图片
                showSelectDiaLog();
                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    /**********************************选择图片 end*********************************************/

}
