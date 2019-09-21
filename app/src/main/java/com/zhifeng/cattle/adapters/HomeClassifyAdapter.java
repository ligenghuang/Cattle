package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.Catenav2Bean;

/**
 * @ClassName: 首页二级分类列表
 * @Description:
 * @Author: lgh
 * @CreateDate: 2019/9/21 9:35
 * @Version: 1.0
 */

public class HomeClassifyAdapter extends BaseRecyclerAdapter<Catenav2Bean> {
    Context context;

    ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public HomeClassifyAdapter(Context context) {
        super(R.layout.layout_item_recmment_classify);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, Catenav2Bean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_classify_name, model.getCat_name());
        ImageView IMG = holder.itemView.findViewById(R.id.iv_item_classify_img);
        GlideUtil.setImageCircle(context,model.getImg(),IMG,R.drawable.icon_recommend_classify);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击事件
                clickListener.onClick(model.getCat_id());
            }
        });
    }

    public interface ClickListener{
        void onClick(int id);
    }


}
