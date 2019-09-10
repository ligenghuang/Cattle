package com.zhifeng.cattle.ui.classify;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.BaseAction;
import com.zhifeng.cattle.actions.ClassifyAction;
import com.zhifeng.cattle.adapters.CategoryAdapter;
import com.zhifeng.cattle.adapters.CategoryListAdapter;
import com.zhifeng.cattle.modules.CategoryListDto;
import com.zhifeng.cattle.ui.impl.ClassifyView;
import com.zhifeng.cattle.utils.base.UserBaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: 分类fragment
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/9 16:58
 * @Version: 1.0
 */

public class ClassifyFragment extends UserBaseFragment<ClassifyAction> implements ClassifyView {
    View view;
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.et_classify_search)
    EditText etClassifySearch;
    @BindView(R.id.rv_left)
    RecyclerView rvLeft;
    @BindView(R.id.rv_right)
    RecyclerView rvRight;

    CategoryListAdapter categoryListAdapter;
    CategoryAdapter categoryAdapter;


    @Override
    protected ClassifyAction initAction() {
        return new ClassifyAction((RxAppCompatActivity) getActivity(),this);
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

        categoryListAdapter = new CategoryListAdapter();
        rvLeft.setLayoutManager(new LinearLayoutManager(mContext));
        rvLeft.setAdapter(categoryListAdapter);

        categoryAdapter = new CategoryAdapter(mContext);
        rvRight.setLayoutManager(new LinearLayoutManager(mContext));
        rvRight.setAdapter(categoryAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classify, container, false);
        ButterKnife.bind(this, view);
        binding();
        mImmersionBar.setStatusBarView(getActivity(), topView);
        return view;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible){
            getCategoryList();
        }
    }

    @Override
    protected void loadView() {
        super.loadView();
        categoryListAdapter.setOnClickListener(new CategoryListAdapter.OnClickListener() {
            @Override
            public void OnListClick(int id, CategoryListDto.DataBean goodsBean) {
                List<CategoryListDto.DataBean> list = categoryListAdapter.getAllData();
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setClick(list.get(i).getCat_id() == id);
                }
                categoryListAdapter.notifyDataSetChanged();
                List<CategoryListDto.DataBean> dataBeanList = new ArrayList<>();
                dataBeanList.add(goodsBean);
                categoryAdapter.refresh(dataBeanList);
            }
        });
    }

    /**
     * 获取分类列表
     */
    @Override
    public void getCategoryList() {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.getCategoryList();
        }
    }

    /**
     * 获取分类列表成功
     * @param categoryListDto
     */
    @Override
    public void getCategoryListSuccess(CategoryListDto categoryListDto) {
        loadDiss();
        List<CategoryListDto.DataBean> list =categoryListDto.getData();
        if (list.size() != 0){
            list.get(0).setClick(true);
            categoryListAdapter.refresh(list);
            List<CategoryListDto.DataBean> dataBeanList = new ArrayList<>();
            dataBeanList.add(list.get(0));
            categoryAdapter.refresh(dataBeanList);
        }else {
            //todo 暂无数据
        }
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


}
