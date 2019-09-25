package com.zhifeng.cattle.adapters;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.DetailRecord;

public class DetailRecordAdapter extends BaseRecyclerAdapter<DetailRecord.DataBeanX.DataBean> {
    public DetailRecordAdapter() {
        super(R.layout.layout_item_detailrecord);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, DetailRecord.DataBeanX.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_time, model.getCreate_time());
        holder.text(R.id.tv_item_money, String.valueOf(model.getBalance()));
        holder.text(R.id.tv_item_description, model.getNote());
    }
}
