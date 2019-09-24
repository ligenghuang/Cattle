package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.OrderDetailDto;
import com.zhifeng.cattle.modules.RefundDto;

public interface RefundView extends BaseView {
    void getOrderDetail();

    void getOrderDetailSuccess(OrderDetailDto orderDetailDto);

    void applyRefund();

    void applyRefundSuccess(RefundDto refundDto);
}
