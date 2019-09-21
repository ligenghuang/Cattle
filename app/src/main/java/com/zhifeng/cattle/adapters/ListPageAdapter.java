package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.ListPage;

public class ListPageAdapter extends BaseRecyclerAdapter<ListPage.DataBeanX.GoodsBean.DataBean> {
    private Context context;

    public ListPageAdapter(Context context) {
        super(R.layout.layout_item_other_goods);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, ListPage.DataBeanX.GoodsBean.DataBean model, int position) {
        holder.setIsRecyclable(false);
        ImageView iv = holder.itemView.findViewById(R.id.iv_item_goods_img);
        GlideUtil.setImageCircle(context, model.getImg(), iv, R.mipmap.touxiang);
        holder.text(R.id.tv_item_goods_name, model.getGoods_name());
        holder.text(R.id.tv_item_goods_price, model.getPrice());
        holder.text(R.id.tv_item_goods_original_price, model.getOriginal_price());
    }
}
