package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.lgh.huanglib.util.config.GlideUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.BankImgListDto;
import com.zhifeng.cattle.modules.BankListDto;

import java.util.ArrayList;
import java.util.List;

/**
  *
  * @ClassName:     已绑定银行卡列表适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/27 12:00
  * @Version:        1.0
 */

public class BankListAdapter extends BaseRecyclerAdapter<BankListDto.DataBean> {
    Context context;

    OnClickListener onClickListener;
    List<BankImgListDto.DataBean> dataBeanList = new ArrayList<>();

    public void setDataBeanList(List<BankImgListDto.DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public BankListAdapter(Context context) {
        super(R.layout.layout_item_bank);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, BankListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        String num = model.getBank_card().substring(model.getBank_card().length()-4,model.getBank_card().length());
        String name = model.getBank_name()+"("+num+")";
        holder.text(R.id.tv_bank_name,name);//银行卡名
        ImageView img = holder.itemView.findViewById(R.id.iv_bank);
        for (int i = 0; i < dataBeanList.size(); i++) {
            if (model.equals(dataBeanList.get(i).getName())){
                GlideUtil.setImageCircle(context,dataBeanList.get(i).getImg(),img,R.mipmap.logo);
            }
        }

        ImageView imageView = holder.itemView.findViewById(R.id.iv_item_bank);
        imageView.setSelected(model.isClick());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.OnClcik(model);
            }
        });
    }

    public interface OnClickListener{
        void OnClcik(BankListDto.DataBean model);
    }
}
