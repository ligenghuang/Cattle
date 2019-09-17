package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.CategoryListDto;
import com.zhifeng.cattle.ui.home.GoodsDetailActivity;

/**
  *
  * @ClassName:     分类列表商品列表适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/10 10:54
  * @Version:        1.0
 */

public class CategoryListGoodsAdapter extends BaseRecyclerAdapter<CategoryListDto.DataBean.GoodsBean> {
    Context context;

    public CategoryListGoodsAdapter(Context context) {
        super(R.layout.layout_item_level_goods);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, CategoryListDto.DataBean.GoodsBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_goods_name,model.getGoods_name());
        ImageView img = holder.itemView.findViewById(R.id.iv_item_goods);
        GlideUtil.setImage(context,model.getImg(),img,R.drawable.icon_goods);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
