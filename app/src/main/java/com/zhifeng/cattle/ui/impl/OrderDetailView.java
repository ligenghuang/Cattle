package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.AlipayOrderDto;
import com.zhifeng.cattle.modules.OrderDetailDto;
import com.zhifeng.cattle.modules.PayOrderDto;
import com.zhifeng.cattle.modules.PayTypeDto;
import com.zhifeng.cattle.modules.post.SubmitOrderPost;

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

    void getPayType();
    void getPayTypeSuccess(PayTypeDto payTypeDto);

    /**
     * 支付
     */
    void payOrder(SubmitOrderPost submitOrderPost);
    void payOrderSuccess(PayOrderDto submitOrderDto);
    void payOrderError(String msg);

    /**
     * 支付宝支付
     */
    void aliPaySuccess(AlipayOrderDto alipayOrderDto);
    void aliPayErroe();
}
