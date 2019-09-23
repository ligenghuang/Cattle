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

    /**
     * 获取购物车列表
     */
    void getCartList();
    void getCartListSuccess(CartListDto cartListDto);

    /***
     * 删除购物车商品
     * @param id
     */
    void delCart(String id);
    void delCartSuccess();

    void editCart(String id,String num);
    void editCartSuccess();
    void editCartError(String msg);
}
