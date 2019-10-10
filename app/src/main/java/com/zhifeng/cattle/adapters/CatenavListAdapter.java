package com.zhifeng.cattle.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.Catenav2Bean;
import com.zhifeng.cattle.modules.RecommendHomeDto;

import java.util.List;

/**
  *
  * @ClassName:     popup弹窗中的列表适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/21 15:09
  * @Version:        1.0
 */

public class CatenavListAdapter extends BaseRecyclerAdapter<RecommendHomeDto.DataBean.Catenav1Bean> {

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public CatenavListAdapter() {
        super(R.layout.layout_item_popup_classify);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, RecommendHomeDto.DataBean.Catenav1Bean model, int position) {
        holder.setIsRecyclable(false);
        ImageView img = holder.itemView.findViewById(R.id.iv_logo);
        TextView textView = holder.itemView.findViewById(R.id.tv_item_name);
        img.setVisibility(position == 0? View.VISIBLE :View.GONE);
        textView.setVisibility(position == 0?View.GONE:View.VISIBLE);
        textView.setText(model.getCat_name());
        textView.setSelected(model.isOnClick());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(model.getCat_id(),model.getCat_name(),position - 1);
            }
        });
    }

    public interface OnClickListener{
        void onClick(int id,String name,int position);
    }


}
