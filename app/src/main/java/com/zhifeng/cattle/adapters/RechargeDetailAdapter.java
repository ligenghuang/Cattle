package com.zhifeng.cattle.adapters;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.ReChargeDetail;

public class RechargeDetailAdapter extends BaseRecyclerAdapter<ReChargeDetail.DataBeanX.ListBean.DataBean> {
    public RechargeDetailAdapter() {
        super(R.layout.layout_item_rechargedetail);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, ReChargeDetail.DataBeanX.ListBean.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_rechargeTime, model.getCreate_time());
        holder.text(R.id.tv_item_money,model.getAmount());
        holder.text(R.id.tv_item_rechargeStatus,model.getOrder_status());
    }
}
