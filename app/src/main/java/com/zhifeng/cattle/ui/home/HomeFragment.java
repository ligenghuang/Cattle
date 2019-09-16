package com.zhifeng.cattle.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BaseAction;
import com.zhifeng.cattle.utils.base.UserBaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
  *
  * @ClassName:     首页fragment
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/9 16:58
  * @Version:        1.0
 */

public class HomeFragment extends UserBaseFragment {
    View view;
    @BindView(R.id.top_view)
    View topView;

    @Override
    protected BaseAction initAction() {
        return null;
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
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        binding();
        mImmersionBar.setStatusBarView(getActivity(), topView);
        return view;
    }
}
