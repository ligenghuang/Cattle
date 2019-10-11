package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.MyAction;
import com.zhifeng.cattle.modules.LoginUser;
import com.zhifeng.cattle.modules.UserInfoDto;
import com.zhifeng.cattle.ui.MainActivity;
import com.zhifeng.cattle.ui.impl.MyView;
import com.zhifeng.cattle.ui.login.LoginActivity;
import com.zhifeng.cattle.utils.base.UserBaseFragment;
import com.zhifeng.cattle.utils.data.MySp;
import com.zhifeng.cattle.utils.dialog.PicturesDialog;
import com.zhifeng.cattle.utils.imageloader.GlideImageLoader;
import com.zhifeng.cattle.utils.photo.PicUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName: 我的fragment
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/9 16:58
 * @Version: 1.0
 */

public class MyFragment extends UserBaseFragment<MyAction> implements MyView {
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_TAKE = 102;
    public static final int REQUEST_CODE_ALBUM = 103;
    public static int REQUEST_SELECT_TYPE = -1;//选择的类型
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ArrayList<ImageItem> selImageList = new ArrayList<>(); //当前选择的所有图片
    ArrayList<ImageItem> images = null;
    private int maxImgCount = 1;               //允许选择图片最大数

    View view;
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.iv_my_avatar)
    ImageView ivMyAvatar;
    @BindView(R.id.tv_my_name)
    TextView tvMyName;
    @BindView(R.id.tv_my_id)
    TextView tvMyId;
    @BindView(R.id.tv_my_level)
    TextView tvMyLevel;
    @BindView(R.id.tv_my_remainder_money)
    TextView tvMyRemainderMoney;
    @BindView(R.id.tv_my_collection)
    TextView tvMyCollection;
    @BindView(R.id.tv_bonus_day)
    TextView tvBonusDay;
    @BindView(R.id.tv_bonus_month)
    TextView tvBonusMonth;
    @BindView(R.id.tv_total_results)
    TextView tvTotalResults;
    @BindView(R.id.tv_headcount)
    TextView tvHeadcount;
    @BindView(R.id.tv_recommended)
    TextView tvRecommended;

    @Override
    protected MyAction initAction() {
        return new MyAction((RxAppCompatActivity) getActivity(), this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getContext();
        mActivity = activity;
    }

    @Override
    protected void initialize() {
        init();
        loadView();
    }

    @Override
    protected void init() {
        super.init();
        refreshLayout.setEnableLoadMore(false);

        initImagePicker();
    }

    @Override
    protected void loadView() {
        super.loadView();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getUserInfo();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        binding();
        mImmersionBar.setStatusBarView(getActivity(), topView);
        return view;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
            getUserInfo();
        }
    }

    /**
     * 获取用户信息
     */
    @Override
    public void getUserInfo() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            baseAction.getUserInfo();
        }
    }

    /**
     * 获取用户信息成功
     *
     * @param userInfoDto
     */
    @Override
    public void getUserInfoSuccess(UserInfoDto userInfoDto) {
        loadDiss();
        refreshLayout.finishRefresh();
        UserInfoDto.DataBean dataBean = userInfoDto.getData();
        GlideUtil.setImageCircle(mContext, dataBean.getAvatar(), ivMyAvatar, R.mipmap.logo);//头像
        tvMyName.setText(dataBean.getRealname());//昵称
        tvMyLevel.setText(dataBean.getLevelname());//等级
        tvMyId.setText("ID:" + dataBean.getId() + "");//id

        double money = Double.parseDouble(dataBean.getRemainder_money());
        DecimalFormat df = new DecimalFormat("#0.00");
        tvMyRemainderMoney.setText(df.format(money));//提现余额
        tvMyCollection.setText(dataBean.getCollection() + "");//关注

        BigDecimal bigDecimal = new BigDecimal(dataBean.getDay());
        BigDecimal bigDecimal2 = new BigDecimal(dataBean.getMonth());
        tvBonusDay.setText(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP) + "");//当日奖金
        tvBonusMonth.setText(bigDecimal2.setScale(2, BigDecimal.ROUND_HALF_UP) + "");//当月奖金

        tvTotalResults.setText(dataBean.getDistribut_money());//总业绩
        tvHeadcount.setText(dataBean.getTeam_count() + "");//总人数
        tvRecommended.setText(dataBean.getToday_rec() + "");//今日推荐

        if (MainActivity.isLogin) {
            String json = MySp.getUserList(mContext);
            List<LoginUser> list = new ArrayList<>();
            if (!TextUtils.isEmpty(json)) {
                list = new Gson().fromJson(json, new TypeToken<List<LoginUser>>() {
                }.getType());
            }
            LoginUser userDto = new LoginUser();
            userDto.setAvatar(dataBean.getAvatar());
            userDto.setRealname(dataBean.getRealname());
            userDto.setMobile(dataBean.getMobile());
            userDto.setToken(MySp.getAccessToken(mContext));
            L.e("lgh_user", "token  = " + MySp.getAccessToken(mContext));

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getMobile().equals(userDto.getMobile())) {
                    list.set(i, userDto);
                    L.e("lgh_user", "user  = " + userDto.getToken());
                    MySp.setUserList(mContext, new Gson().toJson(list));
                    MainActivity.isLogin = false;
                    return;
                }
            }
            if (list.size() >= 3) {
                list.set(0, userDto);
            } else {
                list.add(userDto);
            }


            MySp.setUserList(mContext, new Gson().toJson(list));
            MainActivity.isLogin = false;
        }
    }

    /**
     * 修改头像
     *
     * @param path
     */
    @Override
    public void updataAvatar(String path) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.updataAvatar(path);
        }
    }

    /**
     * 修改头像成功
     *
     * @param url
     */
    @Override
    public void updataAvatarSuccess(String url) {
        loadDiss();
        GlideUtil.setImageCircle(mContext, url, ivMyAvatar, R.mipmap.logo);
        images = new ArrayList<>();
        selImageList = new ArrayList<>();
    }

    /**
     * token过期
     */
    @Override
    public void onLoginNo() {
        loadDiss();
        showNormalToast("登录过期，请重新登录！");
        MainActivity.Position = 0;
        MySp.clearAllSP(mContext);
        jumpActivityNotFinish(mContext, LoginActivity.class);
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
        refreshLayout.finishRefresh();
        showNormalToast(message);
    }

    @Override
    public void onResume() {
        super.onResume();
        baseAction.toRegister();
        getUserInfo();
    }

    @Override
    public void onPause() {
        super.onPause();
        baseAction.toUnregister();

    }

    @OnClick({R.id.ll_my_remainder_money, R.id.ll_my_collection, R.id.ll_my_order,
            R.id.tv_wait_pay, R.id.tv_wait_order, R.id.tv_wait_receive,
            R.id.tv_wait_evaluation, R.id.tv_sales_return, R.id.ll_my_team,
            R.id.ll_total_results, R.id.ll_headcount, R.id.ll_recommended,
            R.id.tv_invitation, R.id.tv_address, R.id.tv_supplier, R.id.tv_security,
            R.id.ll_bonus_day, R.id.ll_bonus_month, R.id.tv_ranking_list, R.id.iv_my_avatar, R.id.tv_my_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_remainder_money:
                //todo 提现余额
                jumpActivityNotFinish(mContext, BalanceActivity.class);
                break;
            case R.id.ll_my_collection:
                //todo 关注
                jumpActivityNotFinish(mContext, CollectionActivity.class);
                break;
            case R.id.ll_my_order:
                //todo 我的订单
                jumpOrderActivity(0);
                break;
            case R.id.tv_wait_pay:
                //todo 待付款
                jumpOrderActivity(1);
                break;
            case R.id.tv_wait_order:
                //todo 待发货
                jumpOrderActivity(2);
                break;
            case R.id.tv_wait_receive:
                //todo 待收货
                jumpOrderActivity(3);
                break;
            case R.id.tv_wait_evaluation:
                //todo 待评价
                jumpOrderActivity(4);
                break;
            case R.id.tv_sales_return:
                //todo 退货
                jumpActivityNotFinish(mContext, SalesReturnActivity.class);
                break;
            case R.id.ll_bonus_day:
                //todo 当日累计奖金
                jumpActivityNotFinish(mContext, BonusDayActivity.class);
                break;
            case R.id.tv_ranking_list:
                //todo 排行榜
                jumpActivityNotFinish(mContext, RankingListActivity.class);
                break;
            case R.id.ll_bonus_month:
                //todo 当月累计奖金
                jumpActivityNotFinish(mContext, BonusMonActivity.class);
                break;
            case R.id.ll_my_team:
            case R.id.ll_total_results:
            case R.id.ll_headcount:
            case R.id.ll_recommended:
                //todo 我的团队
                jumpActivityNotFinish(mContext, MyTeamActivity.class);
                break;
            case R.id.tv_invitation:
                //todo 邀请分享
                jumpActivityNotFinish(mContext, InvitationActivity.class);
                break;
            case R.id.tv_address:
                //todo 地址管理
                jumpActivityNotFinish(mContext, AddressListActivity.class);
                break;
            case R.id.tv_supplier:
                //todo 供应商
                jumpActivityNotFinish(mContext, SupplierActivity.class);
                break;
            case R.id.tv_security:
                //todo 安全中心
                jumpActivityNotFinish(mContext, SecurityActivity.class);
                break;
            case R.id.iv_my_avatar:
                //todo 修改头像
                showSelectDiaLog();
                break;
            case R.id.tv_my_name:
                //todo 修改用户名
                Intent intent = new Intent(mContext, ModifyUserNameActivity.class);
                intent.putExtra("userName", tvMyName.getText().toString());
                startActivity(intent);
                break;
        }
    }

    /**
     * 跳转至订单页面
     *
     * @param i
     */
    private void jumpOrderActivity(int i) {
        Intent intent = new Intent(mContext, OrderActivity.class);
        intent.putExtra("type", i);
        startActivity(intent);
    }

    /**
     * 选择图片
     */
    public void showSelectDiaLog() {
        PicturesDialog dialog = new PicturesDialog(mActivity, R.style.MY_AlertDialog);
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
                    //todo 请求接口
                    updataAvatar("data:image/gif;base64," + PicUtils.imageToBase64(selImageList.get(0).path));
                }
            }
        }
//        else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
//            //预览图片返回
//            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
//                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
//                if (images != null) {
//                    selImageList.clear();
//                    selImageList.addAll(images);
//                    adapter.setImages(selImageList);
//                }
//            }
//        }
    }


    /**********************************选择图片 end*********************************************/

}
