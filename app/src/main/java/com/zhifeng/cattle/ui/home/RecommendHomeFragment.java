package com.zhifeng.cattle.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.RecommendHomeAction;
import com.zhifeng.cattle.modules.RecommendHomeDto;
import com.zhifeng.cattle.ui.impl.RecommendHomeView;
import com.zhifeng.cattle.utils.base.UserBaseFragment;

import butterknife.ButterKnife;

/**
  *
  * @ClassName:     首页推荐
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/18 17:12
  * @Version:        1.0
 */

public class RecommendHomeFragment extends UserBaseFragment<RecommendHomeAction> implements RecommendHomeView {
    View view;

    @Override
    protected RecommendHomeAction initAction() {
        return new RecommendHomeAction((RxAppCompatActivity) getActivity(),this);
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
        view = inflater.inflate(R.layout.fragment_home_recommend, container, false);
        ButterKnife.bind(this, view);
        binding();
        return view;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible){
            getRecommendHome();
        }
    }

    /**
     * 获取首页推荐
     */
    @Override
    public void getRecommendHome() {
       if (CheckNetwork.checkNetwork2(mContext)){
           baseAction.getRecommendHome();
       }
    }

    @Override
    public void getRecommendHomeSuccess(RecommendHomeDto recommendHomeDto) {

    }

    @Override
    public void onError(String message, int code) {

    }


}
