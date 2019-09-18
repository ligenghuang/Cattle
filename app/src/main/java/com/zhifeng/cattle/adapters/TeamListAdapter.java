package com.zhifeng.cattle.adapters;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.TeamList;

public class TeamListAdapter extends BaseRecyclerAdapter<TeamList.DataBeanX.DataBean> {
    private OnClickListener onClickListener;

    public TeamListAdapter() {
        super(R.layout.layout_item_teamlist);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, TeamList.DataBeanX.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_userId, String.valueOf(model.getId()));
        holder.text(R.id.tv_item_name, model.getRealname());
        setViewClickListener(holder, model);
    }

    private void setViewClickListener(SmartViewHolder smartViewHolder, TeamList.DataBeanX.DataBean model) {
        smartViewHolder.itemView.findViewById(R.id.tv_item_lookOver).setOnClickListener(v -> {
            if (onClickListener != null) {
                onClickListener.onClick(model.getId());
            }
        });
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int id);
    }
}
