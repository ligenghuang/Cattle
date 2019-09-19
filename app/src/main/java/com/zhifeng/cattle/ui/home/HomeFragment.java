package com.zhifeng.cattle.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BaseAction;
import com.zhifeng.cattle.utils.base.UserBaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: 首页fragment
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/9 16:58
 * @Version: 1.0
 */

public class HomeFragment extends UserBaseFragment {
    View view;
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.tv_type_1)
    TextView tvType1;
    @BindView(R.id.tv_type_2)
    TextView tvType2;
    @BindView(R.id.tv_type_3)
    TextView tvType3;

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
//        Spannable wordtoSpan = new SpannableString("大字小字");
//        wordtoSpan.setSpan(new AbsoluteSizeSpan(20), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        wordtoSpan.setSpan(new AbsoluteSizeSpan(14), 2, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        tvType2.setText(wordtoSpan);
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
