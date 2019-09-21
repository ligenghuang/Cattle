package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.RecommendHomeDto;

/**
  *
  * @ClassName:     首页推荐 立即购买商品列表适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/21 9:46
  * @Version:        1.0
 */

public class RecommenSelfnavAdapter extends BaseRecyclerAdapter<RecommendHomeDto.DataBean.BuyNowBean>{
    Context context;
    public RecommenSelfnavAdapter(Context context) {
        super(R.layout.layout_item_recommend_goods);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, RecommendHomeDto.DataBean.BuyNowBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_goods_name,model.getGoods_name());//商品名称
        holder.text(R.id.tv_goods_price,"￥"+model.getPrice());//商品价格

        ImageView img = holder.itemView.findViewById(R.id.iv_goods_img);
        GlideUtil.setRoundedImage(context,model.getImg(),img,R.drawable.icon_goods,4);
    }
}
