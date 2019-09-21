package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.HomeImportOrFoodBean;
import com.zhifeng.cattle.ui.home.GoodsDetailActivity;

/**
  *
  * @ClassName:     首页进口货物和食品酒水 食品列表适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/21 11:47
  * @Version:        1.0
 */

public class HomeOtherGoodsAdapter extends BaseRecyclerAdapter<HomeImportOrFoodBean> {
    Context context;

    public HomeOtherGoodsAdapter(Context context) {
        super(R.layout.layout_item_other_goods);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, HomeImportOrFoodBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_goods_name,model.getGoods_name());//商品名称
        holder.text(R.id.tv_item_goods_price,"￥"+model.getPrice());//商品价格
        TextView textView = holder.itemView.findViewById(R.id.tv_item_goods_original_price);
        textView.setText("￥"+model.getOriginal_price());//商品原价
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        ImageView imageView = holder.itemView.findViewById(R.id.iv_item_goods_img);
        GlideUtil.setImage(context,model.getImg(),imageView,R.drawable.icon_goods);
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
