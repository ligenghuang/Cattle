package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
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


}
