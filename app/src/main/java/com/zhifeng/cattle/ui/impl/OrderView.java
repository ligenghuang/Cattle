package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.OrderListDto;

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

}
