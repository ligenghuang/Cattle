package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.ResUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.CollectionAction;
import com.zhifeng.cattle.adapters.CollectionListAdapter;
import com.zhifeng.cattle.modules.CollectionListDto;
import com.zhifeng.cattle.ui.impl.CollectionView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: 关注
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/11 10:27
 * @Version: 1.0
 */

public class CollectionActivity extends UserBaseActivity<CollectionAction> implements CollectionView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.f_right_tv)
    TextView fRightTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_cart_total)
    ImageView ivCartTotal;
    @BindView(R.id.ll_management)
    LinearLayout llManagement;

    CollectionListAdapter collectionListAdapter;

    boolean isManagement = false;

    @Override
    public int intiLayout() {
        return R.layout.activity_collection;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected CollectionAction initAction() {
        return new CollectionAction(this, this);
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
                .addTag("CollectionActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.my_tab_2));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        refreshLayout.setEnableLoadMore(false);
        collectionListAdapter = new CollectionListAdapter(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(collectionListAdapter);

        refreshLayout.autoRefresh();
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getCollectionList();
            }
        });

        collectionListAdapter.setOnClickListener(new CollectionListAdapter.OnClickListener() {
            @Override
            public void onClick(int id) {
                List<CollectionListDto.DataBean> list = collectionListAdapter.getAllData();
                int num = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getGoods_id() == id){
                        list.get(i).setClick(true);
                    }
                    if (list.get(i).isClick()){
                        num++;//todo 计算选中数量
                    }
                }
                ivCartTotal.setSelected(num == list.size());
                collectionListAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 获取关注列表
     */
    @Override
    public void getCollectionList() {
        if (CheckNetwork.checkNetwork2(mContext)){
            baseAction.getCollectionList();
        }else {
            loadDiss();
            refreshLayout.finishRefresh();
        }
    }

    /**
     * 获取关注列表成功
     * @param collectionListDto
     */
    @Override
    public void getCollectionListSuccess(CollectionListDto collectionListDto) {
        loadDiss();
        refreshLayout.finishRefresh();
//        String json = new GetJsonDataUtil().getJson(mContext,"collectionList.json");
//        CollectionListDto collectionListDto1 = new Gson().fromJson(json, new TypeToken<CollectionListDto>() {
//        }.getType());

        collectionListAdapter.refresh(collectionListDto.getData());
        isManagement = false;
        setManagement();
    }

    /**
     * 取消关注
     * @param goods_id
     */
    @Override
    public void deleteCollection(String goods_id) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.deleteCollection(goods_id);
        }
    }

    /**
     * 取消关注成功
     * @param msg
     */
    @Override
    public void deleteCollectionSuccess(String msg) {
        getCollectionList();
    }

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

    private void setManagement() {
        fRightTv.setText(ResUtil.getString(isManagement?R.string.collection_tab_3:R.string.collection_tab_4));
        collectionListAdapter.setManagement(isManagement);
        collectionListAdapter.notifyDataSetChanged();
        llManagement.setVisibility(isManagement?View.VISIBLE:View.GONE);
    }

    /**
     * 全选或反选
     */
    private void setSelected() {
        List<CollectionListDto.DataBean> list = collectionListAdapter.getAllData();
        double total = 0;
        if (ivCartTotal.isSelected()){
            //todo 已全选中
            for (int i = 0; i <list.size() ; i++) {
                //todo 进行反选
                list.get(i).setClick(false);
            }
        }else {
            //todo 未全选中
            for (int i = 0; i <list.size() ; i++) {
                //todo 进行全选
                list.get(i).setClick(true);
            }
        }
        ivCartTotal.setSelected(!ivCartTotal.isSelected());
        collectionListAdapter.notifyDataSetChanged();
    }

    /**
     * 删除商品
     */
    private void delete() {
        List<CollectionListDto.DataBean> list = collectionListAdapter.getAllData();
        String id = "";
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isClick()){
                num++;
                //todo 拼接id
                if (num == 1){
                    id = list.get(i).getGoods_id()+"";
                }else {
                    id = id+","+list.get(i).getGoods_id();
                }
            }
        }
        //todo 判断是否有选中的商品
        if (num == 0){
            showNormalToast(ResUtil.getString(R.string.cart_tab_10));
            return;
        }

        //todo 请求接口
        deleteCollection(id);

    }

    @OnClick({R.id.iv_cart_total, R.id.tv_cart_total, R.id.tv_collection_delete,R.id.f_right_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_cart_total:
            case R.id.tv_cart_total:
                //todo 全选或反选
                setSelected();
                break;
            case R.id.tv_collection_delete:
                //todo 删除
                delete();
                break;
            case R.id.f_right_tv:
                isManagement = !isManagement;
                setManagement();
                break;
        }
    }
}
