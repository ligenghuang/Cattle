package com.zhifeng.cattle.adapters;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

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

        ImageView ivPayTypeCircle = holder.itemView.findViewById(R.id.iv);
        if (model.isSelect()){
            ivPayTypeCircle.setImageResource(R.drawable.shape_solid_circle);
        }else{
            ivPayTypeCircle.setImageDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        ivPayTypeCircle.setOnClickListener(v -> {
            for (Temporary.DataBean.PayTypeBean bean : getAllData()) {
                bean.setSelect(false);
            }
            model.setSelect(true);
            notifyDataSetChanged();
        });
    }
}
