package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.CollectionListDto;
import com.zhifeng.cattle.ui.home.GoodsDetailActivity;

/**
  *
  * @ClassName:     关注列表适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/11 11:25
  * @Version:        1.0
 */

public class CollectionListAdapter extends BaseRecyclerAdapter<CollectionListDto.DataBean> {
    Context context;

    boolean isManagement = false;

    OnClickListener onClickListener;

    public CollectionListAdapter(Context context) {
        super(R.layout.layout_item_collection);
        this.context = context;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /**
     * 是否显示选择按钮
     * @param management
     */
    public void setManagement(boolean management) {
        isManagement = management;

    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, CollectionListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_goods_name,model.getGoods_name());
        holder.text(R.id.tv_item_goods_price,"￥"+model.getPrice());

        ImageView img = holder.itemView.findViewById(R.id.iv_item_goods_img);
        GlideUtil.setImage(context,model.getImg(),img);

        ImageView imageView = holder.itemView.findViewById(R.id.iv_item_goods);
        imageView.setVisibility(isManagement? View.VISIBLE:View.GONE);
        imageView.setSelected(model.isClick());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (isManagement){
                   onClickListener.onClick(model.getGoods_id());
               }else {
                   //todo 跳转至商品详情页
                   Intent intent = new Intent(context, GoodsDetailActivity.class);
                   intent.putExtra("goods_id",model.getGoods_id());
                   context.startActivity(intent);
               }
            }
        });
    }

    public interface OnClickListener{
        void onClick(int id);
    }
}
