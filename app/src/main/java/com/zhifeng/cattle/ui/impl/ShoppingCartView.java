package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.CartListDto;

/**
  *
  * @ClassName:     购物车
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/10 12:16
  * @Version:        1.0
 */

public interface ShoppingCartView extends BaseView {

    void getCartList();
    void getCartListSuccess(CartListDto cartListDto);

    void delCart(String id);
    void delCartSuccess();
}
