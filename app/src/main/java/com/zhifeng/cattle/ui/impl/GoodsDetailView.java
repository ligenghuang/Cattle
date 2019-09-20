package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.DefaultCityDto;
import com.zhifeng.cattle.modules.GoodsDetailDto;

/**
  *
  * @ClassName:     商品详情
  * @Description:
  * @Author:         lgh
  * @CreateDate:     2019/9/18 12:14
  * @Version:        1.0
 */

public interface GoodsDetailView extends BaseView {

    /**
     * 商品详情
     */
    void getGoodsDetail();
    void getGoodsDetailSuccess(GoodsDetailDto goodsDetailDto);

    /**
     * 取消关注或关注
     */
    void deleteOrAddCollection();
    void deleteOrAddCollection(String msg);

    /**
     * 立即购买
     * @param sku_id
     * @param cart_number
     */
    void buyNow(int sku_id,int cart_number);
    void buyNowSuccess(int cartId);

    /**
     * 获取默认地址
     */
    void getDefaultCity();
    void getDefaultCitySuccess(DefaultCityDto defaultCityDto);
}
