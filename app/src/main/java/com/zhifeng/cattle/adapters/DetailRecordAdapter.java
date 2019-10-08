package com.zhifeng.cattle.adapters;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.DetailRecord;

import java.math.BigDecimal;

public class DetailRecordAdapter extends BaseRecyclerAdapter<DetailRecord.DataBeanX.DataBean> {
    public DetailRecordAdapter() {
        super(R.layout.layout_item_detailrecord);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, DetailRecord.DataBeanX.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_time, model.getCreate_time());
        BigDecimal bigDecimal = new BigDecimal(model.getBalance());
        holder.text(R.id.tv_item_money, bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP)+"");
        holder.text(R.id.tv_item_description, model.getNote());
    }
}
