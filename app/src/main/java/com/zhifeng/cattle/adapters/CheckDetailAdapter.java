package com.zhifeng.cattle.adapters;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.CheckDetail;

public class CheckDetailAdapter extends BaseRecyclerAdapter<CheckDetail.DataBeanX.DataBean> {

    public CheckDetailAdapter() {
        super(R.layout.layout_item_checkdetail);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, CheckDetail.DataBeanX.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tvNote, model.getNote());
        holder.text(R.id.tvCreateTime, model.getCreate_time());
        holder.text(R.id.tvBalance, String.valueOf(model.getBalance()));
    }
}
