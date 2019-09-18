package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.TeamList;

public interface TeamListView extends BaseView {
    void getTeamList();

    void getTeamListSuccess(TeamList teamList);
}
