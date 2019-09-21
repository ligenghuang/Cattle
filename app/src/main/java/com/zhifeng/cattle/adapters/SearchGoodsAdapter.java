package com.zhifeng.cattle.adapters;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.SearchGoods;

public class SearchGoodsAdapter extends BaseRecyclerAdapter<SearchGoods.DataBean> {

    public SearchGoodsAdapter() {
        super(R.layout.layout_item_search_goods);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, SearchGoods.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_goods_name, model.getGoods_name());
    }
}
