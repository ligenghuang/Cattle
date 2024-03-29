package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.CategoryListDto;
import com.zhifeng.cattle.ui.home.GoodsDetailActivity;

/**
  *
  * @ClassName:     商品分类适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/10 10:47
  * @Version:        1.0
 */

public class CategoryAdapter extends BaseRecyclerAdapter<CategoryListDto.DataBean> {
    Context context;

    public CategoryAdapter(Context context) {
        super(R.layout.layout_item_level_2);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, CategoryListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_lever_title,model.getCat_name());
        CategoryListGoodsAdapter categoryListGoodsAdapter = new CategoryListGoodsAdapter(context);
        RecyclerView recyclerView = holder.itemView.findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(categoryListGoodsAdapter);
        categoryListGoodsAdapter.refresh(model.getGoods());
        CardView cardView = holder.itemView.findViewById(R.id.cardView);
        cardView.setVisibility(model.getGoods().size() == 0? View.GONE:View.VISIBLE);

    }
}
