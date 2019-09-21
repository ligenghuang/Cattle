package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.PovertyReliefDto;
import com.zhifeng.cattle.ui.home.GoodsDetailActivity;

/**
  *
  * @ClassName:     首页兴农扶贫猜你喜欢商品列表适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/21 10:46
  * @Version:        1.0
 */

public class PovertyReliefLikeAdapter extends BaseRecyclerAdapter<PovertyReliefDto.DataBean.LikeBean> {
    Context context;

    public PovertyReliefLikeAdapter(Context context) {
        super(R.layout.layout_item_poverty_relief_like);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, PovertyReliefDto.DataBean.LikeBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_goods_name,model.getGoods_name());//商品名称
        holder.text(R.id.tv_item_goods_price,"￥"+model.getPrice());//商品价格
        ImageView img = holder.itemView.findViewById(R.id.iv_item_goods_img);
        GlideUtil.setImage(context,model.getImg(),img,R.drawable.icon_goods);//商品图片
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
