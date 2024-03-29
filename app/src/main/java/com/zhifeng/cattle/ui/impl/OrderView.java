package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.OrderListDto;
import com.zhifeng.cattle.modules.PayOrderDto;
import com.zhifeng.cattle.modules.SubmitOrderDto;
import com.zhifeng.cattle.modules.post.SubmitOrderPost;

/**
  *
  * @ClassName:     我的订单
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/11 14:54
  * @Version:        1.0
 */

public interface OrderView extends BaseView {

    /**
     * 获取订单列表
     */
    void refresOrderList();
//    void loadmoreOrderList();
    void getOrderListSuccess(OrderListDto orderListDto);

    /**
     * 修改订单状态
     * @param id
     * @param status
     */
    void editOrderStatus(int id,int status);
    void editOrderStatusSuccess(String msg,int status);

    /**
     * 提交订单
     */
    void submitOrder(SubmitOrderPost submitOrderPost);
    void submitOrderSuccess(PayOrderDto submitOrderDto);

}
