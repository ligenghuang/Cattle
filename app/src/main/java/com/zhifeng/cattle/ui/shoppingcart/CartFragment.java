package com.zhifeng.cattle.ui.shoppingcart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.data.ResUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.actions.ShoppingCartAction;
import com.zhifeng.cattle.adapters.CartListAdapter;
import com.zhifeng.cattle.modules.CartListDto;
import com.zhifeng.cattle.ui.MainActivity;
import com.zhifeng.cattle.ui.home.TemporaryActivity;
import com.zhifeng.cattle.ui.impl.ShoppingCartView;
import com.zhifeng.cattle.utils.base.UserBaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName: 购物车
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/9 17:04
 * @Version: 1.0
 */
public class CartFragment extends UserBaseFragment<ShoppingCartAction> implements ShoppingCartView {
    View view;
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView fTitleTv;
    @BindView(R.id.f_right_iv)
    ImageView fRightIv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_cart_total)
    ImageView ivCartTotal;
    @BindView(R.id.tv_cart_total)
    TextView tvCartTotal;
    @BindView(R.id.tv_cart_delete)
    TextView tvCartDelete;
    @BindView(R.id.tv_cart_price)
    TextView tvCartPrice;
    @BindView(R.id.tv_cart_settlement)
    TextView tvCartSettlement;
    @BindView(R.id.ll_data)
    LinearLayout llData;
    @BindView(R.id.tv_cart_f)
    TextView tvCartF;
    @BindView(R.id.ll_null)
    LinearLayout llNull;

    CartListAdapter cartListAdapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getContext();
        mActivity = activity;
    }

    @Override
    protected ShoppingCartAction initAction() {
        return new ShoppingCartAction((RxAppCompatActivity) getActivity(),this);
    }

    @Override
    protected void initialize() {
        init();
        loadView();
    }

    @Override
    protected void init() {
        super.init();
        fTitleTv.setText(ResUtil.getString(R.string.main_tab_3));
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableRefresh(false);

        cartListAdapter = new CartListAdapter(mContext);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(cartListAdapter);
        loadDialog();
        getCartList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);
        binding();
        mImmersionBar.setStatusBarView(getActivity(), topView);

        return view;
    }

    @Override
    protected void loadView() {
        super.loadView();
        cartListAdapter.setOnClickListener(new CartListAdapter.OnClickListener() {
            @Override
            public void selectedListener(int id) {
                List<CartListDto.DataBean> list = cartListAdapter.getAllData();
                int num = 0;
                double total = 0;
                for (int i = 0; i <list.size() ; i++) {
                    if (list.get(i).getCart_id() == id){
                        list.get(i).setSelected( list.get(i).getSelected() == 1 ? 0:1);
                    }
                    if (list.get(i).getSelected() == 1){
                        num++;//todo 计算选中数量
                        double totalPrice = Double.parseDouble(list.get(i).getGoods_price()) * list.get(i).getGoods_num();
                        total = total+totalPrice;
                    }
                }
                tvCartPrice.setText(ResUtil.getFormatString(R.string.cart_tab_5,total+""));
                ivCartTotal.setSelected(num == list.size());
                cartListAdapter.notifyDataSetChanged();
            }

            @Override
            public void goodsNumListener() {
                //todo 计算总价
                List<CartListDto.DataBean> list = cartListAdapter.getAllData();
                double total = 0;
                for (int i = 0; i <list.size() ; i++) {
                    if (list.get(i).getSelected() == 1){
                        double totalPrice = Double.parseDouble(list.get(i).getGoods_price()) * list.get(i).getGoods_num();
                        total = total+totalPrice;
                    }
                }
                tvCartPrice.setText(ResUtil.getFormatString(R.string.cart_tab_5,total+""));
            }

            @Override
            public void editGoodsNum(int id, int num) {
                editCart(id+"",num+"");
            }
        });
    }

    /**
     * 获取购物车列表
     */
    @Override
    public void getCartList() {
        if (CheckNetwork.checkNetwork2(mContext)){
            baseAction.getCartList();
        }
    }

    /**
     * 获取购物车列表成功
     * @param cartListDto
     */
    @Override
    public void getCartListSuccess(CartListDto cartListDto) {
        loadDiss();
//        String json = new GetJsonDataUtil().getJson(mContext,"cart.json");
//        CartListDto cartListDto1 = new Gson().fromJson(json, new TypeToken<CartListDto>() {
//        }.getType());

        if (cartListDto.getData().size()!= 0){
            llData.setVisibility(View.VISIBLE);
            llNull.setVisibility(View.GONE);
            cartListAdapter.refresh(cartListDto.getData());
            fTitleTv.setText(ResUtil.getFormatString(R.string.cart_tab_9,cartListDto.getData().size()+""));
        }else {
            llData.setVisibility(View.GONE);
            llNull.setVisibility(View.VISIBLE);
            fTitleTv.setText(ResUtil.getString(R.string.cart_tab_11));
        }
    }

    /**
     * 删除购物车商品
     * @param id
     */
    @Override
    public void delCart(String id) {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.delCart(id);
        }
    }

    /**
     * 删除购物车商品成功
     */
    @Override
    public void delCartSuccess() {
        getCartList();
    }


    @Override
    public void editCart(String id, String num) {
        if (CheckNetwork.checkNetwork2(mContext)){
            baseAction.editCart(id,num);
        }
    }

    @Override
    public void editCartSuccess() {

    }

    @Override
    public void editCartError(String msg) {
        showToast(msg);
        getCartList();
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
        refreshLayout.finishLoadMore();
        showToast(message);
    }


    @Override
    public void onResume() {
        super.onResume();
        baseAction.toRegister();
        getCartList();
        tvCartPrice.setText(ResUtil.getFormatString(R.string.cart_tab_5,"0"));
    }

    @Override
    public void onPause() {
        super.onPause();
        baseAction.toUnregister();
    }

    /**
     * 点击事件监听
     * @param view
     */
    @OnClick({ R.id.iv_cart_total, R.id.tv_cart_total, R.id.tv_cart_delete, R.id.tv_cart_settlement, R.id.tv_cart_f,R.id.f_right_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_cart_total:
            case R.id.tv_cart_total:
                //todo 全选或反选
                setSelected();
                break;
            case R.id.tv_cart_delete:
                //todo 删除
                delete();
                break;
            case R.id.tv_cart_settlement:
                //todo 结算
                settlement();
                break;
            case R.id.tv_cart_f:
            case R.id.f_right_iv:
                //todo 返回首页
                MainActivity.Position = 0;
                ((MainActivity)mActivity).setSelectedLin();
                break;
        }
    }

    /**
     * 结算
     */
    private void settlement() {
        List<CartListDto.DataBean> listDtos = cartListAdapter.getAllData();
        String id = "";
        int num = 0;
        for (int i = 0; i <listDtos.size() ; i++) {
            if (listDtos.get(i).getSelected() == 1){
                num++;
                if (i ==0){
                    id = listDtos.get(i).getCart_id()+"";
                }else {
                    id = id+","+listDtos.get(i).getCart_id();
                }
            }
        }

        if (num == 0){
            showToast(ResUtil.getString(R.string.cart_tab_38));
            return;
        }

        Intent intent = new Intent(mContext, TemporaryActivity.class);
        intent.putExtra("cartId",id);
        startActivity(intent);
    }

    /**
     * 删除购物车商品
     */
    private void delete() {
        List<CartListDto.DataBean> list = cartListAdapter.getAllData();
        String id = "";
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSelected() == 1){
                num++;
                //todo 拼接id
                if (i == 0){
                    id = list.get(i).getCart_id()+"";
                }else {
                    id = id+","+list.get(i).getCart_id();
                }
            }
        }
        //todo 判断是否有选中的商品
        if (num == 0){
            showToast(ResUtil.getString(R.string.cart_tab_10));
            return;
        }

        //todo 请求接口
        delCart(id);

    }

    /**
     * 全选或反选
     */
    private void setSelected() {
        List<CartListDto.DataBean> list = cartListAdapter.getAllData();
        double total = 0;
        if (ivCartTotal.isSelected()){
            //todo 已全选中
            for (int i = 0; i <list.size() ; i++) {
                //todo 进行反选
                list.get(i).setSelected(0);
            }
        }else {
            //todo 未全选中
            for (int i = 0; i <list.size() ; i++) {
                //todo 进行全选
                list.get(i).setSelected(1);
                double totalPrice = Double.parseDouble(list.get(i).getGoods_price()) * list.get(i).getGoods_num();
                total = total+totalPrice;
            }
        }
        tvCartPrice.setText(ResUtil.getFormatString(R.string.cart_tab_5,total+""));
        ivCartTotal.setSelected(!ivCartTotal.isSelected());
        cartListAdapter.notifyDataSetChanged();
    }


}
