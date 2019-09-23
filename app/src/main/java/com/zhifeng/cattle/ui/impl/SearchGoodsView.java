package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.SearchGoods;
import com.zhifeng.cattle.modules.SearchHistory;

public interface SearchGoodsView extends BaseView {
    void getSearchHistory();

    void getSearchHistorySuccess(SearchHistory searchHistory);

    void getGoods();

    void getGoodsSuccess(SearchGoods searchGoods);
}
