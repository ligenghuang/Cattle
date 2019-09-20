package com.zhifeng.cattle.adapters;

import android.widget.ImageView;
import android.widget.RadioButton;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.Temporary;

public class PayTypeAdapter extends BaseRecyclerAdapter<Temporary.DataBean.PayTypeBean> {

    public PayTypeAdapter() {
        super(R.layout.layout_item_paytype);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, Temporary.DataBean.PayTypeBean model, int position) {
        holder.setIsRecyclable(false);
        ImageView iv = holder.itemView.findViewById(R.id.ivPayType);
        int pay_type = model.getPay_type();
        if (pay_type == 1) {
            iv.setImageResource(model.isSelect() ? R.drawable.yueweizhifu : R.drawable.yueweizhifu);
        } else if (pay_type == 3) {
            iv.setImageResource(model.isSelect() ? R.drawable.zhifubaoweizhifu : R.drawable.zhifubaoweizhifu);
        } else {
            iv.setImageResource(model.isSelect() ? R.drawable.weixinzhifu : R.drawable.weixinweizhifu);
        }
        holder.text(R.id.tvPayType, model.getPay_name());
        RadioButton rb = holder.itemView.findViewById(R.id.rbPayType);
        rb.setChecked(model.isSelect());
        rb.setOnClickListener(v -> {
            for (Temporary.DataBean.PayTypeBean bean : getAllData()) {
                bean.setSelect(false);
            }
            model.setSelect(true);
            notifyDataSetChanged();
        });
    }
}
