package com.zhifeng.cattle.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BaseAction;
import com.zhifeng.cattle.adapters.UserListAdapter;
import com.zhifeng.cattle.modules.LoginUser;
import com.zhifeng.cattle.ui.MainActivity;
import com.zhifeng.cattle.ui.login.LoginActivity;
import com.zhifeng.cattle.utils.base.UserBaseActivity;
import com.zhifeng.cattle.utils.data.MySp;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName:
 * @Description: 切换用户页面
 * @Author: Administrator
 * @Date: 2019/9/21 14:28
 */
public class ChangeAccountActivity extends UserBaseActivity {
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    UserListAdapter userListAdapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_changeaccount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    protected BaseAction initAction() {
        return null;
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
                .addTag("ChangeAccountActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.security_tab_4));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        userListAdapter = new UserListAdapter(mContext);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(userListAdapter);

        String json = MySp.getUserList(mContext);
        List<LoginUser> list = new Gson().fromJson(json, new TypeToken<List<LoginUser>>() {
        }.getType());
        userListAdapter.refresh(list);
        loadView();

    }

    @Override
    protected void loadView() {
        super.loadView();
        userListAdapter.setOnClickListener(new UserListAdapter.OnClickListener() {
            @Override
            public void OnClick(LoginUser dto) {
//                MySp.setAccessToken(mContext,dto.getToken());
//                MySp.setUserName(mContext,dto.getRealname());
                if (!dto.getToken().equals(MySp.getAccessToken(mContext))) {
                    MySp.clearAllSP(mContext);
                    MainActivity.isLogin2 = true;
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    intent.putExtra("phone", dto.getMobile());
                    startActivity(intent);
                    ActivityStack.getInstance().exitIsNotHaveMain(LoginActivity.class);
                }
                finish();
            }
        });
    }

    @OnClick(R.id.btnChangeAccount)
    public void onViewClicked(View view) {
//        ActivityStack.getInstance().removeAll();
        jumpActivityNotFinish(mContext, LoginActivity.class);
    }
}
