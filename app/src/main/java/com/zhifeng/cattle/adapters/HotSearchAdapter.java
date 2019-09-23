package com.zhifeng.cattle.adapters;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.SearchHistory;
/**
 * @ClassName:
 * @Description: 搜索历史
 * @Author: Administrator
 * @Date: 2019/9/23 12:22
 */
public class HotSearchAdapter extends BaseRecyclerAdapter<SearchHistory.DataBean> {
    public HotSearchAdapter() {
        super(R.layout.layout_item_search_goods);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, SearchHistory.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_goods_name, model.getKeyword());
    }
}
