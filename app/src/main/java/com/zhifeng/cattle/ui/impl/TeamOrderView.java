package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.TeamOrder;

public interface TeamOrderView extends BaseView {
    void getTeamOrder();

    void getTeamOrderSuccess(TeamOrder teamOrder);
}
