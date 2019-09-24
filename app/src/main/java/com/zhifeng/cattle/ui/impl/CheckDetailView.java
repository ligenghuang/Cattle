package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.CheckDetail;

public interface CheckDetailView extends BaseView {
   void getCheckDetail();

   void getCheckDetailSuccess(CheckDetail checkDetail);
}
