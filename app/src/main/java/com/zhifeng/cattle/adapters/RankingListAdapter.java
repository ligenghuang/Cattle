package com.zhifeng.cattle.adapters;

import android.widget.ImageView;

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
        holder.text(R.id.tv_item_placing, String.valueOf(model.getNo()));
        holder.text(R.id.tv_item_user, model.getRealname());
        ImageView iv = holder.itemView.findViewById(R.id.ivUser);
        GlideUtil.setImageCircle(holder.itemView.getContext(), model.getAvatar(), iv, R.mipmap.touxiang);
        holder.text(R.id.tv_item_money, model.getTotal());
        holder.text(R.id.tv_item_Difference_Last, ResUtil.getFormatString(R.string.rankinglist_different_last, String.valueOf(model.getDifferent_last())));
    }
}
