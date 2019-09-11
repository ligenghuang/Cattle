package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.OrderDetailDto;

/**
  *
  * @ClassName:     订单详情
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/11 16:56
  * @Version:        1.0
 */

public interface OrderDetailView extends BaseView {
    /**
     * 订单详情
     */
    void getOrderDetail();
    void getOrderDetailSuccess(OrderDetailDto orderDetailDto);
}
