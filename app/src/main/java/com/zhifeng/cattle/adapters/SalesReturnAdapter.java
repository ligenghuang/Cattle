package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.OrderListDto;

/**
  *
  * @ClassName:     退货列表适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/12 9:29
  * @Version:        1.0
 */

public class SalesReturnAdapter extends BaseRecyclerAdapter<OrderListDto.DataBean> {
    Context context;

    public SalesReturnAdapter(Context context) {
        super(R.layout.layout_item_sales_return);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, OrderListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_order_time,model.getAdd_time());//订单时间

        RecyclerView recyclerView = holder.itemView.findViewById(R.id.rv_item_order_goods);
        SalesReturnGoodsAdapter salesReturnGoodsAdapter = new SalesReturnGoodsAdapter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(salesReturnGoodsAdapter);
        salesReturnGoodsAdapter.refresh(model.getGoods());

        TextView type = holder.itemView.findViewById(R.id.tv_item_order_type);
        String status = ResUtil.getString(R.string.sales_return_tab_1);
        switch (model.getOrder_status()){
            case 6:
                status = ResUtil.getString(R.string.sales_return_tab_1);
                break;
            case 7:
                status = ResUtil.getString(R.string.sales_return_tab_2);
                break;
            case 8:
                status = ResUtil.getString(R.string.sales_return_tab_3);
                break;
        }
        type.setText(status);
    }
}
