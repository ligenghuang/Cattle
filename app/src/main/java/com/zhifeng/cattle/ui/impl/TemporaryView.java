package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.Temporary;

public interface TemporaryView extends BaseView {
    void getTemporary();

    void getTemporarySuccess(Temporary temporary);
}
