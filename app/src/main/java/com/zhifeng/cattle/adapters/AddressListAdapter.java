package com.zhifeng.cattle.adapters;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.AddressListDto;

/**
  *
  * @ClassName:     地址管理
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/12 16:01
  * @Version:        1.0
 */

public class AddressListAdapter extends BaseRecyclerAdapter<AddressListDto.DataBean> {
    OnClickListener onClickListener;
    boolean isGoods;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public AddressListAdapter(boolean isGoods) {
        super(R.layout.layout_item_address);
        this.isGoods = isGoods;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, AddressListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_consignee,model.getConsignee());//收货人
        holder.text(R.id.tv_address_phone,model.getMobile());//联系电话
        holder.text(R.id.tv_item_address,model.getP_cn()+model.getC_cn()+model.getD_cn()+" "+model.getAddress());//详细地址

        TextView tvDefault = holder.itemView.findViewById(R.id.tv_item_default);
        tvDefault.setVisibility(model.getIs_default()==1? View.VISIBLE:View.GONE);
        ImageView img = holder.itemView.findViewById(R.id.iv_item_address);
        img.setSelected(model.getIs_default() == 1);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGoods){
                    onClickListener.itemView(model);
                }
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getIs_default() != 1){
                    onClickListener.Is_default(model.getAddress_id());
                }
            }
        });

        holder.itemView.findViewById(R.id.tv_address_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.edit(model.getAddress_id());
            }
        });
        holder.itemView.findViewById(R.id.tv_address_detele).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.Detele(model.getAddress_id());
            }
        });

    }

    public interface OnClickListener{
        void edit(int id);
        void Detele(int id);
        void Is_default(int id);
        void itemView(AddressListDto.DataBean model);
    }
}
