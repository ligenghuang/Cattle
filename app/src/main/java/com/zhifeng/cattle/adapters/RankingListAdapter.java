package com.zhifeng.cattle.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.RankingList;

public class RankingListAdapter extends BaseRecyclerAdapter<RankingList.DataBean> {
    public RankingListAdapter() {
        super(R.layout.layout_item_rankinglist);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, RankingList.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_placing, ResUtil.getFormatString(R.string.rankinglist_tab_6,String.valueOf(model.getNo())));
        holder.text(R.id.tv_item_user, model.getRealname());
        ImageView iv = holder.itemView.findViewById(R.id.ivUser);
        GlideUtil.setImageCircle(holder.itemView.getContext(), model.getAvatar(), iv, R.mipmap.touxiang);
        holder.text(R.id.tv_item_money, model.getTotal());
        holder.text(R.id.tv_item_Difference_Last, ResUtil.getFormatString(R.string.rankinglist_different_last, String.valueOf(model.getDifferent_last())));
        if (position == 0){
            TextView tvPlacing = holder.itemView.findViewById(R.id.tv_item_placing);
            TextView tvUser = holder.itemView.findViewById(R.id.tv_item_user);
            TextView tvMoney = holder.itemView.findViewById(R.id.tv_item_money);
            TextView tvDifferenceLast = holder.itemView.findViewById(R.id.tv_item_Difference_Last);
            tvUser.setTextColor(ResUtil.getColor(R.color.white));
            tvMoney.setTextColor(ResUtil.getColor(R.color.white));
            tvDifferenceLast.setTextColor(ResUtil.getColor(R.color.white));
            tvPlacing.setTextColor(ResUtil.getColor(R.color.white));
            LinearLayout linearLayout = holder.itemView.findViewById(R.id.linearLayout);
            linearLayout.setVisibility(View.VISIBLE);
            View v_line = holder.itemView.findViewById(R.id.v_line);
            v_line.setVisibility(View.GONE);
            ImageView iv_no = holder.itemView.findViewById(R.id.iv_no);
            iv_no.setVisibility(View.VISIBLE);
        }
    }
}
