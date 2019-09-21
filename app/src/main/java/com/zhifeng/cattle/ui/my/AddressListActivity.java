package com.zhifeng.cattle.ui.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.zhifeng.cattle.actions.AddressListAction;
import com.zhifeng.cattle.adapters.AddressListAdapter;
import com.zhifeng.cattle.modules.AddressListDto;
import com.zhifeng.cattle.modules.GeneralDto;
import com.zhifeng.cattle.ui.impl.AddressListView;
import com.zhifeng.cattle.utils.base.UserBaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: 地址管理
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/12 15:15
 * @Version: 1.0
 */

public class AddressListActivity extends UserBaseActivity<AddressListAction> implements AddressListView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    AddressListAdapter addressListAdapter;

    boolean isGoods = false;

    @Override
    public int intiLayout() {
        return R.layout.activity_address_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<Activity>(this));
        binding();
    }

    @Override
    protected AddressListAction initAction() {
        return new AddressListAction(this, this);
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
                .addTag("AddressListActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        fTitleTv.setText(ResUtil.getString(R.string.my_tab_19));
    }

    @Override
    protected void init() {
        super.init();
        mActicity = this;
        mContext = this;

        isGoods = getIntent().getBooleanExtra("isGoods",false);

        refreshLayout.setEnableLoadMore(false);
        addressListAdapter = new AddressListAdapter(isGoods);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(addressListAdapter);

        loadDialog();
        getAddressList();
        loadView();
    }

    @Override
    protected void loadView() {
        super.loadView();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getAddressList();
            }
        });

        addressListAdapter.setOnClickListener(new AddressListAdapter.OnClickListener() {
            @Override
            public void edit(int id) {
                //编辑
                Intent intent = new Intent(mContext,AddAddressActivity.class);
                intent.putExtra("address_id",id);
                startActivity(intent);
            }

            @Override
            public void Detele(int id) {
                //删除
                deteleAddress(id);
            }

            @Override
            public void Is_default(int id) {
                //设置为默认地址
                setDefaultAddress(id);
            }

            @Override
            public void itemView(AddressListDto.DataBean model) {
                Intent intent = new Intent();
                intent.putExtra("address",model.getP_cn()+model.getC_cn()+model.getD_cn());
                intent.putExtra("phone",model.getMobile());
                intent.putExtra("address_id",model.getAddress_id());
                intent.putExtra("consignee",model.getConsignee());
                intent.putExtra("address2",model.getAddress());
                setResult(200,intent);
                finish();
            }
        });
    }

    /**
     * 获取地址列表
     */
    @Override
    public void getAddressList() {
        if (CheckNetwork.checkNetwork2(mContext)){
            baseAction.getAddressList();
        }else {
            loadDiss();
            refreshLayout.finishRefresh();
        }
    }

    /**
     * 获取地址列表成功
     * @param addressListDto
     */
    @Override
    public void getAddressListSuccess(AddressListDto addressListDto) {
        loadDiss();
        refreshLayout.finishRefresh();
        if (addressListDto.getData().size() != 0){
            recyclerview.setVisibility(View.VISIBLE);
            addressListAdapter.refresh(addressListDto.getData());
        }else {
            //todo 2019/09/12 添加空布局
            recyclerview.setVisibility(View.GONE);
        }
    }

    @Override
    public void getAddressListNull(){
        loadDiss();
        refreshLayout.finishRefresh();
        //todo 2019/09/12 添加空布局
        recyclerview.setVisibility(View.GONE);
    }

    /**
     * 删除地址
     * @param id
     */
    @Override
    public void deteleAddress(int id) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.deteleAddress(id);
        }
    }

    /**
     * 删除地址成功
     * @param generalDto
     */
    @Override
    public void deteleAddressSuccess(GeneralDto generalDto) {
        showNormalToast(ResUtil.getString(R.string.address_tab_5));
        getAddressList();
    }

    /**
     * 设置默认地址
     * @param id
     */
    @Override
    public void setDefaultAddress(int id) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.setDefaultAddress(id);
        }
    }

    /**
     * 设置默认地址成功
     * @param generalDto
     */
    @Override
    public void setDefaultAddressSuccess(GeneralDto generalDto) {
        showNormalToast(ResUtil.getString(R.string.address_tab_6));
        getAddressList();
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
    protected void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (baseAction != null){
            baseAction.toRegister();
            getAddressList();
        }
    }

    @OnClick(R.id.ll_add_address)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.ll_add_address:
                //todo 新增地址
                jumpActivityNotFinish(mContext,AddAddressActivity.class);
                break;
        }
    }
}
