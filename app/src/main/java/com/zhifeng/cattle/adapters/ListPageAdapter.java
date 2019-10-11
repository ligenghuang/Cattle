package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.ListPage;
import com.zhifeng.cattle.ui.home.GoodsDetailActivity;

public class ListPageAdapter extends BaseRecyclerAdapter<ListPage.DataBean> {
    private Context context;

    public ListPageAdapter(Context context) {
        super(R.layout.layout_item_other_goods);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, ListPage.DataBean model, int position) {
        holder.setIsRecyclable(false);
        ImageView iv = holder.itemView.findViewById(R.id.iv_item_goods_img);
        GlideUtil.setImage(context, model.getImg(), iv, R.drawable.icon_goods_detail);
        holder.text(R.id.tv_item_goods_name, model.getGoods_name());
        holder.text(R.id.tv_item_goods_price, "￥"+model.getPrice());
        TextView textView = holder.itemView.findViewById(R.id.tv_item_goods_original_price);
        textView.setText("￥"+model.getOriginal_price());
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsDetailActivity.class);
                intent.putExtra("goods_id",model.getGoods_id());
                context.startActivity(intent);
            }
        });
    }
}
