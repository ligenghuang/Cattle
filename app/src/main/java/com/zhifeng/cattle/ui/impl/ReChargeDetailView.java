package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.ReChargeDetail;

public interface ReChargeDetailView extends BaseView {
    void getChargeDetail();
    void moreChargeDetail();
    void getChargeDetailSuccess(ReChargeDetail reChargeDetail);
}
