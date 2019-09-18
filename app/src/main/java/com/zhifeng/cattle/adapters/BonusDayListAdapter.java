package com.zhifeng.cattle.adapters;

import android.widget.TextView;

import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.BonusDayDto;

/**
  *
  * @ClassName:     当日累计奖励列表适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/12 14:53
  * @Version:        1.0
 */

public class BonusDayListAdapter extends BaseRecyclerAdapter<BonusDayDto.DataBean.ListBean> {
    public BonusDayListAdapter() {
        super(R.layout.layout_item_bonus_day);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, BonusDayDto.DataBean.ListBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_bonus_day, ResUtil.getFormatString(R.string.bonus_day_tab_3,model.getRealname(),model.getNum()+""));
        int res = R.color.color_3;
        switch (position){
            case 0:
                res = R.color.color_F4B00E;
                break;
            case 1:
                res = R.color.color_A8ACAC;
                break;
            case 3:
                res = R.color.color_B06A31;
                break;
            default:
                res = R.color.color_3;
                break;
        }
        TextView textView = holder.itemView.findViewById(R.id.tv_item_bonus_day_no);
        textView.setTextColor(ResUtil.getColor(res));
        int no = position + 1;
        textView.setText("NO."+no);
    }
}
