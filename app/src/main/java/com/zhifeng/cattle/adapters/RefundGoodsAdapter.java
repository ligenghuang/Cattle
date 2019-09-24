package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.OrderDetailDto;

public class RefundGoodsAdapter extends BaseRecyclerAdapter<OrderDetailDto.DataBean.GoodsResBean> {
    private Context context;

    public RefundGoodsAdapter(Context context) {
        super(R.layout.layout_item_refundgoodsdetail);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, OrderDetailDto.DataBean.GoodsResBean model, int position) {
        holder.setIsRecyclable(false);
        ImageView iv = holder.itemView.findViewById(R.id.iv_goods_img);
        GlideUtil.setImage(context, model.getImg(), iv, R.drawable.icon_goods);
        holder.text(R.id.tv_goods_name, model.getGoods_name());
        holder.text(R.id.tv_spec_key_name, model.getSpec_key_name());
        holder.text(R.id.tv_goods_price, ResUtil.getFormatString(R.string.cart_tab_17, model.getGoods_price()));
        holder.text(R.id.tv_goods_num, ResUtil.getFormatString(R.string.cart_tab_18, String.valueOf(model.getGoods_num())));
    }
}
