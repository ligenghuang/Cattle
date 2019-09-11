package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.OrderListDto;

/**
  *
  * @ClassName:     退货
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/11 17:58
  * @Version:        1.0
 */

public interface SalesReturnView extends BaseView {

    /**
     * 退货列表
     */
    void getSalesReturnList();
    void getSalesReturnListSucces(OrderListDto orderListDto);
}
