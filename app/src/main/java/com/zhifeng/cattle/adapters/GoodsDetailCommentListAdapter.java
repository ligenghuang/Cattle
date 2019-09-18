package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.GoodsDetailDto;

/**
  *
  * @ClassName:     商品详情页评价列表适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/18 14:15
  * @Version:        1.0
 */

public class GoodsDetailCommentListAdapter extends BaseRecyclerAdapter<GoodsDetailDto.DataBean.CommentlistBean> {
    Context context;
    public GoodsDetailCommentListAdapter(Context context) {
        super(R.layout.layout_item_comment);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, GoodsDetailDto.DataBean.CommentlistBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_comment_name,model.getRealname());//用户昵称
        holder.text(R.id.tv_item_comment_content,model.getContent());//评价内容
        holder.text(R.id.tv_item_comment_time,model.getAdd_time());//评价时间
        RecyclerView recyclerView = holder.itemView.findViewById(R.id.rv_item_comment_img);//评价图片列表
        CommentImgAdapter commentImgAdapter = new CommentImgAdapter(context);
        recyclerView.setLayoutManager(new GridLayoutManager(context,4));
        recyclerView.setAdapter(commentImgAdapter);
        if (model.getImg().size() != 0){
            recyclerView.setVisibility(View.VISIBLE);
            commentImgAdapter.refresh(model.getImg());
        }else {
            recyclerView.setVisibility(View.GONE);
        }

    }
}
