package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.SearchGoods;

public interface SearchGoodsView extends BaseView {
    void getGoods();

    void getGoodsSuccess(SearchGoods searchGoods);
}
