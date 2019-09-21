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
    private String shipping_price;
    private OnGoodsNumChangeListener onGoodsNumChangeListener;
    private Handler handler = new MyHandler(this);

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
        View.OnClickListener onClickListener = v -> {
            if (v.getId() == R.id.tv_item_goods_subtract) {
                model.setGoods_num(model.getGoods_num() - 1 < 1 ? 1 : model.getGoods_num() - 1);
                holder.text(R.id.et_item_goods_num, String.valueOf(model.getGoods_num()));
            } else {
                model.setGoods_num(model.getGoods_num() + 1);
                holder.text(R.id.et_item_goods_num, String.valueOf(model.getGoods_num()));
            }
//            if (onGoodsNumChangeListener != null) {
//                List<Temporary.DataBean.GoodsResBean> beans = getAllData();
//                int totalNum = 0;
//                double totalPrice = 0;
//                for (Temporary.DataBean.GoodsResBean bean : beans) {
//                    totalNum += bean.getGoods_num();
//                    totalPrice += Double.parseDouble(bean.getGoods_price()) * bean.getGoods_num();
//                }
//                onGoodsNumChangeListener.onChange(totalNum, totalPrice);
//            }
        };

        EditText et_item_goods_num = holder.itemView.findViewById(R.id.et_item_goods_num);
        et_item_goods_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    handler.removeMessages(1);
                    int num = Integer.parseInt(s.toString());
                    model.setGoods_num(num);
                    holder.text(R.id.et_item_goods_num, String.valueOf(model.getGoods_num()));
                    handler.sendEmptyMessageDelayed(1,1000);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        holder.itemView.findViewById(R.id.tv_item_goods_subtract).setOnClickListener(onClickListener);
        holder.itemView.findViewById(R.id.tv_item_goods_add).setOnClickListener(onClickListener);
    }

    public void setShipping_price(String shipping_price) {
        this.shipping_price = shipping_price;
    }

    public void setOnGoodsNumChangeListener(OnGoodsNumChangeListener onGoodsNumChangeListener) {
        this.onGoodsNumChangeListener = onGoodsNumChangeListener;
    }

    private static class MyHandler extends Handler {
        private WeakReference<GoodsResAdapter> adapterWeakReference;

        private MyHandler(GoodsResAdapter goodsResAdapter) {
            adapterWeakReference = new WeakReference<>(goodsResAdapter);
        }

        @Override
        public void handleMessage(Message msg) {
            GoodsResAdapter goodsResAdapter = adapterWeakReference.get();
            OnGoodsNumChangeListener onGoodsNumChangeListener = goodsResAdapter.onGoodsNumChangeListener;
            if (onGoodsNumChangeListener != null) {
                List<Temporary.DataBean.GoodsResBean> beans = goodsResAdapter.getAllData();
                int totalNum = 0;
                double totalPrice = 0;
                for (Temporary.DataBean.GoodsResBean bean : beans) {
                    totalNum += bean.getGoods_num();
                    totalPrice += Double.parseDouble(bean.getGoods_price()) * bean.getGoods_num();
                }
                onGoodsNumChangeListener.onChange(totalNum, totalPrice);
            }
        }
    }

    public interface OnGoodsNumChangeListener {
        void onChange(int totalNum, double totalPrice);
    }
}
