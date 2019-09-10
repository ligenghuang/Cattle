package com.zhifeng.cattle.adapters;

import android.view.View;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.CategoryListDto;

import java.util.List;

/**
  *
  * @ClassName:     商品分类列表适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/10 10:35
  * @Version:        1.0
 */

public class CategoryListAdapter extends BaseRecyclerAdapter<CategoryListDto.DataBean> {
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public CategoryListAdapter() {
        super(R.layout.layout_item_level_1);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, CategoryListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_level_1,model.getCat_name());
        holder.itemView.setSelected(model.isClick());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.OnListClick(model.getCat_id(),model);
            }
        });
    }

    public interface OnClickListener{
        void OnListClick(int id,CategoryListDto.DataBean goodsBean);
    }
}
