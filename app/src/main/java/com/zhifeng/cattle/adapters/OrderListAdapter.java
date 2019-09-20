package com.zhifeng.cattle.adapters;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lgh.huanglib.util.data.ResUtil;
import com.zhifeng.cattle.R;
import com.zhifeng.cattle.modules.OrderListDto;

import java.util.List;

/**
  *
  * @ClassName:     我的订单列表适配器
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/11 15:11
  * @Version:        1.0
 */

public class OrderListAdapter extends BaseRecyclerAdapter<OrderListDto.DataBean> {
    Context context;
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public OrderListAdapter(Context context) {
        super(R.layout.layout_item_order);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(SmartViewHolder holder, OrderListDto.DataBean model, int position) {
        holder.setIsRecyclable(false);
        holder.text(R.id.tv_item_order_time,model.getAdd_time());//订单时间
        holder.text(R.id.tv_item_order_goods_num, ResUtil.getFormatString(R.string.order_tab_9,model.getGoods_num()+""));
        holder.text(R.id.tv_item_order_price_total,"￥"+model.getTotal_amount());

        //设置商品
        RecyclerView recyclerView = holder.itemView.findViewById(R.id.rv_item_order_goods);
        OrderGoodsListAdapter orderGoodsListAdapter = new OrderGoodsListAdapter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(orderGoodsListAdapter);
        orderGoodsListAdapter.refresh(model.getGoods());

        //设置状态及底部按钮
        TextView TvType = holder.itemView.findViewById(R.id.tv_item_order_type);
        LinearLayout llOrder_1 = holder.itemView.findViewById(R.id.ll_item_order_1);
        LinearLayout llOrder_2 = holder.itemView.findViewById(R.id.ll_item_order_2);
        LinearLayout llOrder_3 = holder.itemView.findViewById(R.id.ll_item_order_3);
        LinearLayout llOrder_4 = holder.itemView.findViewById(R.id.ll_item_order_4);
        LinearLayout llOrder_5 = holder.itemView.findViewById(R.id.ll_item_order_5);
        setType(model.getOrder_status(),model.getComment(),model.getPay_status(),model.getShipping_status(),
                TvType,llOrder_1,llOrder_2,llOrder_3,llOrder_4,llOrder_5);

        setClickListener(holder,model);

    }

    /**
     * 设置点击监听
     * @param holder
     * @param model
     */
    private void setClickListener(SmartViewHolder holder, OrderListDto.DataBean model) {
        //查看详情
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(model.getOrder_id());
            }
        });
        //删除订单
        holder.itemView.findViewById(R.id.tv_item_order_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int status = model.getOrder_status() == 2?4:5;
                onClickListener.Delete(model.getOrder_id(),status);
            }
        });
        /******************************查看物流**************************************/
        holder.itemView.findViewById(R.id.tv_item_order_logistics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.Logistics(model.getOrder_id());
            }
        });
        holder.itemView.findViewById(R.id.tv_item_order_logistics_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.Logistics(model.getOrder_id());
            }
        });
        holder.itemView.findViewById(R.id.tv_item_order_logistics_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.Logistics(model.getOrder_id());
            }
        });
        /***************************** end ***************************************/

        //取消订单
        holder.itemView.findViewById(R.id.tv_item_order_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.Cancel(model.getOrder_id());
            }
        });

        //去付款
        holder.itemView.findViewById(R.id.tv_item_order_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.Pay(model.getOrder_id());
            }
        });

        //退款
        holder.itemView.findViewById(R.id.tv_item_order_refund).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.Refund(model.getOrder_id());
            }
        });

        //确定收货
        holder.itemView.findViewById(R.id.tv_item_order_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.Confirm(model.getOrder_id());
            }
        });
        //去评价
        holder.itemView.findViewById(R.id.tv_item_order_evaluation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.evaluation(model.getOrder_id(),model.getGoods());
            }
        });
    }

    /**
     * 设置订单状态
     * @param Order_status     订单状态0 待确认 1已确认 2已收货 3已取消 4已完成 5已作废 6申请退款 7已退款 8拒绝退款
     * @param comment          0 未评论 1已评论
     * @param pay_status       支付状态 0未支付 1已支付 2部分支付 3已退款 4拒绝退款
     * @param shipping_status  发货状态 0未发货 1已发货 2部分发货 3已收货 4退货
     * @param tvType
     * @param llOrder_1
     * @param llOrder_2
     * @param llOrder_3
     * @param llOrder_4
     * @param llOrder_5
     */
    private void setType(int Order_status,int comment,int pay_status,int shipping_status, TextView tvType, LinearLayout llOrder_1,
                         LinearLayout llOrder_2, LinearLayout llOrder_3,
                         LinearLayout llOrder_4, LinearLayout llOrder_5) {
        llOrder_1.setVisibility(View.GONE);
        llOrder_2.setVisibility(View.GONE);
        llOrder_3.setVisibility(View.GONE);
        llOrder_4.setVisibility(View.GONE);
        llOrder_5.setVisibility(View.GONE);
        int text = R.string.order_tab_10;
       if (pay_status == 0){
           //todo 未付款
           llOrder_2.setVisibility(View.VISIBLE);
           text = R.string.my_tab_5;
       }else {
           switch (shipping_status){
               case 0:
                   //todo 待发货
                   llOrder_3.setVisibility(View.VISIBLE);
                   text = R.string.my_tab_6;
                   break;
               case 1:
                   //todo 待收货
                   llOrder_4.setVisibility(View.VISIBLE);
                   text = R.string.my_tab_7;
                   break;
               case 3:
                   //todo 已收货
                   if (comment == 0){
                       //todo 待评价
                       llOrder_5.setVisibility(View.VISIBLE);
                       text = R.string.my_tab_8;
                   }else {
                       //todo 交易完成
                       llOrder_1.setVisibility(View.VISIBLE);
                       text = R.string.order_tab_10;
                   }
                   break;
           }
           tvType.setText(ResUtil.getString(text));
       }
    }

    public interface OnClickListener{
        void onClick(int id);
        void Delete(int id,int status);//删除订单
        void Logistics(int id);//查看物流
        void Cancel(int id);//取消订单
        void Pay(int id);//付款
        void Refund(int id);//退款
        void Confirm(int id);//确定收货
        void evaluation(int orderId, List<OrderListDto.DataBean.GoodsBean> goodsBeans);//去评价
    }
}
