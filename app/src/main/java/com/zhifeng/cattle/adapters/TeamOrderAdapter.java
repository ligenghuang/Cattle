package com.zhifeng.cattle.adapters;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.TeamOrder;

public class TeamOrderAdapter extends BaseRecyclerAdapter<TeamOrder.DataBeanX.DataBean> {
    public TeamOrderAdapter() {
        super(R.layout.layout_item_teamorder);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, TeamOrder.DataBeanX.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_orderId,String.valueOf(model.getOrder_id()) );
        holder.text(R.id.tv_item_name,model.getConsignee());
        holder.text(R.id.tv_item_createTime,model.getAdd_time());
    }
}
