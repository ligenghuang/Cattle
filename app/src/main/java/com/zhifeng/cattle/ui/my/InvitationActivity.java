package com.zhifeng.cattle.ui.my;

import android.os.Bundle;
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

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

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
        GlideUtil.setImage(mContext, sharePoster.getData().getAvatar(), ivQcCode, R.drawable.erweima);
        tvInvitationCode.setText(String.valueOf(sharePoster.getData().getInvitation_code()));
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

                break;
        }
    }
}
