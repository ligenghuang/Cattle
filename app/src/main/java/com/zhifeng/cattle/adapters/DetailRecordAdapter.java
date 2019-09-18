package com.zhifeng.cattle.adapters;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.DetailRecord;
import com.zhifeng.cattle.utils.data.DynamicTimeFormat;

public class DetailRecordAdapter extends BaseRecyclerAdapter<DetailRecord.DataBeanX.DataBean> {
    public DetailRecordAdapter() {
        super(R.layout.layout_item_detailrecord);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, DetailRecord.DataBeanX.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_time, DynamicTimeFormat.LongToString4(model.getCreate_time()));
        holder.text(R.id.tv_item_money,model.getBalance());
        holder.text(R.id.tv_item_description,model.getNote());
    }
}
