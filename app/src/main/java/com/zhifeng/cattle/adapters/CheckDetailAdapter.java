package com.zhifeng.cattle.adapters;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.CheckDetail;

public class CheckDetailAdapter extends BaseRecyclerAdapter<CheckDetail.DataBeanX.DataBean> {
    int log_type;
    public CheckDetailAdapter(int log_type) {
        super(R.layout.layout_item_checkdetail);
        this.log_type =  log_type;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, CheckDetail.DataBeanX.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tvNote, model.getNote());
        holder.text(R.id.tvCreateTime, model.getCreate_time());
        String type = log_type == 1?"-":"";
        holder.text(R.id.tvBalance, type+String.valueOf(model.getBalance()));
    }
}
