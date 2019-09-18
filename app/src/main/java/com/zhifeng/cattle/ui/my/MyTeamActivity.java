package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.MyTeamAction;
import com.zhifeng.cattle.modules.MyTeamDto;
import com.zhifeng.cattle.ui.impl.MyTeamView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: 我的团队
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/12 11:10
 * @Version: 1.0
 */

public class MyTeamActivity extends UserBaseActivity<MyTeamAction> implements MyTeamView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_total_results)
    TextView tvTotalResults;
    @BindView(R.id.tv_cumulative_rewards)
    TextView tvCumulativeRewards;
    @BindView(R.id.tv_team_num)
    TextView tvTeamNum;
    @BindView(R.id.tv_first_leader_id)
    TextView tvFirstLeaderId;
    @BindView(R.id.tv_first_leader_name)
    TextView tvFirstLeaderName;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    public int intiLayout() {
        return R.layout.activity_my_team;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected MyTeamAction initAction() {
        return new MyTeamAction(this,this);
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
                .addTag("MyTeamActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.my_tab_13));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        refreshLayout.setEnableLoadMore(false);

        loadDialog();
        getMyTeam();
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getMyTeam();
            }
        });
    }

    /**
     * 获取我的团队
     */
    @Override
    public void getMyTeam() {
        if (CheckNetwork.checkNetwork2(mContext)){
            baseAction.getMyTeam();
        }else {
            loadDiss();
            refreshLayout.finishRefresh();
        }
    }

    /**
     * 获取我的团队成功
     * @param myTeamDto
     */
    @Override
    public void getMyTeamSuccess(MyTeamDto myTeamDto) {
        loadDiss();
        refreshLayout.finishRefresh();
        MyTeamDto.DataBean dataBean = myTeamDto.getData();
        tvTotalResults.setText("￥"+dataBean.getPerformance());
        tvCumulativeRewards.setText("￥"+dataBean.getReward());
        tvTeamNum.setText(dataBean.getTeam_number()+"");
        tvFirstLeaderId.setText(ResUtil.getFormatString(R.string.my_team_tab_6,dataBean.getFirst_leader()+""));
        tvFirstLeaderName.setText(ResUtil.getFormatString(R.string.my_team_tab_8,dataBean.getFirst_leader_name()));
        tvUserId.setText(ResUtil.getFormatString(R.string.my_team_tab_7,dataBean.getId()+""));
    }

    /**
     * 失败
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
    protected void onResume() {
        super.onResume();
        baseAction.toRegister();
    }

    @Override
    protected void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }

    @OnClick({R.id.tv_detail_record, R.id.tv_team_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_detail_record:
                //todo 明细记录
                jumpActivityNotFinish(mContext,DetailRecordActivity.class);
                break;
            case R.id.tv_team_list:
                //todo 团队列表
                jumpActivityNotFinish(mContext,TeamListActivity.class);
                break;
        }
    }
}
