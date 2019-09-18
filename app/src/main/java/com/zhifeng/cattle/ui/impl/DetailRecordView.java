package com.zhifeng.cattle.ui.impl;

import com.lgh.huanglib.util.base.BaseView;
import com.zhifeng.cattle.modules.DetailRecord;

public interface DetailRecordView extends BaseView {
   void getDetailRecordList();

   void loadMoreRecordList();

   void getDetailRecordListSuccess(DetailRecord detailRecord);
}
