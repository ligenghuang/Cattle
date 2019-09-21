package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.RecommendHomeDto;
import com.zhifeng.cattle.modules.SpreeDto;

/**
  *
  * @ClassName:     首页推荐优选单品列表适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/21 10:03
  * @Version:        1.0
 */

public class HomeSpreeAdapter extends BaseRecyclerAdapter<SpreeDto> {
    Context context;

    public HomeSpreeAdapter(Context context) {
        super(R.layout.layout_item_spree);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, SpreeDto model, int position) {
        holder.setIsRecyclable(false);
        ImageView img = holder.itemView.findViewById(R.id.iv_item_spree_img);
        GlideUtil.setImage(context,model.getImage(),img,R.drawable.icon_spree_bg);
    }
}
