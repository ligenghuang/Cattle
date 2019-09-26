package com.zhifeng.cattle.ui.my;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.InvitationAction;
import com.zhifeng.cattle.modules.SharePoster;
import com.zhifeng.cattle.ui.impl.InvitationView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;
import com.zhifeng.cattle.utils.photo.BitmapUtil;
import com.zhifeng.cattle.utils.photo.PicUtils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zhifeng.cattle.utils.photo.PicUtils.GetImageInputStream;

/**
 * @ClassName: 邀请页面
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/12 14:21
 * @Version: 1.0
 */

public class InvitationActivity extends UserBaseActivity<InvitationAction> implements InvitationView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_qc_code)
    ImageView ivQcCode;
    @BindView(R.id.tv_invitation_code)
    TextView tvInvitationCode;

    String path;

    Bitmap bitmap;

    @Override
    public int intiLayout() {
        return R.layout.activity_invitation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
        getSharePoster();
    }

    @Override
    protected InvitationAction initAction() {
        return new InvitationAction(this, this);
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
                .addTag("InvitationActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.my_tab_18));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;
    }

    @Override
    public void getSharePoster() {
        baseAction.getSharePoster();
    }

    @Override
    public void getSharePosterSuccess(SharePoster sharePoster) {
       path = sharePoster.getData().getQrcode();
//       path = "http://pic21.nipic.com/20120508/10020937_124825449172_2.jpg";
        GlideUtil.setImage(mContext,sharePoster.getData().getQrcode(),ivQcCode,R.drawable.erweima);
        tvInvitationCode.setText(sharePoster.getData().getInvitation_code()+"");
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

    @OnClick({R.id.tv_share, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_share:

                break;
            case R.id.tv_save:
                getPhotoDir();
                break;
        }
    }

    /**
     * 获取路径
     *
     */
    private void getPhotoDir() {

//加入网络图片地址
        new Task().execute(path);

    }

    Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0x123){
                PicUtils.saveBmp2Gallery(bitmap, getFileName(),mContext);
            }
        };
    };

    /**
     * 异步线程下载图片
     *
     */
    class Task extends AsyncTask<String, Integer, Void> {

        protected Void doInBackground(String... params) {
            bitmap=GetImageInputStream((String)params[0]);
            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Message message=new Message();
            message.what=0x123;
            handler.sendMessage(message);
        }

    }


    private static String getFileName() {
        return "zhifeng" + "_" + getTimeName();
    }

    /**
     * 获取当前时间来命名，以免有重复的文件名,再加上3位的随机数
     *
     * @return
     */
    public static String getTimeName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_hhmmssSSS");
        String timeName = dateFormat.format(date) + "_" + new Random().nextInt(99999);
        return timeName;
    }
}
