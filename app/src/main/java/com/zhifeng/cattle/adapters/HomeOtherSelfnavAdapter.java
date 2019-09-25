package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.HomeOtherSelfnavBean;
import com.zhifeng.cattle.ui.home.AdvertisingActivity;

/**
  *
  * @ClassName:     首页进口货物和食品酒水 广告列表适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/21 11:42
  * @Version:        1.0
 */

public class HomeOtherSelfnavAdapter extends BaseRecyclerAdapter<HomeOtherSelfnavBean> {
    Context context;
    public HomeOtherSelfnavAdapter(Context context) {
        super(R.layout.layout_item_other_selfnav);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, HomeOtherSelfnavBean model, int position) {
        holder.setIsRecyclable(false);
        ImageView img = holder.itemView.findViewById(R.id.iv_other_selfnav_img);
        GlideUtil.setImage(context,model.getImage(),img,R.drawable.icon_goods);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 跳转至广告页
                Intent intent = new Intent(context, AdvertisingActivity.class);
                intent.putExtra("url",model.getUrl());
                intent.putExtra("title",model.getTitle());
                context.startActivity(intent);
            }
        });
    }
}
