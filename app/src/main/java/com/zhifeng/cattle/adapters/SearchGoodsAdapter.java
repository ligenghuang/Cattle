package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.SearchGoods;
/**
 * @ClassName:
 * @Description: 搜索列表页
 * @Author: Administrator
 * @Date: 2019/9/23 12:23
 */
public class SearchGoodsAdapter extends BaseRecyclerAdapter<SearchGoods.DataBean> {
    private Context context;

    public SearchGoodsAdapter(Context context) {
        super(R.layout.layout_item_other_goods);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, SearchGoods.DataBean model, int position) {
        holder.setIsRecyclable(false);
        ImageView iv = holder.itemView.findViewById(R.id.iv_item_goods_img);
        GlideUtil.setImageCircle(context, model.getPicture(), iv, R.drawable.icon_goods);
        holder.text(R.id.tv_item_goods_name, model.getGoods_name());
        holder.text(R.id.tv_item_goods_price, model.getPrice());
        holder.text(R.id.tv_item_goods_original_price, "");
    }
}
