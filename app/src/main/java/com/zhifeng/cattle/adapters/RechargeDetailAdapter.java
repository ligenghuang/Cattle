package com.zhifeng.cattle.adapters;

import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.ReChargeDetail;

import java.text.DecimalFormat;

public class RechargeDetailAdapter extends BaseRecyclerAdapter<ReChargeDetail.DataBeanX.DataBean> {
    public RechargeDetailAdapter() {
        super(R.layout.layout_item_rechargedetail);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, ReChargeDetail.DataBeanX.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_rechargeTime, model.getCreate_time());
        DecimalFormat df = new DecimalFormat("#0.00");
        holder.text(R.id.tv_item_money,"AU$"+df.format(model.getBalance()));
        //todo 0充值成功 1充值失败
        switch (model.getStatus()){
            case 0:
                holder.text(R.id.tv_item_rechargeStatus, ResUtil.getString(R.string.team_rechargedetail_tab_5));
                break;
            case 1:
                holder.text(R.id.tv_item_rechargeStatus,ResUtil.getString(R.string.team_rechargedetail_tab_6));
                break;
        }
//
    }
}
