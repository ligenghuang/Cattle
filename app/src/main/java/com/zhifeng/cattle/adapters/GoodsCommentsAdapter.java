package com.zhifeng.cattle.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.GoodsComment;
import com.zhifeng.cattle.utils.data.DynamicTimeFormat;

import java.util.List;

public class GoodsCommentsAdapter extends BaseRecyclerAdapter<GoodsComment.DataBean> {

    public GoodsCommentsAdapter() {
        super(R.layout.layout_item_goodscomments);
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, GoodsComment.DataBean model, int position) {
        holder.setIsRecyclable(false);
        ImageView ivAvatar = holder.itemView.findViewById(R.id.ivAvatar);
        GlideUtil.setImageCircle(holder.itemView.getContext(), model.getAvatar(), ivAvatar, R.mipmap.touxiang);
        holder.text(R.id.tv_Mobile, model.getMobile());
        holder.text(R.id.tv_AddTime, DynamicTimeFormat.LongToString5(model.getAdd_time()));
        holder.text(R.id.tv_Comment, model.getContent());
        LinearLayout llImage = holder.itemView.findViewById(R.id.llImage);
        List<String> img = model.getImg();
        llImage.setVisibility(img.size() > 0 ? View.VISIBLE : View.GONE);
        if (img.size() > 0) {
            ImageView iv1 = holder.itemView.findViewById(R.id.iv1);
            GlideUtil.setImage(holder.itemView.getContext(), img.get(0), iv1, R.drawable.icon_comment_img);
        }
        if (img.size() > 1) {
            ImageView iv2 = holder.itemView.findViewById(R.id.iv2);
            GlideUtil.setImage(holder.itemView.getContext(), img.get(1), iv2, R.drawable.icon_comment_img);
        }
        if (img.size() > 2) {
            ImageView iv3 = holder.itemView.findViewById(R.id.iv3);
            GlideUtil.setImage(holder.itemView.getContext(), img.get(2), iv3, R.drawable.icon_comment_img);
        }
    }
}
