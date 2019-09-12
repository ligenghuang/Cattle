package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.config.GlideUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BaseAction;
import com.zhifeng.cattle.actions.MyAction;
import com.zhifeng.cattle.modules.UserInfoDto;
import com.zhifeng.cattle.ui.MainActivity;
import com.zhifeng.cattle.ui.impl.MyView;
import com.zhifeng.cattle.ui.login.LoginActivity;
import com.zhifeng.cattle.utils.base.UserBaseFragment;
import com.zhifeng.cattle.utils.data.MySp;

import java.text.DecimalFormat;

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
        return new MyAction((RxAppCompatActivity) getActivity(),this);
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
        if (isVisible){
            getUserInfo();
        }
    }

    /**
     * 获取用户信息
     */
    @Override
    public void getUserInfo() {
        if (CheckNetwork.checkNetwork2(mContext)){
            baseAction.getUserInfo();
        }
    }

    /**
     * 获取用户信息成功
     * @param userInfoDto
     */
    @Override
    public void getUserInfoSuccess(UserInfoDto userInfoDto) {
        loadDiss();
        UserInfoDto.DataBean dataBean = userInfoDto.getData();
        GlideUtil.setImageCircle(mContext,dataBean.getAvatar(),ivMyAvatar,R.mipmap.logo);//头像
        tvMyName.setText(dataBean.getRealname());//昵称
        tvMyLevel.setText(dataBean.getLevelname());//等级
        tvMyId.setText("ID:"+dataBean.getId()+"");//id

        double money = Double.parseDouble(dataBean.getRemainder_money());
        DecimalFormat df = new DecimalFormat("#0.00");
        tvMyRemainderMoney.setText(df.format(money));//提现余额
        tvMyCollection.setText(dataBean.getCollection()+"");//关注

        tvBonusDay.setText(dataBean.getDay()+"");//当日奖金
        tvBonusMonth.setText(dataBean.getMonth()+"");//当月奖金

        tvTotalResults.setText(dataBean.getDistribut_money());//总业绩
        tvHeadcount.setText(dataBean.getTeam_count()+"");//总人数
        tvRecommended.setText(dataBean.getToday_rec()+"");//今日推荐

    }

    /**
     * token过期
     */
    @Override
    public void onLoginNo() {
        loadDialog();
        Toast.makeText(mContext, "登录过期，请重新登录！", Toast.LENGTH_SHORT).show();
        MainActivity.Position = 0;
        MySp.clearAllSP(mContext);
        jumpActivityNotFinish(mContext, LoginActivity.class);
    }

    /**
     * 失败
     * @param message
     * @param code
     */
    @Override
    public void onError(String message, int code) {
        loadDiss();
        showToast(message);
    }

    @Override
    public void onResume() {
        super.onResume();
        baseAction.toRegister();
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
            R.id.ll_bonus_day,R.id.ll_bonus_month,R.id.tv_ranking_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_remainder_money:
                //todo 提现余额
                jumpActivityNotFinish(mContext,BalanceActivity.class);
                break;
            case R.id.ll_my_collection:
                //todo 关注
                jumpActivityNotFinish(mContext,CollectionActivity.class);
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
                jumpActivityNotFinish(mContext,SalesReturnActivity.class);
                break;
            case R.id.ll_bonus_day:
                //todo 当日累计奖金
                jumpActivityNotFinish(mContext,BonusDayActivity.class);
                break;
            case R.id.tv_ranking_list:
                //todo 排行榜
                break;
            case R.id.ll_bonus_month:
                //todo 当月累计奖金
                jumpActivityNotFinish(mContext,BonusMonActivity.class);
                break;
            case R.id.ll_my_team:
            case R.id.ll_total_results:
            case R.id.ll_headcount:
            case R.id.ll_recommended:
                //todo 我的团队
                jumpActivityNotFinish(mContext,MyTeamActivity.class);
                break;
            case R.id.tv_invitation:
                //todo 邀请分享
                jumpActivityNotFinish(mContext,InvitationActivity.class);
                break;
            case R.id.tv_address:
                //todo 地址管理
                jumpActivityNotFinish(mContext,AddressListActivity.class);
                break;
            case R.id.tv_supplier:
                break;
            case R.id.tv_security:
                break;
        }
    }

    /**
     * 跳转至订单页面
     * @param i
     */
    private void jumpOrderActivity(int i) {
        Intent intent = new Intent(mContext,OrderActivity.class);
        intent.putExtra("type",i);
        startActivity(intent);
    }


}
