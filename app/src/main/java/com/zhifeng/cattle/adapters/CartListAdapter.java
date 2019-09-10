package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.icu.util.EthiopicCalendar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.CartListDto;

/**
  *
  * @ClassName:     购物车列表适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/10 12:31
  * @Version:        1.0
 */

public class CartListAdapter extends BaseRecyclerAdapter<CartListDto.DataBean> {
    Context context;

    OnClickListener onClickListener;

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public CartListAdapter(Context context) {
        super(R.layout.layout_item_cart);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, CartListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_goods_name,model.getGoods_name());//商品名称
        holder.text(R.id.tv_item_goods_price,"￥"+model.getGoods_price());//商品价格
        ImageView goodsImg = holder.itemView.findViewById(R.id.iv_item_goods_img);
        GlideUtil.setImage(context,model.getImg(),goodsImg);//商品图片

        /************************************商品数量  start*************************************************/
        EditText editText = holder.itemView.findViewById(R.id.et_item_goods_num);
        editText.setText(model.getGoods_num()+"");

        int inventory = model.getInventory();

        /**
         * 输入框监听
         */
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(editText.getText().toString())){
                    editText.setText("1");
                    model.setGoods_num(1);
                }else {
                    int goodsNum = Integer.parseInt(editText.getText().toString());
                    if (goodsNum <= 0){
                        editText.setText("1");
                        model.setGoods_num(1);
                        Toast.makeText(context, ResUtil.getString(R.string.cart_tab_7), Toast.LENGTH_SHORT).show();
                    }else if (goodsNum > inventory){
                        model.setGoods_num(inventory);
                        editText.setText(inventory+"");
                        Toast.makeText(context, ResUtil.getString(R.string.cart_tab_8), Toast.LENGTH_SHORT).show();
                    }else {
                        model.setGoods_num(goodsNum);
                    }
                    L.e("lgh_cart","onTextChanged = "+model.getGoods_num());
                    onClickListener.goodsNumListener();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        TextView subtract = holder.itemView.findViewById(R.id.tv_item_goods_subtract);
        TextView add = holder.itemView.findViewById(R.id.tv_item_goods_add);

        /**
         * 减
         */
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (model.getGoods_num() == 1){
                    model.setGoods_num(1);
                    editText.setText(model.getGoods_num());
                    Toast.makeText(context, ResUtil.getString(R.string.cart_tab_7), Toast.LENGTH_SHORT).show();
                }else {
                    int num = model.getGoods_num()-1;
                    model.setGoods_num(num);
                    editText.setText(num+"");
                }
                L.e("lgh_cart","subtract = "+model.getGoods_num());
            }
        });

        /**
         * 加
         */
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (model.getGoods_num() >= inventory){
                    Toast.makeText(context, ResUtil.getString(R.string.cart_tab_8), Toast.LENGTH_SHORT).show();
                }else {
                    int num = model.getGoods_num()+1;
                    model.setGoods_num(num);
                    editText.setText(model.getGoods_num()+"");
                }
                L.e("lgh_cart","add = "+model.getGoods_num());
            }
        });
        /***********************************商品数量 end*****************************************/

        ImageView imageView = holder.itemView.findViewById(R.id.iv_item_goods);
        imageView.setSelected(model.getSelected() == 1);//商品是否选中

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.selectedListener(model.getCart_id());
            }
        });

    }

    public interface OnClickListener{
        void selectedListener(int id);
        void goodsNumListener();
    }
}
