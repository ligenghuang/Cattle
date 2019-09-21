package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.ListPage;

public interface ListPageView extends BaseView {
    void getListPage();

    void getListPageSuccess(ListPage listPage);
}
