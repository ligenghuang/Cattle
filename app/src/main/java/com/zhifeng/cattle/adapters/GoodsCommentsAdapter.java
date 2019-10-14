package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.GoodsComment;
import com.zhifeng.cattle.utils.data.DynamicTimeFormat;

public class GoodsCommentsAdapter extends BaseRecyclerAdapter<GoodsComment.DataBeanX.DataBean> {
    private Context context;

    public GoodsCommentsAdapter(Context context) {
        super(R.layout.layout_item_goodscomments);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, GoodsComment.DataBeanX.DataBean model, int position) {
        holder.setIsRecyclable(false);
        ImageView ivAvatar = holder.itemView.findViewById(R.id.ivAvatar);
        GlideUtil.setImageCircle(context, model.getAvatar(), ivAvatar, R.mipmap.touxiang);
        holder.text(R.id.tv_Mobile, model.getMobile());
//        long time = model.getAdd_time() * (long) 1000;
        holder.text(R.id.tv_AddTime, model.getAdd_time());
        holder.text(R.id.tv_Comment, model.getContent());
        RecyclerView rv=holder.itemView.findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(context,4));
        CommentImgAdapter adapter = new CommentImgAdapter(context);
        adapter.refresh(model.getImg());
        rv.setAdapter(adapter);
    }
}
