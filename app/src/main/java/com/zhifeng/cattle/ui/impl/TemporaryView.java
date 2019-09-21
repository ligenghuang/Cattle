package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.SubmitOrderDto;
import com.zhifeng.cattle.modules.Temporary;
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
     * 提交订单
     */
    void submitOrder(SubmitOrderPost submitOrderPost);
    void submitOrderSuccess(SubmitOrderDto submitOrderDto);
}
