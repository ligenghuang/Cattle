package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.OrderListDto;
/**
  *
  * @ClassName:     订单列表商品适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/11 15:31
  * @Version:        1.0
 */

public class OrderGoodsListAdapter extends BaseRecyclerAdapter<OrderListDto.DataBean.GoodsBean> {
    Context context;

    public OrderGoodsListAdapter(Context context) {
        super(R.layout.layout_item_order_goods);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, OrderListDto.DataBean.GoodsBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_order_name,model.getGoods_name());//商品名称
        holder.text(R.id.tv_item_order_spec,model.getSpec_key_name());//商品规格
        holder.text(R.id.tv_item_order_price,"AU$"+model.getGoods_price());//商品价格
        ImageView imageView = holder.itemView.findViewById(R.id.iv_item_order_img);
        GlideUtil.setImage(context,model.getImg(),imageView);
    }
}
