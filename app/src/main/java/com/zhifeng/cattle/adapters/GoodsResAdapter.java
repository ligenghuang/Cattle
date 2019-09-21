package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.Temporary;

import java.lang.ref.WeakReference;
import java.util.List;

public class GoodsResAdapter extends BaseRecyclerAdapter<Temporary.DataBean.GoodsResBean> {
    private Context context;

    public GoodsResAdapter(Context context) {
        super(R.layout.layout_item_temporary);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, Temporary.DataBean.GoodsResBean model, int position) {
        holder.setIsRecyclable(false);
        ImageView iv = holder.itemView.findViewById(R.id.iv_goods_img);
        GlideUtil.setImage(context, model.getImg(), iv, R.drawable.icon_goods);
        holder.text(R.id.tv_goods_name, model.getGoods_name());
        holder.text(R.id.tv_spec_key_name, model.getSpec_key_name());
        holder.text(R.id.tv_goods_price, ResUtil.getFormatString(R.string.cart_tab_17, model.getGoods_price()));
        holder.text(R.id.tv_goods_num, ResUtil.getFormatString(R.string.cart_tab_18, model.getGoods_num()));
        holder.text(R.id.et_item_goods_num, String.valueOf(model.getGoods_num()));


    }

    public interface OnGoodsListener {
        void onChange(int totalNum, double totalPrice);
    }
}
