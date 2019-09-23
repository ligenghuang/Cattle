package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.OrderDetailDto;
import com.zhifeng.cattle.ui.home.GoodsDetailActivity;

/**
  *
  * @ClassName:     订单详情商品列表适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/11 17:10
  * @Version:        1.0
 */

public class OrderDetailGoodsAdapter extends BaseRecyclerAdapter<OrderDetailDto.DataBean.GoodsResBean> {
    Context context;

    public OrderDetailGoodsAdapter(Context context) {
        super(R.layout.layout_order_goods);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, OrderDetailDto.DataBean.GoodsResBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_order_goods_name,model.getGoods_name());//商品名称
        holder.text(R.id.tv_order_goods_price,"￥"+model.getGoods_price());//商品价格
        holder.text(R.id.tv_order_goods_num,"x"+model.getGoods_num());//商品数量
        ImageView img = holder.itemView.findViewById(R.id.iv_order_goods_img);
        GlideUtil.setImage(context,model.getImg(),img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 跳转至商品详情页
                Intent intent = new Intent(context, GoodsDetailActivity.class);
                intent.putExtra("goods_id",model.getGoods_id());
                context.startActivity(intent);
            }
        });
    }
}
