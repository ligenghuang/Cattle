package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.OrderListDto;
/**
  *
  * @ClassName:     退货列表商品适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/12 9:32
  * @Version:        1.0
 */

public class SalesReturnGoodsAdapter extends BaseRecyclerAdapter<OrderListDto.DataBean.GoodsBean> {
    Context context;

    public SalesReturnGoodsAdapter(Context context) {
        super(R.layout.layout_item_sales_return_goods);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, OrderListDto.DataBean.GoodsBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_sales_return_name,model.getGoods_name());//商品名称
        holder.text(R.id.tv_item_sales_return_spec,model.getSpec_key_name());//商品规格
        holder.text(R.id.tv_item_sales_return_num,"×"+model.getGoods_num());//商品数量

        ImageView img = holder.itemView.findViewById(R.id.iv_item_sales_return_img);
        GlideUtil.setImage(context,model.getImg(),img);
    }
}
