package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.AlipayOrderDto;
import com.zhifeng.cattle.modules.PayOrderDto;
import com.zhifeng.cattle.modules.ShowIdCardDto;
import com.zhifeng.cattle.modules.SubmitOrderDto;
import com.zhifeng.cattle.modules.Temporary;
import com.zhifeng.cattle.modules.WxPayOrderDto;
import com.zhifeng.cattle.modules.post.SubmitOrderPost;

/**
  *
  * @ClassName:     提交订单
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/21 17:46
  * @Version:        1.0
 */

public interface TemporaryView extends BaseView {
    /**
     * 获取订单信息
     */
    void getTemporary();
    void getTemporarySuccess(Temporary temporary);
    /**
     * 显示身份认证信息
     */
    void showIdCard();
    void showIdCardSuccess(ShowIdCardDto showIdCardDto);
    /**
     * 提交订单
     */
    void submitOrder(SubmitOrderPost submitOrderPost);
    void submitOrderSuccess(SubmitOrderDto submitOrderDto);

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

    /**
     * 微信支付
     */
    void wxPaySuccess(WxPayOrderDto wxPayOrderDto);
    void wxPayErroe();
}
