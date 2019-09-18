package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.RankingList;

public interface RankingListView extends BaseView {
    void getRankingList();

    void getRankingListSuccess(RankingList rankingList);
}
