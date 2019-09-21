package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.LoginUser;
import com.zhifeng.cattle.utils.data.MySp;

/**
  *
  * @ClassName:     用户列表
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/21 16:10
  * @Version:        1.0
 */


public class UserListAdapter extends BaseRecyclerAdapter<LoginUser>{
    Context context;

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public UserListAdapter(Context context) {
        super(R.layout.layout_item_user);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, LoginUser model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_name,model.getRealname());
        TextView tvIsLogin = holder.itemView.findViewById(R.id.tv_item_login);
        boolean b = model.getToken().equals(MySp.getAccessToken(context));
        tvIsLogin.setVisibility(b? View.VISIBLE:View.GONE);
        L.e("lgh_user","model  = "+model.toString());
        L.e("lgh_user","model  = "+MySp.getAccessToken(context));
        ImageView imageView = holder.itemView.findViewById(R.id.iv_avatar);
        GlideUtil.setImageCircle(context,model.getAvatar(),imageView,R.drawable.icon_avatar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.OnClick(model);
            }
        });
    }

    public interface OnClickListener{
        void OnClick(LoginUser dto);
    }

}
