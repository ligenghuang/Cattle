package com.zhifeng.cattle.adapters;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.WithdrawalListDto;

/**
  *
  * @ClassName:     提现明细列表适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/17 14:14
  * @Version:        1.0
 */

public class WithdrawalDetailAdapter extends BaseRecyclerAdapter<WithdrawalListDto.DataBeanX.ListBean.DataBean> {
    public WithdrawalDetailAdapter() {
        super(R.layout.layout_item_withdrawal_detail);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, WithdrawalListDto.DataBeanX.ListBean.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_time,model.getCreatetime());//时间
        holder.text(R.id.tv_item_money,model.getMoney());//金额
        holder.text(R.id.tv_item_taxfee,model.getTaxfee());//手续费
        TextView textView = holder.itemView.findViewById(R.id.tv_item_status);
        setStatus(textView,model.getStatus());//状态
        LinearLayout linearLayout = holder.itemView.findViewById(R.id.linearLayout);
        boolean b = (position == 0)||(position%2==0);
        linearLayout.setBackgroundColor(ResUtil.getColor(b?R.color.color_e4f1ee:R.color.color_bfd8d3));//颜色
    }

    /**
     * 状态
     * @param textView
     * @param status	-1不通过审批，1待审批，2通过审批
     */
    private void setStatus(TextView textView, int status) {
        int textRes;
        int colorRes = R.color.color_15;
        switch (status){
            case 1:
                //待审核
                textRes = R.string.withdrawal_detail_tab_7;
                break;
            case 2:
                //通过审核
                textRes = R.string.withdrawal_detail_tab_5;
                break;
            default:
                //不通过审核
                textRes = R.string.withdrawal_detail_tab_6;
                colorRes = R.color.color_f6253c;
                break;
        }
        textView.setText(ResUtil.getString(textRes));
        textView.setTextColor(ResUtil.getColor(colorRes));
    }
}
