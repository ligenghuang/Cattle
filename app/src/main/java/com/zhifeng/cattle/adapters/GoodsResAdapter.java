package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.widget.EditText;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.Temporary;

import java.util.List;

public class GoodsResAdapter extends BaseRecyclerAdapter<Temporary.DataBean.GoodsResBean> {
    private Context context;
    private String shipping_price;
    private OnGoodsDecreaseListener onGoodsDecreaseListener;
    private OnGoodsIncreaseListener onGoodsIncreaseListener;

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
        holder.text(R.id.tvShipping_price, "0.00".equals(shipping_price) ? context.getString(R.string.cart_tab_23) : shipping_price);
        holder.itemView.findViewById(R.id.tv_item_goods_subtract).setOnClickListener(v -> {
            EditText et_item_goods_num = holder.itemView.findViewById(R.id.et_item_goods_num);
            et_item_goods_num.clearFocus();
            model.setGoods_num(model.getGoods_num() - 1 < 1 ? 1 : model.getGoods_num() - 1);
            holder.text(R.id.et_item_goods_num, String.valueOf(model.getGoods_num()));
            if (onGoodsDecreaseListener != null) {
                List<Temporary.DataBean.GoodsResBean> beans = getAllData();
                int totalNum = 0;
                double totalPrice = 0;
                for (Temporary.DataBean.GoodsResBean bean : beans) {
                    totalNum += bean.getGoods_num();
                    totalPrice += Double.parseDouble(bean.getGoods_price()) * bean.getGoods_num();
                }
                onGoodsDecreaseListener.onClick(totalNum, totalPrice);
            }
        });
        holder.itemView.findViewById(R.id.tv_item_goods_add).setOnClickListener(v -> {
            EditText et_item_goods_num = holder.itemView.findViewById(R.id.et_item_goods_num);
            et_item_goods_num.clearFocus();
            model.setGoods_num(model.getGoods_num() + 1);
            holder.text(R.id.et_item_goods_num, String.valueOf(model.getGoods_num()));
            if (onGoodsIncreaseListener != null) {
                List<Temporary.DataBean.GoodsResBean> beans = getAllData();
                int totalNum = 0;
                double totalPrice = 0;
                for (Temporary.DataBean.GoodsResBean bean : beans) {
                    totalNum += bean.getGoods_num();
                    totalPrice += Double.parseDouble(bean.getGoods_price()) * bean.getGoods_num();
                }
                onGoodsIncreaseListener.onClick(totalNum, totalPrice);
            }
        });
    }

    public void setShipping_price(String shipping_price) {
        this.shipping_price = shipping_price;
    }

    public void setOnGoodsDecreaseListener(OnGoodsDecreaseListener onGoodsDecreaseListener) {
        this.onGoodsDecreaseListener = onGoodsDecreaseListener;
    }

    public void setOnGoodsIncreaseListener(OnGoodsIncreaseListener onGoodsIncreaseListener) {
        this.onGoodsIncreaseListener = onGoodsIncreaseListener;
    }

    public interface OnGoodsDecreaseListener {
        void onClick(int totalNum, double totalPrice);
    }

    public interface OnGoodsIncreaseListener {
        void onClick(int totalNum, double totalPrice);
    }
}
